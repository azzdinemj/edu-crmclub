package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.Payment;

import java.util.List;

public interface PaymentMapper extends IInsertMapper<Payment>,ICountMapper<Payment,Integer>,
        IUpdateMapper<Payment>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Payment>,ISelectMapper<Payment,List<Payment>> {
    void updateByPrimaryKey(Payment byPrimaryKey);
}