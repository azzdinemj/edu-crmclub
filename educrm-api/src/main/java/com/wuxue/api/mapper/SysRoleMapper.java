package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper extends IInsertMapper<SysRole>,ICountMapper<SysRole,Integer>,
        IUpdateMapper<SysRole>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SysRole>,ISelectMapper<SysRole,List<SysRole>> {
}