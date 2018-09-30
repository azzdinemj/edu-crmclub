package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.CourseEvaluate;

import java.util.List;

public interface CourseEvaluateMapper  extends IInsertMapper<CourseEvaluate>,ICountMapper<CourseEvaluate,Integer>,
        IUpdateMapper<CourseEvaluate>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,CourseEvaluate>,ISelectMapper<CourseEvaluate,List<CourseEvaluate>>{
}