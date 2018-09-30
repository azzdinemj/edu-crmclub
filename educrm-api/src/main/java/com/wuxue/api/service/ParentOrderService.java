package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.MealCount;
import com.wuxue.model.ParentOrder;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ParentOrderService extends ISaveService<ParentOrder>,IFindService<ParentOrder>,IDeleteService<String> {

    ParentOrder findToChoose(ParentOrder parentOrder);

    Map<String,List<ParentOrder>> findChooseList(ParentOrder parentOrder);

    Map<String,List<ParentOrder>> findForCanteen(Map map);

    List<MealCount> selectByType(Map map);

    Response selectForRecord(Request<ParentOrder> parentOrder);

    /**
     * 根据就餐id修改支付状态
     * @param id
     * @return
     */
    boolean updateOrderStatusById(String id);

    /**
     * 保存或者修改订单记录
     * @param parentOrder
     * @return
     */
    Response saveOrUpdateParentOrder(ParentOrder parentOrder);

    /**
     * 获取选餐记录中已经被使用的现金和
     * @param pkStudent
     * @return
     */
    BigDecimal getParentOrderPaySum(String pkStudent);

    /**
     * 通过主键获取订单信息
     * @param ids
     * @return
     */
    Response<List<ParentOrder>> getParentOrderListByIds(@Param("ids") List<String> ids);

    /**
     * 获取所有未支付订单金额总和
     * @param request
     * @return
     */
    Response getNonPaymentOrderMoneySum(Request<String> request);

    /**
     * 批量保存或者修改订单
     *
     * @param parentOrders
     */
    void batchSaveOrUpdateParentOrder(List<ParentOrder> parentOrders);
}
