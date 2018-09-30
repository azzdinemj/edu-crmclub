package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.CanteenSetmeal;

public interface CanteenSetmealService extends ISaveService<CanteenSetmeal>,IFindService<CanteenSetmeal>,IDeleteService<String> {

}
