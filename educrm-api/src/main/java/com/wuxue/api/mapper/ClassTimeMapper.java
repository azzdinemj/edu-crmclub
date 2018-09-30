package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassTime;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.utils.contract.Light;

import java.util.List;

public interface ClassTimeMapper extends IInsertMapper<ClassTime>,ICountMapper<ClassTime,Integer>,
        IUpdateMapper<ClassTime>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<ClassTime,ClassTime>,ISelectMapper<ClassTime,List<ClassTime>> {

    List<ClassTime> selectSort(ClassTime tParams);


    List<ClassTime> selectMaxSort(ClassTime classTime);
}