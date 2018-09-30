package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.DropExpenseRecord;

public interface DropExpenseRecordService extends ISaveService<DropExpenseRecord>,IFindService<DropExpenseRecord>,IDeleteService<Long> {

}
