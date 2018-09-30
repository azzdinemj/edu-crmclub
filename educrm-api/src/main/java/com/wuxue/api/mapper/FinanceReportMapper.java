package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.FinanceReport;

import java.util.List;

public interface FinanceReportMapper extends IInsertMapper<FinanceReport>,ISelectMapper<FinanceReport,List<FinanceReport>>,ICountMapper<FinanceReport,Integer> {
}