package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.MealCount;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.ParentPay;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ParentPayMapper extends IInsertMapper<ParentPay>,ICountMapper<ParentPay,Integer>,
        IUpdateMapper<ParentPay>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ParentPay>,ISelectMapper<ParentPay,List<ParentPay>>{

    List<MealCount> selectCount(ParentOrder parentOrder);

    List<ParentPay> selectByClassinfo(Map map);

    /**
     * 查询支付信息
     *
     * @author tly
     * @param parentPay
     * @return 支付记录集
     */
    List<ParentPay> selectParentPay(ParentPay parentPay);

    /**
     * 获取学生对应所有支付金额
     * @param studentId
     * @param paymentMethod
     * @return
     */
    BigDecimal getParentPaySum(@Param("studentId") String studentId, @Param("paymentMethod") String paymentMethod);

    /**
     * 获取支付记录列表
     * @return  支付记录列表
     */
    List<ResultEntity> selectPayRecordList(ResultEntity resultEntity);

    /**
     * 获取应付钱
     * */
    BigDecimal getCostSum(ParentPay parentPay);

    List<ResultEntity> selectPayRecord(ResultEntity resultEntity);
}