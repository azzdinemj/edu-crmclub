package com.wuxue.api.controller.shuttle;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.BoardingRecordService;
import com.wuxue.model.shuttle.BoardingRecord;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 上车记录
 *
 * @author tly
 * @data 2018/08/03
 */
@RestController
@RequestMapping(value = "api/shuttle/boardingRecord")
public class BoardingRecordController implements ISaveController<BoardingRecord> ,IFindController<BoardingRecord>{
    @Autowired
    private BoardingRecordService boardingRecordService;

    /**
     * 确认上车
     *
     * @param request
     * @return
     */
    @Override
    public Response Save(@RequestBody Request<BoardingRecord> request) {
        return boardingRecordService.save(request);
    }

    @Override
    public Response Find(@RequestBody Request<BoardingRecord> canteen) {

        return boardingRecordService.find(canteen);
    }




}
