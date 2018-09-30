package com.wuxue.api.mapper;

import java.util.List;

import com.wuxue.api.interfaces.ICountMapper;
import com.wuxue.api.interfaces.IDeleteByPrimaryKeyMapper;
import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectByPrimaryKeyMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.api.interfaces.IUpdateMapper;
import com.wuxue.model.SysLog;

public interface SysLogMapper {
    int deleteByPrimaryKey(String pkSysLog);

    int insert(SysLog record);

    int insertSelective(SysLog record);
    List<SysLog> select(SysLog record);
    SysLog selectByPrimaryKey(String pkSysLog);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}