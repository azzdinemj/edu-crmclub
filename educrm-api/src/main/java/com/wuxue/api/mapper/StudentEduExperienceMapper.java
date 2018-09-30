package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentEduExperience;

import java.util.List;

public interface StudentEduExperienceMapper extends IInsertMapper<StudentEduExperience>,ICountMapper<StudentEduExperience,Integer>,
        IUpdateMapper<StudentEduExperience>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentEduExperience>,ISelectMapper<StudentEduExperience,List<StudentEduExperience>> {
}