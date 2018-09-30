package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.SysAutocode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAutocodeMapper extends IInsertMapper<SysAutocode>,ICountMapper<SysAutocode,Integer>,
        IUpdateMapper<SysAutocode>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SysAutocode>,ISelectMapper<SysAutocode,List<SysAutocode>> {
}