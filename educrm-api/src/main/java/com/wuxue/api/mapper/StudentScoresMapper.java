package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentScores;

import java.util.List;

public interface StudentScoresMapper extends IInsertMapper<StudentScores>,ICountMapper<StudentScores,Integer>,
        IUpdateMapper<StudentScores>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentScores>,ISelectMapper<StudentScores,List<StudentScores>> {

    List<StudentScores> selectCourseByStu(StudentScores studentScores);
    StudentScores selectByCourseAndPalns(StudentScores studentScores);

}