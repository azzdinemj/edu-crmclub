package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindByParentService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SysDict;

public interface SysDictService extends ISaveService<SysDict>,IFindService<SysDict>,IDeleteService<String>{
}
