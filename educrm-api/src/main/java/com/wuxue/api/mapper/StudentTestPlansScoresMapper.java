package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentTestPlansScores;

import java.util.List;

public interface StudentTestPlansScoresMapper extends IInsertMapper<StudentTestPlansScores>,ICountMapper<StudentTestPlansScores,Integer>,
        IUpdateMapper<StudentTestPlansScores>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentTestPlansScores>,ISelectMapper<StudentTestPlansScores,List<StudentTestPlansScores>> {
}