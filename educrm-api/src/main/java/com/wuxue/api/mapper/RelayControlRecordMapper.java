package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.RelayControlRecord;

import java.util.List;

public interface RelayControlRecordMapper extends IInsertMapper<RelayControlRecord>,ICountMapper<RelayControlRecord,Integer>,
        IUpdateMapper<RelayControlRecord>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,RelayControlRecord>,ISelectMapper<RelayControlRecord,List<RelayControlRecord>>{
}