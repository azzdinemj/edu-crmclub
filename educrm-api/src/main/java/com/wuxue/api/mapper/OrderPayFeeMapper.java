package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.OrderPayFee;

import java.util.List;

public interface OrderPayFeeMapper  extends IInsertMapper<OrderPayFee>,ICountMapper<OrderPayFee,Integer>,
        IUpdateMapper<OrderPayFee>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,OrderPayFee>,ISelectMapper<OrderPayFee,List<OrderPayFee>>{

}