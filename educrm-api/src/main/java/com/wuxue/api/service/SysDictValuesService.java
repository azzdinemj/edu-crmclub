package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindByParentService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.base.KeyValue;
import com.wuxue.model.SysDictValues;

import java.util.List;

public interface SysDictValuesService extends ISaveService<SysDictValues>,IFindService<SysDictValues>,IDeleteService<String>,IFindByParentService<String>{
    List<SysDictValues> selectBySysDict(String pkSysDictValues);

    List<KeyValue> findWorkType(String pkStudent);
}
