package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentSignup;

import java.util.List;
import java.util.Map;

public interface StudentSignupMapper extends IInsertMapper<StudentSignup>,ICountMapper<StudentSignup,Integer>,
        IUpdateMapper<StudentSignup>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentSignup>,ISelectMapper<StudentSignup,List<StudentSignup>> {

    int updateByPrimaryKey(StudentSignup signup);

    List<StudentSignup> getNotPlacement(StudentSignup studentSignup);

    Map<String,Object> selectForFrop(StudentSignup studentSignup);
}