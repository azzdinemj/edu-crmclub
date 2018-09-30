package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.MarketReport;

import java.util.List;

public interface MarketReportMapper extends IInsertMapper<MarketReport>,ISelectMapper<MarketReport,List<MarketReport>>,ICountMapper<MarketReport,Integer> {
}