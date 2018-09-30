package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.CourseTeacher;

import java.util.List;

public interface CourseTeacherMapper extends IInsertMapper<CourseTeacher>,ICountMapper<CourseTeacher,Integer>,
        IUpdateMapper<CourseTeacher>,IDeleteByPrimaryKeyMapper<CourseTeacher>,
        ISelectMapper<CourseTeacher,List<CourseTeacher>> {
    CourseTeacher selectByDoublePrimaryKey(CourseTeacher tParams);
//    selectByPrimary<String,CourseTeacher>
    List<CourseTeacher> selectByPrimary(String tParams);


    List<CourseTeacher> selectByEmp(String pkEmployee);

    List<CourseTeacher> selectByHeadEmp(String pkEmployee);
}