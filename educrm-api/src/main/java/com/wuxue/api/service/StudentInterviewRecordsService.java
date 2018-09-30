package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentInterviewRecords;

public interface StudentInterviewRecordsService extends ISaveService<StudentInterviewRecords>,IFindService<StudentInterviewRecords>,IDeleteService<String> {
}
