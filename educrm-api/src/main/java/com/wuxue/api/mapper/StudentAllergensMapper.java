package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentAllergens;
import com.wuxue.model.StudentAllergensKey;

import java.util.List;

public interface StudentAllergensMapper  extends IInsertMapper<StudentAllergens>,ICountMapper<StudentAllergens,Integer>,
        IUpdateMapper<StudentAllergens>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentAllergens>,ISelectMapper<StudentAllergens,List<StudentAllergens>> {

    List<StudentAllergens> selectByStudent(String pkStudent);
}