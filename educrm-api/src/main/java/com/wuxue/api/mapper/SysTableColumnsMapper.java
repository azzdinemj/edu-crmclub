package com.wuxue.api.mapper;

import com.wuxue.model.SysTableColumns;

import java.util.List;

public interface SysTableColumnsMapper {
    int deleteByPrimaryKey(String pkSysTableColumns);

    int insert(SysTableColumns record);

    int insertSelective(SysTableColumns record);

    SysTableColumns selectByPrimaryKey(String pkSysTableColumns);

    int updateByPrimaryKeySelective(SysTableColumns record);

    int updateByPrimaryKey(SysTableColumns record);

    List<SysTableColumns> selectByParentKey(String pkSysTableColumn);
}