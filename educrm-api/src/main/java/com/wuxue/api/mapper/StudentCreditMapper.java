package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentCredit;

import java.util.List;

public interface StudentCreditMapper extends IInsertMapper<StudentCredit>,ICountMapper<StudentCredit,Integer>,
        IUpdateMapper<StudentCredit>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentCredit>,ISelectMapper<StudentCredit,List<StudentCredit>> {
}