package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.JhOption;


public interface JhOptionService extends ISaveService<JhOption>,IFindService<JhOption>,IDeleteService<JhOption> {

}
