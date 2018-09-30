package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.CanteenSetmeal;
import com.wuxue.model.MealCount;
import com.wuxue.model.ParentOrder;

public interface MealCountService extends ISaveService<MealCount>,IFindService<ParentOrder>,IDeleteService<String> {

}
