package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentBehaviorRecord;

import java.util.List;

public interface StudentBehaviorRecordMapper extends IInsertMapper<StudentBehaviorRecord>,ICountMapper<StudentBehaviorRecord,Integer>,
        IUpdateMapper<StudentBehaviorRecord>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentBehaviorRecord>,ISelectMapper<StudentBehaviorRecord,List<StudentBehaviorRecord>> {
}