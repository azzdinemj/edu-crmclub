package com.wuxue.api.controller.heyun;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.HyOrderDetailService;
import com.wuxue.model.HyOrderDetail;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单明细
 */
@RestController
@RequestMapping(value = "api/heyun/hyOrderDetail")
public class HyOrderDetailController implements IFindController<HyOrderDetail>,
        ISaveController<HyOrderDetail>,IDeleteController<Integer> {

    @Autowired
    HyOrderDetailService hyOrderDetailService;

   @Override
    public Response Find(@RequestBody Request<HyOrderDetail> askForLeaveRequest) {
        return hyOrderDetailService.find(askForLeaveRequest);
    }

    @Override
    public Response Save(@RequestBody Request<HyOrderDetail> askForLeaveRequest) {
        return hyOrderDetailService.save(askForLeaveRequest);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> stringRequest) {
        return null;
    }
}