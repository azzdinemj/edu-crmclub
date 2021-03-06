package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentPlans;

public interface StudentPlansService extends ISaveService<StudentPlans>,IFindService<StudentPlans>,IDeleteService<String> {
}
