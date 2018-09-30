package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.NoticeRecordMapper;
import com.wuxue.api.service.junhwa.NoticeRecordService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.shuttle.NoticeRecord;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 发送通知
 *
 * @author tly
 * @date 2018-08-03
 */
@Service("noticeRecordService")
public class NoticeRecordServiceImpl implements NoticeRecordService {
    @Autowired
    private NoticeRecordMapper noticeRecordMapper;

    @Override
    public Response save(Request<NoticeRecord> tParams) {
        Response response = Response.newResponse();
        NoticeRecord noticeRecord = tParams.getData();
        if (null == noticeRecord) {
            return response.PARAMS_ISNULL();
        }
        if (null == noticeRecord.getPkSchoolBus() || "".equals(noticeRecord.getPkSchoolBus())){
            return response.PARAMS_ISNULL();
        }
        if (null == noticeRecord.getLineNum() || "".equals(noticeRecord.getLineNum())){
            return response.PARAMS_ISNULL();
        }
        noticeRecord.setPkNoticeRecord(GuidUtils.getGuid());
        if (null == noticeRecord.getPkNotificationRecord() || "" .equals(noticeRecord.getPkNotificationRecord())){
            noticeRecord.setPkNotificationRecord("1");
        }
        if (null == noticeRecord.getCode()){
            noticeRecord.setCode("1");
        }
        if (null == noticeRecord.getNum() || 0 == noticeRecord.getNum()){
            noticeRecord.setNum(1);
        }
        if (null == noticeRecord.getPkDomain()){
            noticeRecord.setPkDomain("1");
        }
        noticeRecord.setDate(new Date());
        try {
            noticeRecordMapper.insertSelective(noticeRecord);
        } catch (Exception e) {
            return response.SEND_FAIL(e.getMessage());
        }

        return response;
    }
}
