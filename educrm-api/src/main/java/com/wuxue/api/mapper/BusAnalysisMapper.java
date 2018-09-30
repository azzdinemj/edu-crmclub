package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.BusAnalysis;

import java.util.List;

public interface BusAnalysisMapper extends IInsertMapper<BusAnalysis>,ICountMapper<BusAnalysis,Integer>,
        IUpdateMapper<BusAnalysis>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,BusAnalysis>,ISelectMapper<BusAnalysis,List<BusAnalysis>>{
}