package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentSignupDetails;

import java.util.List;

public interface StudentSignupDetailsMapper extends IInsertMapper<StudentSignupDetails>,ICountMapper<StudentSignupDetails,Integer>,
        IUpdateMapper<StudentSignupDetails>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentSignupDetails>,ISelectMapper<StudentSignupDetails,List<StudentSignupDetails>>  {
}