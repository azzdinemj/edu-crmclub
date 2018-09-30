package com.wuxue.api.service;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TkSetMeal;

public interface TkSetMealService extends ISaveService<TkSetMeal>,IFindService<TkSetMeal>,IDeleteService<String>,
        IAuditService<TkSetMeal>,ICancelService<TkSetMeal>,ISubmitService<TkSetMeal>{

}
