package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.EnrolmentPerformanceReport;

import java.util.List;

public interface EnrolmentPerformanceReportMapper extends IInsertMapper<EnrolmentPerformanceReport>,ISelectMapper<EnrolmentPerformanceReport,List<EnrolmentPerformanceReport>>,ICountMapper<EnrolmentPerformanceReport,Integer> {
}