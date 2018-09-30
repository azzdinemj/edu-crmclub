package com.wuxue.api.controller.shuttle;

import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.junhwa.NoticeRecordService;
import com.wuxue.model.shuttle.NoticeRecord;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 校车路线
 *
 * @author tly
 * @data 2018/08/03
 */
@RestController
@RequestMapping(value = "api/shuttle/noticeRecord")
public class NoticeRecordController implements ISaveController<NoticeRecord> {
    @Autowired
    private NoticeRecordService noticeRecordService;

    /**
     * 发送到站通知
     * @param request
     * @return
     */
    @Override
    public Response Save(@RequestBody Request<NoticeRecord> request) {
        return noticeRecordService.save(request);
    }
}
