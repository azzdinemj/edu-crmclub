package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.junhwa.MealMaterial;

import java.util.List;

public interface MealMaterialMapper extends IInsertMapper<MealMaterial>,ICountMapper<MealMaterial,Integer>,
        IUpdateMapper<MealMaterial>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,MealMaterial>,ISelectMapper<MealMaterial,List<MealMaterial>>  {
    /**
     * 删除套餐关联
     * */
    void deleteByMeal(String setMealId);
}