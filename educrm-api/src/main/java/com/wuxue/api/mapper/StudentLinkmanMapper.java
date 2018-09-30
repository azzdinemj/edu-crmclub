package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentLinkmanKey;

import java.util.List;

public interface StudentLinkmanMapper  extends IInsertMapper<StudentLinkmanKey>,ICountMapper<StudentLinkmanKey,Integer>,
        IUpdateMapper<StudentLinkmanKey>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<StudentLinkmanKey,StudentLinkmanKey>,ISelectMapper<StudentLinkmanKey,List<StudentLinkmanKey>>{

        int deleteByStudent(String pkStudent);

    int insertSelectiveCollect(StudentLinkmanKey studentLinkmanKey);

    StudentLinkmanKey selectStuByParPhone(String phone);
}