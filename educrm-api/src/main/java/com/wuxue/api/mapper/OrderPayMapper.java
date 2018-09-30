package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.OrderPay;
import com.wuxue.model.ParentPay;

import java.util.List;

public interface OrderPayMapper  extends IInsertMapper<OrderPay>,ICountMapper<OrderPay,Integer>,
        IUpdateMapper<OrderPay>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,OrderPay>,ISelectMapper<OrderPay,List<OrderPay>> {
}