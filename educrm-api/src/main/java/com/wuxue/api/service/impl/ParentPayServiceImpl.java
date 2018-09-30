package com.wuxue.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ParentOrderMapper;
import com.wuxue.api.mapper.ParentPayMapper;
import com.wuxue.api.service.ParentOrderService;
import com.wuxue.api.service.ParentPayService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.service.junhwa.PayInfoService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.ParentPay;
import com.wuxue.model.junhwa.PayInfo;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.utils.https.HttpRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("parentPayService")
public class ParentPayServiceImpl implements ParentPayService {

    @Autowired
    private ParentPayMapper parentPayMapper;
    @Autowired
    private UtilsService utilsService;
    @Autowired
    private ParentOrderService parentOrderService;

    @Autowired
    private ParentOrderMapper parentOrderMapper;
    @Autowired
    private PayInfoService payInfoService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            ParentPay parentPay = parentPayMapper.selectByPrimaryKey(primaryKey);
            if (parentPay != null) {
                parentPay.setIsvalid(0);
                parentPayMapper.updateByPrimaryKeySelective(parentPay);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<ParentPay> tParams) {
        Response response = Response.newResponse();
        ParentPay parentPay = tParams.getData();
        if (parentPay == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = parentPay.getPkPaymentRecord();
        if (primaryKey != null && !"".equals(primaryKey)) {
            ParentPay parentPay1 = parentPayMapper.selectByPrimaryKey(primaryKey);
            response.setData(parentPay1);
        } else {
            PageHelper.startPage(parentPay.getPageNo(), parentPay.getPageSize());
            List<ParentPay> list = parentPayMapper.select(parentPay);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            if (list.size() > 0) {
                for (ParentPay pay : list) {
                    utilsService.setMealKeyValue(pay, pay.getSetMealId(), LinkEntity.TKSETMEAL_ENTITY);
                }
            }
            response.setData(list);

        }


        return response;
    }

    @Override
    public Response save(Request<ParentPay> tParams) {
        Response response = Response.newResponse();
        ParentPay parentPay = tParams.getData();
        if (parentPay == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = parentPay.getPkPaymentRecord();
        String message = "";
        ParentPay select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = parentPayMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                parentPay.setLasteditDate(new Date());
                parentPayMapper.updateByPrimaryKeySelective(parentPay);
            } else {
                parentPay.setPkPaymentRecord(GuidUtils.getGuid());
                parentPay.setCreationDate(new Date());
                parentPay.setLasteditDate(new Date());
                parentPay.setIsvalid(1);
                parentPayMapper.insertSelective(parentPay);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }


    @Override
    public List<ParentPay> selectByClassinfo(Map map) {

        List<ParentPay> list = parentPayMapper.selectByClassinfo(map);

        if (list.size() > 0) {
            for (ParentPay parentPay : list) {
                utilsService.setMealKeyValue(parentPay, parentPay.getSetMealId(), LinkEntity.TKSETMEAL_ENTITY);
                utilsService.setStudentIdValue(parentPay, parentPay.getPkStudent(), LinkEntity.STUDENT_ENTITY);

            }
        }

        return list;
    }

    @Override
    public BigDecimal getParentPaySum(String pkStudent) {
        //payMethod ICBC_PAY,BALANCE_PAY
        BigDecimal bigDecimal = parentPayMapper.getParentPaySum(pkStudent, "ICBC_PAY");
        return null == bigDecimal ? new BigDecimal(0) : bigDecimal;
    }

    @Override
    public Response getAccountBalance(String pkStudent) {
        Response response = Response.newResponse();
        if (null == pkStudent || "".equals(pkStudent)) {
            return response.PARAMS_ISNULL();
        }
//        Double balance = getAccountBalanceByStudentId(pkStudent);
        ParentPay parentPay = new ParentPay();
        parentPay.setPkStudent(pkStudent);
        BigDecimal balance = parentPayMapper.getCostSum(parentPay);
        response.setData(balance);
        return response;
    }

    @Override
    public Double getAccountBalanceByStudentId(String pkStudent) {
        //现金支付总结额 - 实际使用后的 = 余额
        BigDecimal parentPaySum = getParentPaySum(pkStudent);
        BigDecimal newOrderSumCost = parentOrderService.getParentOrderPaySum(pkStudent);
        return parentPaySum.subtract(newOrderSumCost).doubleValue();
    }

    @Override
    public Response getPayRecordList(ResultEntity resultEntity) {
        Response response = Response.newResponse();
        if (null == resultEntity) {
            return response.PARAMS_ISNULL();
        }


        PageHelper.startPage(resultEntity.getPageNo(), resultEntity.getPageSize());
        List<ResultEntity> payRecords = parentPayMapper.selectPayRecord(resultEntity);
        PageInfo page = new PageInfo(payRecords);

        response.setTotal(page.getTotal());
        response.setData(payRecords);
        return response;
    }

    @Override
    public void batchSaveParentPay(List<ParentPay> parentPays) {
        for (ParentPay parentPay : parentPays) {
            parentPayMapper.insertSelective(parentPay);
        }
    }

    @Override
    public void toPay(List<String> orderIds) {

        List<ParentOrder> orderList = parentOrderMapper.getParentOrderListByIds(orderIds);
        String pkStudent = orderList.get(0).getPkStudent();
        Double balance = getAccountBalanceByStudentId(pkStudent);

        //订单应该支付总额
        BigDecimal cost = new BigDecimal(0);
        for (ParentOrder parentOrder : orderList) {
            cost = cost.add(parentOrder.getCost());
        }

        BigDecimal balanceBig = BigDecimal.valueOf(balance);
        boolean isFlag = balanceBig.compareTo(cost) >= 0;
//        List<ParentPay> parentPayList = generateParentPay(orderList, balanceBig, cost, isFlag);
//        batchSaveParentPay(parentPayList);
        if (isFlag) {
            System.out.println("余额充足");
            try {
                saveParentPaySetCostIsZero(orderList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("余额不足");
            //icbc方式 为套餐价格-余额
            //余额为账户余额全部
            //调用esc接口
            //发起http请求
            List<String> orderIdList = new ArrayList<>();//订单id
            for (ParentOrder parentOrder : orderList) {
                orderIdList.add(parentOrder.getPkOrder());
            }
            BigDecimal payableAmount = cost.subtract(balanceBig);
            PayInfo payInfo = new PayInfo();
            payInfo.setOrderIdList(orderIdList);
            payInfo.setPayableAmount(payableAmount);
            HttpRequest.sendPost("url", JSON.toJSONString(payInfo));
        }
    }

    @Override
    public Response getBalance(String pkStudent) {
        Response response = Response.newResponse();
        if (null == pkStudent || "".equals(pkStudent)) {
            return response.PARAMS_ISNULL();
        }
//        Double balance = getAccountBalanceByStudentId(pkStudent);
        ParentPay parentPay = new ParentPay();
        parentPay.setPkStudent(pkStudent);
        parentPay.setIsvalid(999);
        BigDecimal balance = parentPayMapper.getCostSum(parentPay);
        response.setData(balance);
        return response;
    }

    //余额充足调此接口,余额抵扣，现金支付统统为0
    private void saveParentPaySetCostIsZero(List<ParentOrder> orderList) {
        //icbc方式金额为0
        //直接修改订单为支付状态
        for (ParentOrder parentOrder : orderList) {
            ParentPay parentPay = new ParentPay();
            parentPay.setPkPaymentRecord(GuidUtils.getGuid());
            parentPay.setPkOrder(parentOrder.getPkOrder());
            parentPay.setCost(BigDecimal.valueOf(0));
            parentPay.setPaymentMethod("ICBC_PAY");
            parentPay.setPaymentDate(new Date());
            parentPay.setPkStudent(parentOrder.getPkStudent());
            parentPay.setPkLinkman(parentOrder.getPkLinkman());
            parentPay.setIsvalid(1);
            parentOrderService.updateOrderStatusById(parentOrder.getPkOrder());
        }
    }

    //余额不足调此接口，共支付成功后调用
    private void insufficientBalance(List<String> orderList) {
        List<ParentOrder> parentOrderList = parentOrderService.getParentOrderListByIds(orderList).getData();
        if (CollectionUtils.isEmpty(parentOrderList)) {
            //TODO 为空操作
        }
        String pkStudent = parentOrderList.get(0).getPkStudent();
        for (ParentOrder parentOrder : parentOrderList) {
            BigDecimal balance = BigDecimal.valueOf(getAccountBalanceByStudentId(pkStudent));
            ParentPay parentPay = new ParentPay();
            parentPay.setPkPaymentRecord(GuidUtils.getGuid());
            parentPay.setPkOrder(parentOrder.getPkOrder());
            parentPay.setPaymentMethod("ICBC_PAY");
            parentPay.setPaymentDate(new Date());
            parentPay.setPkStudent(parentOrder.getPkStudent());
            parentPay.setPkLinkman(parentOrder.getPkLinkman());
            parentPay.setIsvalid(1);
            if (balance.compareTo(parentOrder.getCost()) >= 0) {
                parentPay.setCost(BigDecimal.valueOf(0));
            } else {
                parentPay.setCost((parentOrder.getCost().subtract(balance)));
            }
            parentOrderService.updateOrderStatusById(parentOrder.getPkOrder());
        }
    }


//    private List<ParentPay> generateParentPay(List<ParentOrder> orderList, BigDecimal balance, BigDecimal cost, boolean flag) {
//
//        String pkStudent = orderList.get(0).getPkStudent();
//        String pkLinkman = orderList.get(0).getPkLinkman();
//
//        String payInfoId = null;
//        if (!flag) {
//            PayInfo payInfo = new PayInfo();
//            payInfo.setPayInfoId(GuidUtils.getGuid());
//            payInfo.setCost(cost.subtract(balance).doubleValue());
//            payInfoId = payInfoService.savePayInfo(payInfo);
//
//        }
//
//        List<ParentPay> parentPayList = new ArrayList<>(orderList.size());
//        BigDecimal zero = new BigDecimal(0);
//        for (ParentOrder parentOrder : orderList) {
//            BigDecimal price = parentOrder.getSetMeal().getPrice();
//            ParentPay icbcPay = new ParentPay();
//            ParentPay balancePay = new ParentPay();
//
//            icbcPay.setPayInfoId(payInfoId);
//            balancePay.setPayInfoId(payInfoId);
//            if (flag) {
//                //总消费>余额
//                icbcPay.setCost(price.subtract(balance).doubleValue());
//                balancePay.setCost(balance.doubleValue());
//                balance = new BigDecimal(0);
//                //0 未支付， 1支付
//                icbcPay.setIsvalid(0);
//                balancePay.setIsvalid(0);
//            } else {
//                //总消费<余额
//                icbcPay.setCost(zero.doubleValue());
//                balancePay.setCost(price.doubleValue());
//                balance = balance.subtract(price);
//
//                //0 未支付， 1支付
//                icbcPay.setIsvalid(1);
//                balancePay.setIsvalid(1);
//            }
//
//            icbcPay.setPkOrder(parentOrder.getPkOrder());
//            icbcPay.setPaymentMethod("ICBC_PAY");
//            icbcPay.setPaymentDate(new Date());
//            icbcPay.setPkStudent(pkStudent);
//            icbcPay.setPkLinkman(pkLinkman);
//
//            icbcPay.setSetMealId(parentOrder.getSetMealId());
//
//            balancePay.setPkOrder(parentOrder.getPkOrder());
//            balancePay.setPaymentMethod("BALANCE_PAY");
//            balancePay.setPaymentDate(new Date());
//            balancePay.setPkStudent(pkStudent);
//            balancePay.setPkLinkman(pkLinkman);
//
//            balancePay.setSetMealId(parentOrder.getSetMealId());
//
//            parentPayList.add(icbcPay);
//            parentPayList.add(balancePay);
//        }
//
//        return parentPayList;
//    }
}
