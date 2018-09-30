package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ParentOrderEdit;

public interface ParentOrderEditService extends ISaveService<ParentOrderEdit>,IFindService<ParentOrderEdit>,IDeleteService<String> {

}
