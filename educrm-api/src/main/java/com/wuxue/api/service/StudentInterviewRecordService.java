package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentInterviewRecord;

public interface StudentInterviewRecordService extends ISaveService<StudentInterviewRecord>,IFindService<StudentInterviewRecord>,IDeleteService<String> {
}
