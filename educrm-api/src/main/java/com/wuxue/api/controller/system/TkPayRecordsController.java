package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TkPayRecordsService;
import com.wuxue.model.TkPayRecords;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/tkPayRecords")
public class TkPayRecordsController implements IFindController<TkPayRecords>,
        ISaveController<TkPayRecords>,IDeleteController<String>{
    @Autowired
    private TkPayRecordsService tkPayRecordsService;

    @Override
    @ResponseBody
    public Response Find(@RequestBody Request<TkPayRecords> tkPayRecords) {
        return tkPayRecordsService.find(tkPayRecords);
    }

    @Override
    public Response Save(@RequestBody Request<TkPayRecords> tkPayRecords) {
        return tkPayRecordsService.save(tkPayRecords);
    }

    @Override
    public Response Delete(@RequestBody Request<String> tkPayRecords) {
        return tkPayRecordsService.delete(tkPayRecords);
    }
}
