package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.junhwa.Holiday;

import java.util.List;

public interface HolidayMapper extends IInsertMapper<Holiday>,ICountMapper<Holiday,Integer>,
        IUpdateMapper<Holiday>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,Holiday>,ISelectMapper<Holiday,List<Holiday>>{
}