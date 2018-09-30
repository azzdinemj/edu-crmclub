package com.wuxue.api.controller.product;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TkLearnRecordsService;
import com.wuxue.model.TkLearnRecords;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * description: 学习(产品)记录表
 * @auther: wh
 * @date: 2018/6/13 14:56
 */
@RestController
@RequestMapping(value = "api/product/tklearnrecords")
public class TkLearnRecordsController implements IFindController<TkLearnRecords>,
        ISaveController<TkLearnRecords>,IDeleteController<TkLearnRecords> {


    @Autowired
    TkLearnRecordsService tkLearnRecordsService;

    @Override
    public Response Find(@RequestBody Request<TkLearnRecords> request) {
        return tkLearnRecordsService.find(request);
    }

    @Override
    public Response Save(@RequestBody Request<TkLearnRecords> request) {
        return tkLearnRecordsService.save(request);
    }

    @Override
    public Response Delete(@RequestBody Request<TkLearnRecords> request) {
        return tkLearnRecordsService.delete(request);
    }
}