package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SetMealMaterial;
import com.wuxue.model.junhwa.MealMaterial;

public interface MealMaterialService extends ISaveService<MealMaterial>,IFindService<MealMaterial>,IDeleteService<String> {

}
