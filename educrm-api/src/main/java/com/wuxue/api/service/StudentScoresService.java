package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.Receipt;
import com.wuxue.model.Student;
import com.wuxue.model.StudentScores;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface StudentScoresService extends ISaveService<StudentScores>,IFindService<StudentScores>,IDeleteService<String> {
    /**
     * 驳回
     * */
    Response saveAll(Request<List<StudentScores>> listRequest);
    Response findSchoolReport(Request<Student> listRequest);
}
