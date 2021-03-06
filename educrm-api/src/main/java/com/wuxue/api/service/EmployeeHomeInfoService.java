package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.EmployeeHomeInfo;

public interface EmployeeHomeInfoService extends ISaveService<EmployeeHomeInfo>,IFindService<EmployeeHomeInfo>,IDeleteService<String> {
}
