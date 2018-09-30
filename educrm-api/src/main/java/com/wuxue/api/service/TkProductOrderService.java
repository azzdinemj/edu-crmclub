package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.TkProductOrder;

public interface TkProductOrderService extends ISaveService<TkProductOrder>,IFindService<TkProductOrder>,IDeleteService<TkProductOrder> {

}
