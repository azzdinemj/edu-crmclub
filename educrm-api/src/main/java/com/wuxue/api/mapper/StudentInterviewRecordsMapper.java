package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentInterviewRecords;

import java.util.List;

public interface StudentInterviewRecordsMapper extends IInsertMapper<StudentInterviewRecords>,ICountMapper<StudentInterviewRecords,Integer>,
        IUpdateMapper<StudentInterviewRecords>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentInterviewRecords>,ISelectMapper<StudentInterviewRecords,List<StudentInterviewRecords>> {
}
