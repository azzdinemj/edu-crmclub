package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.Receivable;

import java.util.List;

public interface ReceivableMapper extends IInsertMapper<Receivable>,ICountMapper<Receivable,Integer>,
        IUpdateMapper<Receivable>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Receivable>,ISelectMapper<Receivable,List<Receivable>> {
    int updateoldReceivable(Receivable receivable);
}