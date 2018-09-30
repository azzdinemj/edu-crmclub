package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentAwards;

import java.util.List;

public interface StudentAwardsMapper extends IInsertMapper<StudentAwards>,ICountMapper<StudentAwards,Integer>,
        IUpdateMapper<StudentAwards>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentAwards>,ISelectMapper<StudentAwards,List<StudentAwards>> {
}