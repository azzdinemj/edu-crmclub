package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends IInsertMapper<SysUser>,ICountMapper<SysUser,Integer>,
        IUpdateMapper<SysUser>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SysUser>,ISelectMapper<SysUser,List<SysUser>> {
}