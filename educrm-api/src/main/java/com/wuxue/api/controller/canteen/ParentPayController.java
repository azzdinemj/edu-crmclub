package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ParentPayService;
import com.wuxue.api.service.junhwa.PayInfoService;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.ParentPay;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jie 2018/07/10
 * 家长支付记录
 */
@RestController
@RequestMapping(value = "api/canteen/parentPay")
public class ParentPayController implements IFindController<ParentPay>,
        ISaveController<ParentPay>,IDeleteController<String>{
    @Autowired
    private ParentPayService parentPayService;
    @Autowired
    private PayInfoService payInfoService;

    @Override
    public Response Find(@RequestBody Request<ParentPay> parentPay) {
        return parentPayService.find(parentPay);
    }

    @Override
    public Response Save(@RequestBody Request<ParentPay> parentPay) {
        return parentPayService.save(parentPay);
    }

    @Override
    public Response Delete(@RequestBody Request<String> parentPay) {
        return parentPayService.delete(parentPay);
    }

    /**
     * 支付记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/getpayrecordlist",method = RequestMethod.POST)
    public Response getPayRecordList(@RequestBody Request<ResultEntity> request){
        return parentPayService.getPayRecordList(request.getData());
    }

    @RequestMapping(value = "/getMeet" ,method = RequestMethod.POST)
    public Response getMeet(@RequestBody String pkStudent) {
        return parentPayService.getAccountBalance(pkStudent);
    }

    @RequestMapping(value = "/getBalance" ,method = RequestMethod.POST)
    public Response getBalance(@RequestBody String pkStudent) {
        return parentPayService.getBalance(pkStudent);
    }

    /**
     * 保存支付记录
     */
    @RequestMapping(value = "/saveParentPay", method = RequestMethod.POST)
    public Response saveParentPay(@RequestBody ParentPay parentPay) {
        return payInfoService.saveParentPay(parentPay);
    }

    /**
     * 修改订单支付状态
     */
    @RequestMapping(value = "/updatePayOrder", method = RequestMethod.POST)
    public Response updatePayOrder(@RequestBody String orderId) {
        return payInfoService.updatePayOrder(orderId);
    }

}
