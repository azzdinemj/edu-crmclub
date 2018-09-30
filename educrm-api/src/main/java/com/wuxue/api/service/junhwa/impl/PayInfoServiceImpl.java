package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.*;
import com.wuxue.api.service.ParentPayService;
import com.wuxue.api.service.junhwa.PayInfoService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.model.OrderPayFee;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tly
 * @date 2018-08-20
 */
@Service("payInfoService")
public class PayInfoServiceImpl implements PayInfoService {

    @Autowired
    private ParentPayService parentPayService;

    @Autowired
    private ParentOrderMapper parentOrderMapper;
    @Autowired
    private ParentPayMapper parentPayMapper;
    @Autowired
    private OrderPayMapper orderPayMapper;
    @Autowired
    private ParentOrderEditMapper parentOrderEditMapper;
    @Autowired
    private OrderPayFeeMapper orderPayFeeMapper;

    @Override
    public Response saveParentPay(ParentPay parentPay) {
        Response response = Response.newResponse();
        if (parentPay == null) {
            return response.PARAMS_ISNULL();
        }

//        BigDecimal balance = null; //(BigDecimal) parentPayService.getAccountBalance();
//        List<String> orderIds = new ArrayList<>();
//        for (ParentPay parentPay : parentPayList) {
//            orderIds.add(parentPay.getPkOrder());
//        }
//        //获取所有订单

//        List<ParentOrder> orderList = parentOrderMapper.select(orderIds);
//        //统计所有订单金额
//        BigDecimal sumMoney = null;
//        if (CollectionUtils.isEmpty(orderIds)) {
//            response.OPERATION_FAIL("");
//        }

        boolean flag = false;
        boolean status = false;
        ParentOrderEdit parentOrderEdit;
//        if (orderList.size() > 0) {
        OrderPay orderPay;
            String orderId2 = GuidUtils.getGuid();
            String orderId = "";
            String patentPays = "";
//            ParentPay parentPay;
        parentPay.setIsvalid(0);
        List<ParentPay> parentPayList = parentPayMapper.select(parentPay);
//        orderPay = new OrderPay();
//        orderPay.setPkParentPay(parentPayList);
        if (parentPayList.size() > 0) {
            for (ParentPay pay : parentPayList) {
                patentPays = patentPays + "'" + pay.getPkPaymentRecord() + "'"+ ",";
                 }
            }
            if(!patentPays.equals("")) {
                patentPays = patentPays.substring(0, patentPays.length() - 1);
            }
        orderPay = new OrderPay();
        orderPay.setPkParentPayList(patentPays);
        List<OrderPay> select = orderPayMapper.select(orderPay);
        if(select.size() > 0){
            orderId = select.get(0).getPkOrderid();
        }
        if (parentPayList.size() > 0) {
            for (ParentPay pay : parentPayList) {
//                parentPay = new ParentPay();
//                parentPay.setPkOrder(parentOrder.getPkOrder());
//                parentPay.setIsvalid(0);
//                select = parentPayMapper.select(parentPay);
                if(pay.getIsvalid() == 0 ) {
                    orderPay = new OrderPay();
                    orderPay.setPkParentPay(pay.getPkPaymentRecord());
                    orderPay.setPkOrderid(orderId);
                    List<OrderPay> orderPayList = orderPayMapper.select(orderPay);
                    if(orderPayList.size() == 0) {
                        orderPay.setPkOrderPay(GuidUtils.getGuid());
                        orderPay.setPkOrderid(orderId2);
                        orderPayMapper.insertSelective(orderPay);
                    }else{
                        orderPayList.get(0).setPkOrderid(orderId2);
                        orderPayMapper.updateByPrimaryKeySelective(orderPayList.get(0));
                    }
                    flag = true;
                }
//                sumMoney.add(parentOrder.getCost());
//            }
//
            }
        }
        if (flag == true) {
            response.setData(orderId2);

//            创建一条充值记录
            OrderPayFee orderPayFee = new OrderPayFee();
            orderPayFee.setFee(parentPay.getCost());
            orderPayFee.setPkOrderid(orderId);
            orderPayFee.setType(0);
            List<OrderPayFee> orderPayFeeList = orderPayFeeMapper.select(orderPayFee);
            if (orderPayFeeList.size() > 0) {
                orderPayFee.setPkOrderid(orderId2);
                orderPayFee.setPkOrderPayFee(orderPayFeeList.get(0).getPkOrderPayFee());
                orderPayFee.setLasteditDate(new Date());
                orderPayFeeMapper.updateByPrimaryKeySelective(orderPayFee);
            }else{
                orderPayFee.setPkOrderid(orderId2);
                orderPayFee.setCreationDate(new Date());
                orderPayFeeMapper.insertSelective(orderPayFee);
            }
        }


//        if (balance.compareTo(sumMoney) >= 0) {
//            System.out.println("余额充足");
//            //icbc方式金额为0
//            //余额方式金额为套餐价格
//        } else {
//            System.out.println("余额不足");
//            //icbc方式 为套餐价格-余额
//            //余额为账户余额全部
//        }
            return response;
        }

    @Override
    public Response updatePayOrder(String orderId) {
        try {
            OrderPay orderPay = new OrderPay();
            orderPay.setPkOrderid(orderId);
            List<OrderPay> select = orderPayMapper.select(orderPay);
            if (select.size() > 0) {
                for (OrderPay pay : select) {
                    ParentPay parentPay = parentPayMapper.selectByPrimaryKey(pay.getPkParentPay());
                    parentPay.setIsvalid(1);
                    parentPay.setCreationDate(new Date());
                    parentPayMapper.updateByPrimaryKeySelective(parentPay);
                    ParentOrder parentOrder = parentOrderMapper.selectByPrimaryKey(parentPay.getPkOrder());
                    parentOrder.setIspay(1);
                    parentOrderMapper.updateByPrimaryKeySelective(parentOrder);
                }
            }
            OrderPayFee orderPayFee = new OrderPayFee();
            orderPayFee.setPkOrderid(orderId);
            List<OrderPayFee> orderPayFeeList = orderPayFeeMapper.select(orderPayFee);
            if (orderPayFeeList.size() > 0) {
                orderPayFeeList.get(0).setType(1);
                orderPayFee.setLasteditDate(new Date());
                orderPayFeeMapper.updateByPrimaryKeySelective(orderPayFeeList.get(0));
            }
        }catch (Exception e){
            return Response.newResponse().SERVER_ERROR(e.getMessage());
        }
        return Response.newResponse();
    }
}
