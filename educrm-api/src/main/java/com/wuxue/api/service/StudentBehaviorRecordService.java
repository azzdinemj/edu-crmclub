package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentBehaviorRecord;

public interface StudentBehaviorRecordService extends ISaveService<StudentBehaviorRecord>,IFindService<StudentBehaviorRecord>,IDeleteService<String> {
}
