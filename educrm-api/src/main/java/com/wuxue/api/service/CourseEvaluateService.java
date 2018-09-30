package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.CourseEvaluate;

public interface CourseEvaluateService extends ISaveService<CourseEvaluate>,IFindService<CourseEvaluate>,IDeleteService<String> {
}
