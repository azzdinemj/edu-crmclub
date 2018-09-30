package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentPhaseRecord;

import java.util.List;

public interface StudentPhaseRecordMapper extends IInsertMapper<StudentPhaseRecord>,ICountMapper<StudentPhaseRecord,Integer>,
        IUpdateMapper<StudentPhaseRecord>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentPhaseRecord>,ISelectMapper<StudentPhaseRecord,List<StudentPhaseRecord>>{

}