package com.wuxue.api.controller.heyun;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.HyPayService;
import com.wuxue.api.service.HyUserService;
import com.wuxue.model.HyPay;
import com.wuxue.model.HyUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付明细
 */
@RestController
@RequestMapping(value = "api/heyun/hyPay")
public class HyPayController implements IFindController<HyPay>,
        ISaveController<HyPay>,IDeleteController<Integer> {

    @Autowired
    HyPayService hyPayService;

   @Override
    public Response Find(@RequestBody Request<HyPay> askForLeaveRequest) {
        return hyPayService.find(askForLeaveRequest);
    }

    @Override
    public Response Save(@RequestBody Request<HyPay> askForLeaveRequest) {
        return hyPayService.save(askForLeaveRequest);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> stringRequest) {
        return null;
    }
}