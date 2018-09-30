package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentTestPlans;

public interface StudentTestPlansService extends ISaveService<StudentTestPlans>,IFindService<StudentTestPlans>,IDeleteService<String> {
}
