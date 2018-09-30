package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentPlans;

import java.util.List;

public interface StudentPlansMapper extends IInsertMapper<StudentPlans>,ICountMapper<StudentPlans,Integer>,
        IUpdateMapper<StudentPlans>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentPlans>,ISelectMapper<StudentPlans,List<StudentPlans>> {
}
