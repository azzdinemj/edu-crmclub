package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.SetMealMaterial;

import java.util.List;

public interface SetMealMaterialMapper extends IInsertMapper<SetMealMaterial>,ICountMapper<SetMealMaterial,Integer>,
        IUpdateMapper<SetMealMaterial>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SetMealMaterial>,ISelectMapper<SetMealMaterial,List<SetMealMaterial>>{
}