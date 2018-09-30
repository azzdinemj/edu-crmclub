package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.TeacherCourseReport;

import java.util.List;

public interface TeacherCourseReportMapper extends IInsertMapper<TeacherCourseReport>,ISelectMapper<TeacherCourseReport,List<TeacherCourseReport>>,ICountMapper<TeacherCourseReport,Integer> {

}