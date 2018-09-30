package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.SysUserRoleKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends IInsertMapper<SysUserRoleKey>,ICountMapper<SysUserRoleKey,Integer>,IDeleteByPrimaryKeyMapper<SysUserRoleKey>,ISelectMapper<SysUserRoleKey,List<SysUserRoleKey>> {
}