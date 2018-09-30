package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.EmployeeJobExp;

public interface EmployeeJobExpService extends ISaveService<EmployeeJobExp>,IFindService<EmployeeJobExp>,IDeleteService<String> {
}
