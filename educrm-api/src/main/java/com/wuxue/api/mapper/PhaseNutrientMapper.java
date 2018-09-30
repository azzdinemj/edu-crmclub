package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.PhaseNutrient;

import java.util.List;

public interface PhaseNutrientMapper extends IInsertMapper<PhaseNutrient>,ICountMapper<PhaseNutrient,Integer>,
        IUpdateMapper<PhaseNutrient>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<Integer,PhaseNutrient>,ISelectMapper<PhaseNutrient,List<PhaseNutrient>>{
}