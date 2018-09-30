package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TkSetMeal;

import java.util.List;

public interface TkSetMealMapper extends IInsertMapper<TkSetMeal>,ICountMapper<TkSetMeal,Integer>,
        IUpdateMapper<TkSetMeal>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,TkSetMeal>,ISelectMapper<TkSetMeal,List<TkSetMeal>>{

}