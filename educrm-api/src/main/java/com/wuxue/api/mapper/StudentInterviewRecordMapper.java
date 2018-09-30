package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentInterviewRecord;

import java.util.List;

public interface StudentInterviewRecordMapper extends IInsertMapper<StudentInterviewRecord>,ICountMapper<StudentInterviewRecord,Integer>,
        IUpdateMapper<StudentInterviewRecord>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentInterviewRecord>,ISelectMapper<StudentInterviewRecord,List<StudentInterviewRecord>> {
}