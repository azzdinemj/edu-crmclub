package com.wuxue.api.controller.heyun;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.HyOrderService;
import com.wuxue.model.HyOrder;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单
 */
@RestController
@RequestMapping(value = "api/heyun/hyOrder")
public class HyOrderController implements IFindController<HyOrder>,
        ISaveController<HyOrder>,IDeleteController<Integer> {

    @Autowired
    HyOrderService hyOrderService;

   @Override
    public Response Find(@RequestBody Request<HyOrder> askForLeaveRequest) {
        return hyOrderService.find(askForLeaveRequest);
    }

    @Override
    public Response Save(@RequestBody Request<HyOrder> askForLeaveRequest) {
        return hyOrderService.save(askForLeaveRequest);
    }

    @RequestMapping("/countby")
    public Response CoutBy(@RequestBody Request<HyOrder> askForLeaveRequest){
        return hyOrderService.countBy(askForLeaveRequest);
    }

    //更改订单状态（添加更改记录）
    @RequestMapping("/updatestatus")
    public Response updateStatus(@RequestBody Request<HyOrder> hyOrderRequest){
        return hyOrderService.updateStatus(hyOrderRequest);
    }

    @RequestMapping("/saveorder")
    public Response SaveOrder(@RequestBody Request<String> String){
        return hyOrderService.saveOrder(String);
    }


    @Override
    public Response Delete(@RequestBody Request<Integer> stringRequest) {
        return null;
    }
}