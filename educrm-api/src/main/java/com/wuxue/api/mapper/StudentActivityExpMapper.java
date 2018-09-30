package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentActivityExp;

import java.util.List;

public interface StudentActivityExpMapper extends IInsertMapper<StudentActivityExp>,ICountMapper<StudentActivityExp,Integer>,
        IUpdateMapper<StudentActivityExp>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentActivityExp>,ISelectMapper<StudentActivityExp,List<StudentActivityExp>> {
}