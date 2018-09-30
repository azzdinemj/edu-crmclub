package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.NutritionalAnalysisDetails;

import java.util.List;

public interface NutritionalAnalysisDetailsMapper extends IInsertMapper<NutritionalAnalysisDetails>,ICountMapper<NutritionalAnalysisDetails,Integer>,
        IUpdateMapper<NutritionalAnalysisDetails>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,NutritionalAnalysisDetails>,ISelectMapper<NutritionalAnalysisDetails,List<NutritionalAnalysisDetails>>{
}