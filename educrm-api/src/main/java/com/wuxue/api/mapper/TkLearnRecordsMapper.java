package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TkLearnRecords;

import java.util.List;

public interface TkLearnRecordsMapper extends IInsertMapper<TkLearnRecords>,ICountMapper<TkLearnRecords,Integer>,
        IUpdateMapper<TkLearnRecords>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,TkLearnRecords>,ISelectMapper<TkLearnRecords,List<TkLearnRecords>> {


}