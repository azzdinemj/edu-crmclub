package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.BoardingRecordMapper;
import com.wuxue.api.service.BoardingRecordService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.DateUtil;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.shuttle.BoardingRecord;
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


@Service("boardingRecordService")
public class BoardingRecordServiceImpl implements BoardingRecordService {

    @Autowired
    private BoardingRecordMapper boardingRecordMapper;
    @Autowired
    private UtilsService utilsService;

    @Override
    public Response audit(Request<BoardingRecord> tParams) {
        return null;
    }

    @Override
    public Response delete(Request<String> tParams) {
        return null;
    }

    @Override
    public Response find(Request<BoardingRecord> tParams) {


        Response response = Response.newResponse();
        BoardingRecord boardingRecord = tParams.getData();

        if (boardingRecord == null) {
            return response.PARAMS_ISNULL();
        }
        PageHelper.startPage(boardingRecord.getPageNo(), boardingRecord.getPageSize());
        List<BoardingRecord> list = boardingRecordMapper.select(boardingRecord);
        PageInfo page = new PageInfo(list);
        response.setTotal(page.getTotal());
        for (BoardingRecord boardingRecordEntity : list) {
//            utilsService.setSysUserKeyValue(boardingRecordEntity, boardingRecordEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
//            utilsService.setSysUserKeyValue(boardingRecordEntity, boardingRecordEntity.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setStudentKeyValue(boardingRecordEntity, boardingRecordEntity.getPkStudent(), LinkEntity.STUDENT_ENTITY);

        }
        response.setData(list);
        return response;
    }


    @Override
    public Response save(Request<BoardingRecord> tParams) {
        Response response = Response.newResponse();
        BoardingRecord boardingRecord = tParams.getData();
        if (null == boardingRecord) {
            return response.PARAMS_ISNULL();
        }
        String pkStudent = boardingRecord.getPkStudent();
        if(null == pkStudent || "".equals(pkStudent)){
            return response.PARAMS_ISNULL();
        }
        if(null == boardingRecord.getSchoolBus() || "".equals(boardingRecord.getSchoolBus())){
            return response.PARAMS_ISNULL();
        }
        if(null == boardingRecord.getSiteId() || "".equals(boardingRecord.getSiteId())){
            return response.PARAMS_ISNULL();
        }
        if(null == boardingRecord.getStatus() || "".equals(boardingRecord.getStatus())){
            return response.PARAMS_ISNULL();
        }
        Byte direction = boardingRecord.getDirection();
        if (null == direction || "" .equals(direction)){
            return response.PARAMS_ISNULL();
        }

        Date startTime;
        Date endTime;
        //'方向，1=校外开往校内，2=校内开往校外'
        if (1 == direction){
            startTime = DateUtil.getCurrentDayStartTime();
            endTime = DateUtil.getForenoonTime();
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

        List<String> studentIds =new ArrayList<>();
        studentIds.add(pkStudent);
        List<BoardingRecord> boardingRecords = getLatestBoardingRecordByPkStudent(studentIds,startTime,endTime);
        BoardingRecord boardingRecordOld =null;
        if (CollectionUtils.isNotEmpty(boardingRecords)){
            boardingRecordOld = boardingRecords.get(0);
        }
        try {
            if (null == boardingRecordOld) {
                boardingRecord.setPkDomain("1");
                boardingRecord.setPkBoardingRecord(GuidUtils.getGuid());
                boardingRecord.setDate(new Date());
                boardingRecord.setCreationDate(new Date());
                boardingRecordMapper.insertSelective(boardingRecord);
            } else {
                boardingRecordMapper.deleteByPrimaryKey(boardingRecordOld.getPkBoardingRecord());
            }
        } catch (Exception e) {
            return response.SAVE_FAIL(e.getMessage());
        }
        return response;
    }

    @Override
    public Response submit(Request<BoardingRecord> tParams) {
        return null;
    }

    @Override
    public List<BoardingRecord> getLatestBoardingRecordByPkStudent(List<String> pkStudentIds,Date startTime,Date endTime) {
        Calendar calendar =Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,11);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return boardingRecordMapper.getLatestBoardingRecordByPkStudent(pkStudentIds, startTime,endTime);
    }
}
