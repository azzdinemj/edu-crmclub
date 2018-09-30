package com.wuxue.api.controller.shuttle;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.DebusRecordService;
import com.wuxue.model.shuttle.DebusRecord;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 下车记录
 *
 * @author tly
 * @data 2018/08/03
 */
@RestController
@RequestMapping(value = "api/shuttle/debusRecord")
public class DeBusRecordController implements ISaveController<DebusRecord>,IFindController<DebusRecord> {
    @Autowired
    private DebusRecordService debusRecordService;

    /**
     * 确认下车
     *
     * @param request
     * @return
     */
    @Override
    public Response Save(@RequestBody Request<DebusRecord> request) {
        return debusRecordService.save(request);
    }

    @Override
    public Response Find(@RequestBody Request<DebusRecord> request) {
        return debusRecordService.find(request);
    }
}
