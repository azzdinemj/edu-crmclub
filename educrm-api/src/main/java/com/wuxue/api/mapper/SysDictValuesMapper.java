package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.base.KeyValue;
import com.wuxue.model.Employee;
import com.wuxue.model.SysDictValues;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictValuesMapper extends IInsertMapper<SysDictValues>,ICountMapper<SysDictValues,Integer>,
        IUpdateMapper<SysDictValues>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SysDictValues>,ISelectMapper<SysDictValues,List<SysDictValues>> {

    List<SysDictValues> selectCourse(Employee employee);

    List<SysDictValues> selectBySysDict(String pkSysDict);

    List<KeyValue> selectWorkType(String pkStudent);
}