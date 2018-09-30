package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentLesson;

import java.util.List;

public interface StudentLessonMapper extends IInsertMapper<StudentLesson>,ICountMapper<StudentLesson,Integer>,
        IUpdateMapper<StudentLesson>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentLesson>,ISelectMapper<StudentLesson,List<StudentLesson>> {
}