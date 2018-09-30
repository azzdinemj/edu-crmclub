package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ExpenseItem;

public interface ExpenseItemService extends ISaveService<ExpenseItem>,IFindService<ExpenseItem>,IDeleteService<String> {
}
