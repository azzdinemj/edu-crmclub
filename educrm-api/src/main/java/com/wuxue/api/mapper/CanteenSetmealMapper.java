package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.CanteenSetmeal;

import java.util.List;

public interface CanteenSetmealMapper extends IInsertMapper<CanteenSetmeal>,ICountMapper<CanteenSetmeal,Integer>,
        IUpdateMapper<CanteenSetmeal>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,CanteenSetmeal>,ISelectMapper<CanteenSetmeal,List<CanteenSetmeal>> {

}