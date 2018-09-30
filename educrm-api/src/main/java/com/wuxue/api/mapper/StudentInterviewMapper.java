package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentInterview;

import java.util.List;

public interface StudentInterviewMapper extends IInsertMapper<StudentInterview>,ICountMapper<StudentInterview,Integer>,
        IUpdateMapper<StudentInterview>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentInterview>,ISelectMapper<StudentInterview,List<StudentInterview>> {
}