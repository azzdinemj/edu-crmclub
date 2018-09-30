package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SysSet;

public interface SysSetService extends ISaveService<SysSet>,IFindService<SysSet>,IDeleteService<SysSet> {
}
