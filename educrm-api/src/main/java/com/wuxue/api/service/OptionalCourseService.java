package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.OptionalCourse;

public interface OptionalCourseService extends ISaveService<OptionalCourse>,IFindService<OptionalCourse>,IDeleteService<OptionalCourse> {
}
