package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.HyPay;

import java.util.List;

public interface HyPayMapper  extends IInsertMapper<HyPay>,ICountMapper<HyPay,Integer>,
        IUpdateMapper<HyPay>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,HyPay>,ISelectMapper<HyPay,List<HyPay>> {

}