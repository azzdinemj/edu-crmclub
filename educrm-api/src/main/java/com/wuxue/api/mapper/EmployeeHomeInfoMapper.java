package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.EmployeeHomeInfo;

import java.util.List;

public interface EmployeeHomeInfoMapper extends IInsertMapper<EmployeeHomeInfo>,ICountMapper<EmployeeHomeInfo,Integer>,
        IUpdateMapper<EmployeeHomeInfo>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,EmployeeHomeInfo>,ISelectMapper<EmployeeHomeInfo,List<EmployeeHomeInfo>> {
}