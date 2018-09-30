package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.LookAnalysis;

import java.util.List;

public interface LookAnalysisMapper extends IInsertMapper<LookAnalysis>,ICountMapper<LookAnalysis,Integer>,
        IUpdateMapper<LookAnalysis>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,LookAnalysis>,ISelectMapper<LookAnalysis,List<LookAnalysis>> {
}