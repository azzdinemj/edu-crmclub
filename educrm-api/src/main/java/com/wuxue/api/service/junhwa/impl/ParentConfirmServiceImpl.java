package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.ParentConfirmMapper;
import com.wuxue.api.service.BoardingRecordService;
import com.wuxue.api.service.DebusRecordService;
import com.wuxue.api.service.junhwa.ParentConfirmService;
import com.wuxue.api.utils.DateUtil;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.junhwa.ParentConfirm;
import com.wuxue.model.shuttle.DebusRecord;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 家长确认
 *
 * @author tly
 * @date 2018-07-30
 */
@Service("parentConfirmService")
public class ParentConfirmServiceImpl implements ParentConfirmService {

    @Autowired
    private ParentConfirmMapper parentConfirmMapper;
    @Autowired
    private DebusRecordService debusRecordService;
    @Autowired
    private BoardingRecordService boardingRecordService;

    @Override
    public Response saveParentConfirmInfo(ParentConfirm parentConfirm) {
        Response response = Response.newResponse();
        if (null == parentConfirm) {
            return response.PARAMS_ISNULL();
        }
        if (null == parentConfirm.getPkLinkman() || "".equals(parentConfirm.getPkLinkman())) {
            return response.PARAMS_ISNULL();
        }
        if (null == parentConfirm.getPkStudent() || "".equals(parentConfirm.getPkStudent())) {
            return response.PARAMS_ISNULL();
        }


        //下车记录
        List<String> studentIds = new ArrayList<>();
        studentIds.add(parentConfirm.getPkStudent());
        List<DebusRecord> debusRecords = debusRecordService.getLatestDebusRecordByPkStudent(studentIds, DateUtil.getAfternoonTime(), DateUtil.getCurrentDayEndTime());
       if (CollectionUtils.isEmpty(debusRecords)){
           return response.CONFIRM_FAIL("学生可能还没下车");
       }
        parentConfirm.setPkConfirm(GuidUtils.getGuid());
        DebusRecord debusRecord = debusRecords.get(0);
        parentConfirm.setPkDebusRecord(debusRecord.getPkDebusRecord());
//        parentConfirm.setPkLinkman(parentConfirm.getPkLinkman());
        if (null == parentConfirm.getStatus()){
            parentConfirm.setStatus(1);
        }
        parentConfirm.setPkSchoolBus(debusRecord.getSchoolBus());
        parentConfirm.setCreationDate(new Date());
        parentConfirmMapper.insert(parentConfirm);
        return response;
    }
}
