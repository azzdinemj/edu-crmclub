package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.MaterialNutrition;

import java.util.List;

public interface MaterialNutritionalMapper extends IInsertMapper<MaterialNutrition>,ICountMapper<MaterialNutrition,Integer>,
        IUpdateMapper<MaterialNutrition>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,MaterialNutrition>,ISelectMapper<MaterialNutrition,List<MaterialNutrition>>{
}