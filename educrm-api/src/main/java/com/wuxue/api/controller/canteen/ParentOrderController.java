package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ParentOrderService;
import com.wuxue.model.ParentOrder;
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
 * 家长点餐信息
 */
@RestController
@RequestMapping(value = "api/canteen/parentOrder")
public class ParentOrderController implements IFindController<ParentOrder>,
        ISaveController<ParentOrder>, IDeleteController<String> {
    @Autowired
    private ParentOrderService parentOrderService;

    @Override
    public Response Find(@RequestBody Request<ParentOrder> parentOrder) {
        return parentOrderService.find(parentOrder);
    }

    @RequestMapping(value = "/selectforrecord", method = RequestMethod.POST)
    public Response selectForRecord(@RequestBody Request<ParentOrder> parentOrder) {
        return parentOrderService.selectForRecord(parentOrder);
    }

    /**
     * 保存和修改订单
     *
     * @param parentOrder
     * @return
     */
    @Override
    public Response Save(@RequestBody Request<ParentOrder> parentOrder) {
        return parentOrderService.save(parentOrder);
    }

    @Override
    public Response Delete(@RequestBody Request<String> parentOrder) {
        return parentOrderService.delete(parentOrder);
    }

    @RequestMapping(value = "/getParentOrderListByIds", method = RequestMethod.POST)
    public Response getParentOrderListByIds(@RequestBody List<String> ids) {
        return parentOrderService.getParentOrderListByIds(ids);
    }

    /**
     * 获取所有未支付订单总金额
     *
     * @return
     */
    @RequestMapping(value = "/getNonPaymentOrderMoneySum", method = RequestMethod.POST)
    public Response getNonPaymentOrderMoneySum(Request<String> request) {
        return parentOrderService.getNonPaymentOrderMoneySum(request);
    }

}
