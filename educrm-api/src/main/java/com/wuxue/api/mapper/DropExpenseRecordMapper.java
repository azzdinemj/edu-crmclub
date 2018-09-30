package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.DropExpenseRecord;

import java.util.List;

public interface DropExpenseRecordMapper extends IInsertMapper<DropExpenseRecord>,ICountMapper<DropExpenseRecord,Integer>,
        IUpdateMapper<DropExpenseRecord>,IDeleteByPrimaryKeyMapper<Long>,
        ISelectByPrimaryKeyMapper<Long,DropExpenseRecord>,ISelectMapper<DropExpenseRecord,List<DropExpenseRecord>>{
}