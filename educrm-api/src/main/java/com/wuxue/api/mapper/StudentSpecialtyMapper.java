package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.StudentSpecialty;

import java.util.List;

public interface StudentSpecialtyMapper extends IInsertMapper<StudentSpecialty>,ICountMapper<StudentSpecialty,Integer>,
        IUpdateMapper<StudentSpecialty>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentSpecialty>,ISelectMapper<StudentSpecialty,List<StudentSpecialty>> {
}