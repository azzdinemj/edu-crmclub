package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Recharge;

import java.util.List;

public interface RechargeMapper  extends IInsertMapper<Recharge>,ICountMapper<Recharge,Integer>,
        IUpdateMapper<Recharge>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<Integer,Recharge>,ISelectMapper<Recharge,List<Recharge>> {
}