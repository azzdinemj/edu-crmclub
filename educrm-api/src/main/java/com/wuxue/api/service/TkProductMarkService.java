package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.TkProductMark;

public interface TkProductMarkService extends ISaveService<TkProductMark>,IFindService<TkProductMark>,IDeleteService<TkProductMark> {

}
