package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ParentOrderEdit;

import java.math.BigDecimal;
import java.util.List;

public interface ParentOrderEditMapper extends IInsertMapper<ParentOrderEdit>,ICountMapper<ParentOrderEdit,Integer>,
        IUpdateMapper<ParentOrderEdit>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ParentOrderEdit>,ISelectMapper<ParentOrderEdit,List<ParentOrderEdit>>{

    /**
     * 通过家长获取订单修改后金额总和
     * @param pkLinkman
     * @return
     */
    BigDecimal getNewOrderSumCost(String pkLinkman);
}