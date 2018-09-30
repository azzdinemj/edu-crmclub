package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.DebusRecordMapper;
import com.wuxue.api.service.DebusRecordService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.DateUtil;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.shuttle.DebusRecord;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service("debusRecordService")
public class DebusRecordServiceImpl implements DebusRecordService {
    @Autowired
    private DebusRecordMapper debusRecordMapper;
    @Autowired
    private UtilsService utilsService;

    @Override
    public Response audit(Request<DebusRecord> tParams) {
        return null;
    }

    @Override
    public Response delete(Request<String> tParams) {
        return null;
    }

    @Override
    public Response find(Request<DebusRecord> tParams) {


        Response response = Response.newResponse();
        DebusRecord debusRecord = tParams.getData();

        if (debusRecord == null) {
            return response.PARAMS_ISNULL();
        }
        PageHelper.startPage(debusRecord.getPageNo(), debusRecord.getPageSize());
        List<DebusRecord> list = debusRecordMapper.select(debusRecord);
        PageInfo page = new PageInfo(list);
        response.setTotal(page.getTotal());
        for (DebusRecord debusRecordEntity : list) {
//            utilsService.setSysUserKeyValue(debusRecordEntity, debusRecordEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
//            utilsService.setSysUserKeyValue(debusRecordEntity, debusRecordEntity.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setStudentKeyValue(debusRecordEntity, debusRecordEntity.getPkStudent(), LinkEntity.STUDENT_ENTITY);
        }
        response.setData(list);
        return response;
    }

    @Override
    public Response save(Request<DebusRecord> tParams) {
        Response response = Response.newResponse();
        DebusRecord debusRecord = tParams.getData();
        if (null == debusRecord) {
            return response.PARAMS_ISNULL();
        }
        String pkStudent = debusRecord.getPkStudent();
        if (null == pkStudent || "".equals(pkStudent)) {
            return response.PARAMS_ISNULL();
        }
        if (null == debusRecord.getSchoolBus() || "".equals(debusRecord.getSchoolBus())) {
            return response.PARAMS_ISNULL();
        }
        Byte direction = debusRecord.getDirection();
        if (null == direction || "" .equals(direction)){
            return response.PARAMS_ISNULL();
        }
        if (null == debusRecord.getSiteId() || "".equals(debusRecord.getSiteId())) {
            return response.PARAMS_ISNULL();
        }
        if (null == debusRecord.getStatus() || "".equals(debusRecord.getStatus())) {
            return response.PARAMS_ISNULL();
        }
        List<String> studentIds = new ArrayList<>();
        studentIds.add(pkStudent);

        Date startTime;
        Date endTime;
        //'方向，1=校外开往校内，2=校内开往校外'
        if (1 == direction){
            endTime = DateUtil.getForenoonTime();
            startTime = DateUtil.getCurrentDayStartTime();
        }else if(2 == direction){
            startTime = DateUtil.getAfternoonTime();
            endTime = DateUtil.getCurrentDayEndTime();
        }else{
            return response.PARAMS_ISNULL();
        }

        Calendar calendar = Calendar.getInstance();
        Boolean flag = DateUtil.belongCalendar(calendar.getTime(), startTime, endTime);
        if (!flag) {
            return response.OPERATION_FAIL(direction == 1 ? ",现在是放学期间" : "现在上学期间");
        }


        List<DebusRecord> debusRecords = getLatestDebusRecordByPkStudent(studentIds,startTime,endTime);
        DebusRecord debusRecordOld = null;
        if (CollectionUtils.isNotEmpty(debusRecords)) {
            debusRecordOld = debusRecords.get(0);
        }
        try {
            if (null == debusRecordOld) {
                if (null == debusRecord.getStatus()) {
                    debusRecord.setStatus(1);
                }
                debusRecord.setPkDomain("1");
                debusRecord.setPkDebusRecord(GuidUtils.getGuid());
                debusRecord.setDate(new Date());
                debusRecordMapper.insertSelective(debusRecord);
            } else {
                debusRecordMapper.deleteByPrimaryKey(debusRecordOld.getPkDebusRecord());
            }
        } catch (Exception e) {
            return response.SAVE_FAIL(e.getMessage());
        }
        return response;
    }

    @Override
    public Response submit(Request<DebusRecord> tParams) {
        return null;
    }

    @Override
    public List<DebusRecord> getLatestDebusRecordByPkStudent(List<String> pkStudentIds,Date startTime,Date endTime) {
        return debusRecordMapper.getLatestDebusRecordByPkStudent(pkStudentIds,startTime,endTime);
    }
}
