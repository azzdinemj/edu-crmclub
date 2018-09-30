package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.SysPowerKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysPowerMapper extends IInsertMapper<SysPowerKey>,IDeleteByPrimaryKeyMapper<SysPowerKey>,ICountMapper<SysPowerKey,Integer>,ISelectMapper<SysPowerKey,List<SysPowerKey>> {
    SysPowerKey selectforURL(Map map);
}