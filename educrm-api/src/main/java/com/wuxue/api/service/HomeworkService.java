package com.wuxue.api.service;


import com.wuxue.base.KeyValue;
import com.wuxue.model.Homework;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface HomeworkService {
    Response findStuWork(String pkStudent,String workType);

    Response findBykey(String pkHomework);

    Response save(Homework homework,List<String> pkClassinfos);


    List<Homework> findHomeworkByTea(Homework homework, String jobPost,String pkEmployee);
}
