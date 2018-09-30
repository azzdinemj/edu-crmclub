package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;

import java.util.List;

public interface ClassinfoActivityDetailsMapper extends IInsertMapper<ClassinfoActivityDetails>,ICountMapper<ClassinfoActivityDetails,Integer>,
        IUpdateMapper<ClassinfoActivityDetails>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ClassinfoActivityDetails>,ISelectMapper<ClassinfoActivityDetails,List<ClassinfoActivityDetails>> {

}