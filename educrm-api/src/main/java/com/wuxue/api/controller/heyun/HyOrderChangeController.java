package com.wuxue.api.controller.heyun;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.HyOrderChangeService;
import com.wuxue.api.service.HyOrderService;
import com.wuxue.model.HyOrder;
import com.wuxue.model.HyOrderChange;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单变更
 */
@RestController
@RequestMapping(value = "api/heyun/hyOrderChange")
public class HyOrderChangeController implements IFindController<HyOrderChange>,
        ISaveController<HyOrderChange>,IDeleteController<Integer> {

    @Autowired
    HyOrderChangeService hyOrderService;

   @Override
    public Response Find(@RequestBody Request<HyOrderChange> askForLeaveRequest) {
        return hyOrderService.find(askForLeaveRequest);
    }

    @Override
    public Response Save(@RequestBody Request<HyOrderChange> askForLeaveRequest) {
        return hyOrderService.save(askForLeaveRequest);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> stringRequest) {
        return null;
    }
}