package com.wuxue.api.controller.heyun;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.HyProductService;
import com.wuxue.api.service.HyUserService;
import com.wuxue.model.HyProduct;
import com.wuxue.model.HyUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品
 */
@RestController
@RequestMapping(value = "api/heyun/hyProduct")
public class HyProductController implements IFindController<HyProduct>,
        ISaveController<HyProduct>,IDeleteController<Integer> {

    @Autowired
    HyProductService hyProductService;

   @Override
    public Response Find(@RequestBody Request<HyProduct> askForLeaveRequest) {
        return hyProductService.find(askForLeaveRequest);
    }

    @Override
    public Response Save(@RequestBody Request<HyProduct> askForLeaveRequest) {
        return hyProductService.save(askForLeaveRequest);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> stringRequest) {
        return null;
    }
}