package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.SysAutocodeCounter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAutocodeCounterMapper  extends IInsertMapper<SysAutocodeCounter>,ICountMapper<SysAutocodeCounter,Integer>,
        IUpdateMapper<SysAutocodeCounter>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SysAutocodeCounter>,ISelectMapper<SysAutocodeCounter,List<SysAutocodeCounter>> {
}