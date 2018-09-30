package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.EmployeeJobExp;

import java.util.List;

public interface EmployeeJobExpMapper extends IInsertMapper<EmployeeJobExp>,ICountMapper<EmployeeJobExp,Integer>,
        IUpdateMapper<EmployeeJobExp>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,EmployeeJobExp>,ISelectMapper<EmployeeJobExp,List<EmployeeJobExp>> {
}