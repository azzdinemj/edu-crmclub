package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.MealCount;
import com.wuxue.model.ParentOrder;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ParentOrderMapper extends IInsertMapper<ParentOrder>,ICountMapper<ParentOrder,Integer>,
        IUpdateMapper<ParentOrder>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ParentOrder>,ISelectMapper<ParentOrder,List<ParentOrder>>{
    ParentOrder selectByStudent(ParentOrder parentOrder);
    List<ParentOrder> selectForCanteen(Map map);

    List<MealCount> selectByType(Map map);

    /**
     * 通过时间查询学生用餐
     * @author tly
     * @param parentOrder
     * @return
     */
    List<ParentOrder> selectByCondition(ParentOrder parentOrder);

    /**
     * 条件查询
     * @author tly
     * @param parentOrder
     * @return
     */
    List<ParentOrder> selectAllInfoByStudentId(ParentOrder parentOrder);

    /**
     * 通过开始时间结束时间获取学生就餐原料含量总和
     * @author tly
     * @param parentOrder
     * @return
     */
   List<ResultEntity> getMaterialIdAndGramCountList(ParentOrder parentOrder);

    /**
     * 获取没有点餐的家长列表
     * */
    List<ParentOrder> selectNotOrderList(ParentOrder parentOrder);

    /**
     * 获取选餐记录中已经被使用的现金和
     * @return
     */
    BigDecimal getParentOrderPaySum(String pkStudent);

    /**
     * 通过主键获取订单信息
     * @param ids
     * @return
     */
    List<ParentOrder> getParentOrderListByIds(@Param("ids") List<String> ids);

    /**
     * 获取所有未支付订单金额总和
     * @author tly
     * @param pkStudent
     * @return
     */
    BigDecimal getNonPaymentOrderMoneySum(String pkStudent);

    /**
     * 根据学生id计算选餐总统计数
     * @param pkStudent
     * @return
     */
    List<ResultEntity> selectMealStatisticsByPkStudent(String pkStudent);

    List<ParentOrder> selectForRecord(ParentOrder parentOrder);
}