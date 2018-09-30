package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.TkLearnRecords;

public interface TkLearnRecordsService extends ISaveService<TkLearnRecords>,IFindService<TkLearnRecords>,IDeleteService<TkLearnRecords> {

}
