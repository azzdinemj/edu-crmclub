package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SetMealNutrition;

public interface SetMealNutritionService extends ISaveService<SetMealNutrition>,IFindService<SetMealNutrition>,IDeleteService<String> {

}
