package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.StudentCourseReport;

import java.util.List;

public interface StudentCourseReportMapper extends IInsertMapper<StudentCourseReport>,ISelectMapper<StudentCourseReport,List<StudentCourseReport>>,ICountMapper<StudentCourseReport,Integer> {

}