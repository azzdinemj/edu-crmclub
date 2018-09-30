package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.SysDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictMapper  extends IInsertMapper<SysDict>,ICountMapper<SysDict,Integer>,
        IUpdateMapper<SysDict>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SysDict>,ISelectMapper<SysDict,List<SysDict>> {
}