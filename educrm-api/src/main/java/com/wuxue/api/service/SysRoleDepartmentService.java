package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SysRoleDepartmentKey;
import com.wuxue.model.SysRoleDepartment;

public interface SysRoleDepartmentService extends ISaveService<SysRoleDepartment>,IFindService<SysRoleDepartment>,IDeleteService<SysRoleDepartmentKey> {
}