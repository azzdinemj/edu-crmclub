package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ExpenseItem;

import java.util.List;

public interface ExpenseItemMapper extends IInsertMapper<ExpenseItem>,ICountMapper<ExpenseItem,Integer>,
        IUpdateMapper<ExpenseItem>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ExpenseItem>,ISelectMapper<ExpenseItem,List<ExpenseItem>> {
}