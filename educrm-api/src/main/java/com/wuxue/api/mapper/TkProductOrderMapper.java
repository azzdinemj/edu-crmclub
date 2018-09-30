package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TkProductOrder;

import java.util.List;

public interface TkProductOrderMapper extends IInsertMapper<TkProductOrder>,ICountMapper<TkProductOrder,Integer>,
        IUpdateMapper<TkProductOrder>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,TkProductOrder>,ISelectMapper<TkProductOrder,List<TkProductOrder>> {

}