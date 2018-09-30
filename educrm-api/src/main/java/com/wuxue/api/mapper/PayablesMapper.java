package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.Payables;

import java.util.List;

public interface PayablesMapper extends IInsertMapper<Payables>,ICountMapper<Payables,Integer>,
        IUpdateMapper<Payables>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Payables>,ISelectMapper<Payables,List<Payables>> {
}