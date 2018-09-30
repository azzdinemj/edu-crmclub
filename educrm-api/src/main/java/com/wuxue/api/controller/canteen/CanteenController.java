package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.*;
import com.wuxue.api.service.CanteenService;
import com.wuxue.model.Canteen;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10  食堂
 */
@RestController
@RequestMapping(value = "api/canteen/canteen")
public class CanteenController implements IFindController<Canteen>,
        ISaveController<Canteen>,IDeleteController<String>{
    @Autowired
    private CanteenService canteenService;

    @Override
    public Response Find(@RequestBody Request<Canteen> canteen) {

        return canteenService.find(canteen);
    }

    @Override
    public Response Save(@RequestBody Request<Canteen> canteen) {
        return canteenService.save(canteen);
    }

    @Override
    public Response Delete(@RequestBody Request<String> canteen) {
        return canteenService.delete(canteen);
    }


}
