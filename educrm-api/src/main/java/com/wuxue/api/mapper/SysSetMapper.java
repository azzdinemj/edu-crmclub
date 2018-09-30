package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.SysSet;
import com.wuxue.model.SysSetKey;

import java.util.List;

public interface SysSetMapper extends IInsertMapper<SysSet>,ICountMapper<SysSet,Integer>,
        IUpdateMapper<SysSet>,IDeleteByPrimaryKeyMapper<SysSetKey>,
        ISelectByPrimaryKeyMapper<SysSetKey,SysSet>,ISelectMapper<SysSet,List<SysSet>> {
}