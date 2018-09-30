package com.wuxue.api.controller.wxopenid;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.DatavalidService;
import com.wuxue.model.Datavalid;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping(value = "api/wxopenid/datavalid/")
public class DatavalidController implements IFindController<Datavalid>,
        ISaveController<Datavalid>, IDeleteController<String> {


    @Autowired
    private DatavalidService datavalidService;

    @Override
    public Response Find(@RequestBody Request<Datavalid> course) {
        return datavalidService.find(course);
    }

    @Override
    public Response Save(@RequestBody Request<Datavalid> course) {
        return datavalidService.save(course);
    }

    @Override
    public Response Delete(@RequestBody Request<String> course) {
        //return wxOpenidService.delete(course);
        return null;
    }


}
