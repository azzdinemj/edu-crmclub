package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentTestPlans;

import java.util.List;

public interface StudentTestPlansMapper extends IInsertMapper<StudentTestPlans>,ICountMapper<StudentTestPlans,Integer>,
        IUpdateMapper<StudentTestPlans>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentTestPlans>,ISelectMapper<StudentTestPlans,List<StudentTestPlans>> {
}