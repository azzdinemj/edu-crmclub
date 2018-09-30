package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentActivityExp;

public interface StudentActivityExpService extends ISaveService<StudentActivityExp>,IFindService<StudentActivityExp>,IDeleteService<String> {
}
