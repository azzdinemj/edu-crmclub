package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.StudentStatusReport;

import java.util.List;

public interface StudentStatusReportMapper extends IInsertMapper<StudentStatusReport>,ISelectMapper<StudentStatusReport,List<StudentStatusReport>>,ICountMapper<StudentStatusReport,Integer> {

}