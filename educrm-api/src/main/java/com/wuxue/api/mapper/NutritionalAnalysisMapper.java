package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.NutritionalAnalysis;

import java.util.List;

public interface NutritionalAnalysisMapper extends IInsertMapper<NutritionalAnalysis>,ICountMapper<NutritionalAnalysis,Integer>,
        IUpdateMapper<NutritionalAnalysis>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,NutritionalAnalysis>,ISelectMapper<NutritionalAnalysis,List<NutritionalAnalysis>>{
}