package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.ClassinfoStudentReport;

import java.util.List;

public interface ClassinfoStudentReportMapper extends IInsertMapper<ClassinfoStudentReport>,ISelectMapper<ClassinfoStudentReport,List<ClassinfoStudentReport>>,ICountMapper<ClassinfoStudentReport,Integer> {
}