package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ParentOrderEditMapper;
import com.wuxue.api.mapper.ParentOrderMapper;
import com.wuxue.api.mapper.ParentPayMapper;
import com.wuxue.api.mapper.SetMealMapper;
import com.wuxue.api.service.ParentOrderEditService;
import com.wuxue.api.service.ParentOrderService;
import com.wuxue.api.service.SetMealService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("parentOrderService")
public class ParentOrderServiceImpl implements ParentOrderService {

    @Autowired
    private ParentOrderMapper parentOrderMapper;
    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private SetMealService setMealService;
    @Autowired
    private ParentOrderEditService parentOrderEditService;
    @Autowired
    private ParentPayMapper parentPayMapper;
    @Autowired
    private ParentOrderEditMapper parentOrderEditMapper;


    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            ParentOrderEdit parentOrderEdit = new ParentOrderEdit();
            ParentOrder parentOrder = parentOrderMapper.selectByPrimaryKey(primaryKey);
            if (parentOrder != null) {
//                新增删除记录
                    parentOrderEdit.setOldCost(parentOrder.getCost());
                    parentOrderEdit.setOldMealId(parentOrder.getSetMealId());
                    parentOrderEdit.setType(1);
                    parentOrderEdit.setPkLinkman(parentOrder.getPkLinkman());
                    parentOrderEdit.setPkStudent(parentOrder.getPkStudent());
                    parentOrderEdit.setCreationDate(new Date());
                    parentOrderEdit.setParentOrder(parentOrder.getPkOrder());
                    parentOrderEdit.setPkOrderEdit(GuidUtils.getGuid());
                    parentOrderEditMapper.insertSelective(parentOrderEdit);
//                    parentOrder.setIsvalid(0);

//              新增一条支付记录（正数）
                    if (parentOrder.getIspay() == 1) {
                        ParentPay parentPay = new ParentPay();
                        parentPay.setPkOrder(parentOrderEdit.getParentOrder());
                        parentPay.setPkOrder(parentOrder.getPkOrder());
                        parentPay.setCost(parentOrder.getCost());
                        parentPay.setPkPaymentRecord(GuidUtils.getGuid());
                        parentPay.setSetMealId(parentOrder.getSetMealId());
                        parentPay.setPkStudent(parentOrder.getPkStudent());
                        parentPay.setPkLinkman(parentOrder.getPkLinkman());
                        parentPay.setIsvalid(0);
                        parentPay.setPaymentMethod("ICBC_PAY");
                        parentPay.setPaymentDate(new Date());
                        parentPay.setCreationDate(new Date());
                        parentPay.setLasteditDate(new Date());
                        parentPayMapper.insertSelective(parentPay);

                    } else {
                        ParentPay parentPay = new ParentPay();
                        parentPay.setPkOrder(parentOrder.getPkOrder());
                        List<ParentPay> select = parentPayMapper.select(parentPay);
                        if (select.size() > 0) {
                            for (ParentPay pay : select) {
                                parentPayMapper.deleteByPrimaryKey(pay.getPkPaymentRecord());
                            }
                        }
                    }

                //                        删除套餐
                parentOrderMapper.deleteByPrimaryKey(parentOrder.getPkOrder());
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response find(Request<ParentOrder> tParams) {
        Response response = Response.newResponse();
        ParentOrder parentOrder = tParams.getData();
        if (parentOrder == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = parentOrder.getPkOrder();
        if (primaryKey != null && !"".equals(primaryKey)) {
            ParentOrder parentOrder1 = parentOrderMapper.selectByPrimaryKey(primaryKey);
            response.setData(parentOrder1);
        } else {
            PageHelper.startPage(parentOrder.getPageNo(), parentOrder.getPageSize());
            List<ParentOrder> list = parentOrderMapper.select(parentOrder);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            response.setData(list);
        }


        return response;
    }

    @Override
    public Response selectForRecord(Request<ParentOrder> tParams) {
        Response response = Response.newResponse();
        ParentOrder parentOrder = tParams.getData();
        if (parentOrder == null) {
            return response.PARAMS_ISNULL();
        }
        PageHelper.startPage(parentOrder.getPageNo(), parentOrder.getPageSize());
        List<ParentOrder> list = parentOrderMapper.selectForRecord(parentOrder);
        PageInfo page = new PageInfo(list);
        response.setTotal(page.getTotal());
        response.setData(list);

        return response;
    }

    @Override
    public Response save(Request<ParentOrder> tParams) {
        ParentOrder parentOrder = tParams.getData();
        return saveOrUpdateParentOrder(parentOrder);
    }

    public Response saveOrUpdateParentOrder(ParentOrder parentOrder) {
        Response response = Response.newResponse();
        if (parentOrder == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = parentOrder.getPkOrder();
        String message = "";
        ParentOrder oldParentOrder = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            oldParentOrder = parentOrderMapper.selectByPrimaryKey(primaryKey);
//            if (null == oldParentOrder) {
//                return response.ILLEGAL_REQUEST();
//            }
        }
        if (null == parentOrder.getSetMealId() || "".equals(parentOrder.getSetMealId())) {
            return response.PARAMS_ISNULL();
        }
        if (null == parentOrder.getPkStudent() || "".equals(parentOrder.getPkStudent())) {
            return response.PARAMS_ISNULL();
        }
        if (null == parentOrder.getPkLinkman() || "".equals(parentOrder.getPkLinkman())) {
            return response.PARAMS_ISNULL();
        }
        if (null == parentOrder.getType() || "".equals(parentOrder.getType())) {
            return response.PARAMS_ISNULL();
        }
        if (null == parentOrder.getMealDate() || "".equals(parentOrder.getMealDate())) {
            return response.PARAMS_ISNULL();
        }
        try {
            SetMeal newsetMeal = setMealService.findOnlySetMealById(parentOrder.getSetMealId());//根据套餐id获取套餐信息
            BigDecimal num = BigDecimal.valueOf(0);
            if (oldParentOrder == null) {
                parentOrder.setPkOrder(GuidUtils.getGuid());
                parentOrder.setCost(newsetMeal.getPrice());
                parentOrder.setIspay(0);
                parentOrder.setIsvalid(1);
                parentOrder.setCreationDate(new Date());
                parentOrder.setOrderDate(new Date());
                insertSelective(parentOrder); //新增

//                增加支付记录
                ParentPay parentPay = new ParentPay();
                parentPay.setPkOrder(parentOrder.getPkOrder());
                parentPay.setCost(num.subtract(parentOrder.getCost()));
                parentPay.setPkPaymentRecord(GuidUtils.getGuid());
                parentPay.setSetMealId(parentOrder.getSetMealId());
                parentPay.setPkStudent(parentOrder.getPkStudent());
                parentPay.setPkLinkman(parentOrder.getPkLinkman());
                parentPay.setIsvalid(0);
                parentPay.setPaymentMethod("ICBC_PAY");
                parentPay.setPaymentDate(new Date());
                parentPay.setCreationDate(new Date());
                parentPay.setLasteditDate(new Date());
                parentPayMapper.insertSelective(parentPay);
            } else {
                SetMeal oldSetMeal = setMealService.findOnlySetMealById(oldParentOrder.getSetMealId());
                BigDecimal fee = oldSetMeal.getPrice().subtract(newsetMeal.getPrice());
                //修改
                parentOrder.setCost(newsetMeal.getPrice());
                parentOrder.setIsvalid(2);
                parentOrder.setLasteditDate(new Date());
                parentOrder.setOrderDate(new Date());
                if(oldParentOrder.getIspay() == 1){
//                    parentOrder.setIspay(0);

                    ParentPay parentPay = new ParentPay();
                    parentPay.setPkOrder(oldParentOrder.getPkOrder());
                    parentPay.setCost(fee);
                    parentPay.setPkPaymentRecord(GuidUtils.getGuid());
                    parentPay.setSetMealId(parentOrder.getSetMealId());
                    parentPay.setPkStudent(oldParentOrder.getPkStudent());
                    parentPay.setPkLinkman(oldParentOrder.getPkLinkman());
                    parentPay.setIsvalid(0);
                    parentPay.setPaymentMethod("ICBC_PAY");
                    parentPay.setPaymentDate(new Date());
                    parentPay.setCreationDate(new Date());
                    parentPay.setLasteditDate(new Date());
                    parentPayMapper.insertSelective(parentPay);

                }else{
                    //                    修改支付表
                    ParentPay parentPay = new ParentPay();
                    parentPay.setPkOrder(oldParentOrder.getPkOrder());
                    parentPay.setIsvalid(0);
                    List<ParentPay> select = parentPayMapper.select(parentPay);
                    if (select.size() > 0) {
                        select.get(0).setCost(num.subtract(newsetMeal.getPrice()));
                        parentPayMapper.updateByPrimaryKeySelective(select.get(0));
                    }
                }
                parentOrderMapper.updateByPrimaryKeySelective(parentOrder);

                //保存订单修改表
                ParentOrderEdit parentOrderEdit = new ParentOrderEdit();
                parentOrderEdit.setParentOrder(oldParentOrder.getPkOrder());
                parentOrderEdit.setPkStudent(parentOrder.getPkStudent());
                parentOrderEdit.setPkLinkman(parentOrder.getPkLinkman());
                parentOrderEdit.setOldCost(oldSetMeal.getPrice());
                parentOrderEdit.setCost(newsetMeal.getPrice());
                parentOrderEdit.setOldMealId(oldSetMeal.getPkSetMeal());
                parentOrderEdit.setNewMealId(newsetMeal.getPkSetMeal());
                Request<ParentOrderEdit> request = new Request();
                request.setData(parentOrderEdit);
                parentOrderEditService.save(request);

//                修改支付记录
            }
        } catch (Exception ex) {
            if(ex instanceof DuplicateKeyException) {
                message = "不可重复点餐";
            }else{
                message = ex.getMessage();
            }
            return response.SAVE_FAIL(message);
        }
        return response.SUCCESS();
    }

    private String insertSelective(ParentOrder parentOrder) {
        String uid = GuidUtils.getGuid();
        parentOrder.setPkOrder(GuidUtils.getGuid());
        parentOrder.setCreationDate(new Date());
        parentOrder.setLasteditDate(new Date());
        parentOrder.setIsvalid(1);

        if (null == parentOrder.getOrderDate()) {
            parentOrder.setOrderDate(new Date());
        }
        if (null == parentOrder.getIspay()) {
            //未支付
            parentOrder.setIspay(0);
        }
        if (null == parentOrder.getType()) {
            //默认早餐
            parentOrder.setType(0);
        }

        parentOrderMapper.insertSelective(parentOrder);
        return uid;
    }

    /**
     * 今日套餐
     *
     * @param parentOrder
     * @return
     */
    @Override
    public ParentOrder findToChoose(ParentOrder parentOrder) {
        ParentOrder select = parentOrderMapper.selectByStudent(parentOrder);
        return select;
    }

    /**
     * 点餐列表
     *
     * @param parentOrder
     * @return
     */
    @Override
    public Map<String, List<ParentOrder>> findChooseList(ParentOrder parentOrder) {

        List<ParentOrder> list = parentOrderMapper.select(parentOrder);
        List<ParentOrder> breakfastList = new ArrayList<>();//早餐
        List<ParentOrder> lunchList = new ArrayList<>();//午餐
        List<ParentOrder> dinnerList = new ArrayList<>();//晚餐
        if (list.size() > 0) {
            for (ParentOrder order : list) {
                SetMeal setMeal = setMealMapper.selectByPrimaryKey(order.getSetMealId());
                if (setMeal != null) {
                    order.put(LinkEntity.TKSETMEAL_ENTITY, setMeal);
                }else {
                    order.put(LinkEntity.TKSETMEAL_ENTITY, new SetMeal());
                }
                if (order.getType() == 0) {
                    breakfastList.add(order);
                } else if (order.getType() == 1) {
                    lunchList.add(order);
                } else if (order.getType() == 2) {
                    dinnerList.add(order);
                }
            }
        }

        Map<String, List<ParentOrder>> map = new HashMap<>();
        map.put("breakfastList", breakfastList);
        map.put("lunchList", lunchList);
        map.put("dinnerList", dinnerList);

        return map;
    }

    @Override
    public Map<String, List<ParentOrder>> findForCanteen(Map map) {
        List<ParentOrder> breakfastList = new ArrayList<>();//早餐
        List<ParentOrder> lunchList = new ArrayList<>();//午餐
        List<ParentOrder> dinnerList = new ArrayList<>();//晚餐

        List<ParentOrder> select = parentOrderMapper.selectForCanteen(map);

        if (select.size() > 0) {
            for (ParentOrder parentOrder : select) {
                if (parentOrder.getType() == 0) {
                    breakfastList.add(parentOrder);
                } else if (parentOrder.getType() == 1) {
                    lunchList.add(parentOrder);
                } else if (parentOrder.getType() == 2) {
                    dinnerList.add(parentOrder);
                }
            }
        }
        Map<String, List<ParentOrder>> resultMap = new HashMap<>();
        resultMap.put("breakfastList", breakfastList);
        resultMap.put("lunchList", lunchList);
        resultMap.put("dinnerList", dinnerList);

        return resultMap;

    }

    @Override
    public List<MealCount> selectByType(Map map) {

        List<MealCount> list = parentOrderMapper.selectByType(map);

        if (list.size() > 0) {
            for (MealCount mealCount : list) {
                SetMeal setMeal = setMealMapper.selectByPrimaryKey(mealCount.getSetMealId());
                mealCount.put("setMeal", setMeal);
            }
        }

        return list;
    }

    @Override
    public boolean updateOrderStatusById(String id) {
        ParentOrder parentOrder = new ParentOrder();
        parentOrder.setPkOrder(id);
        parentOrder.setIspay(1);
        parentOrder.setLasteditDate(new Date());
        int count = parentOrderMapper.updateByPrimaryKeySelective(parentOrder);
        return count > 0;
    }

    @Override
    public BigDecimal getParentOrderPaySum(String pkStudent) {
        //查出的钱应属于学生，不属于家长
        BigDecimal paySum = parentOrderMapper.getParentOrderPaySum(pkStudent);
        return null == paySum ? new BigDecimal(0) : paySum;
    }

    @Override
    public Response<List<ParentOrder>> getParentOrderListByIds(List<String> ids) {
        Response<List<ParentOrder>> response = Response.newResponse();
        if (CollectionUtils.isEmpty(ids)) {
            return response.PARAMS_ISNULL();
        }

        List<ParentOrder> orderList = parentOrderMapper.getParentOrderListByIds(ids);

        List<String> pkSetMeals = new ArrayList<>(orderList.size());
        for (ParentOrder order : orderList) {
            pkSetMeals.add(order.getSetMealId());
        }

        Map<String, SetMeal> setMealMap = setMealService.getMealIdANdSetMealMap(pkSetMeals);
        for (ParentOrder order : orderList) {
            order.setSetMeal(setMealMap.get(order.getSetMealId()));
        }
        response.setData(orderList);
        return response;
    }

    @Override
    public Response getNonPaymentOrderMoneySum(Request<String> request) {
        Response response = Response.newResponse();
        String pkStudent = request.getData();
        if (null == pkStudent || "".equals(pkStudent)) {
            return response.PARAMS_ISNULL();
        }
        BigDecimal moneySum = parentOrderMapper.getNonPaymentOrderMoneySum(pkStudent);
        response.setData(moneySum);
        return response;
    }

    @Override
    public void batchSaveOrUpdateParentOrder(List<ParentOrder> parentOrders) {
        for (ParentOrder parentOrder : parentOrders) {
            parentOrderMapper.insertSelective(parentOrder);
        }
    }
}
