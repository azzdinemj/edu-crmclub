package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentInterview;

public interface StudentInterviewService extends ISaveService<StudentInterview>,IFindService<StudentInterview>,IDeleteService<String>,
        IAuditService<StudentInterview>,ICancelService<StudentInterview>,ISubmitService<StudentInterview> {
}
 