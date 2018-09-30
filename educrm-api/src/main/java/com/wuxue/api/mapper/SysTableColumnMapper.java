package com.wuxue.api.mapper;

import com.wuxue.model.SysTableColumn;

public interface SysTableColumnMapper {
    int deleteByPrimaryKey(String pkSysTableColumn);

    int insert(SysTableColumn record);

    int insertSelective(SysTableColumn record);

    SysTableColumn selectByPrimaryKey(String pkSysTableColumn);

    int updateByPrimaryKeySelective(SysTableColumn record);

    int updateByPrimaryKey(SysTableColumn record);
}