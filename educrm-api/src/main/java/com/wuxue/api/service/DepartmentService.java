package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Department;

public interface DepartmentService  extends ISaveService<Department>,IFindService<Department>,IDeleteService<String> {
}
