package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.SetMealNutrition;

import java.util.List;

public interface SetMealNutritionMapper extends IInsertMapper<SetMealNutrition>,ICountMapper<SetMealNutrition,Integer>,
        IUpdateMapper<SetMealNutrition>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SetMealNutrition>,ISelectMapper<SetMealNutrition,List<SetMealNutrition>>{
}