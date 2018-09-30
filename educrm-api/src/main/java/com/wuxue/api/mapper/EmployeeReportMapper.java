package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.EmployeeReport;

import java.util.List;

public interface EmployeeReportMapper extends IInsertMapper<EmployeeReport>,ISelectMapper<EmployeeReport,List<EmployeeReport>>,ICountMapper<EmployeeReport,Integer> {
}