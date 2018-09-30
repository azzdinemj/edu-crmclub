package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TkPayRecords;

import java.util.List;

public interface TkPayRecordsMapper extends IInsertMapper<TkPayRecords>,ICountMapper<TkPayRecords,Integer>,
        IUpdateMapper<TkPayRecords>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,TkPayRecords>,ISelectMapper<TkPayRecords,List<TkPayRecords>>{

}