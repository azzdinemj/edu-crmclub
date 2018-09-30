package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentAllergy;

import java.util.List;

public interface StudentAllergyMapper  extends IInsertMapper<StudentAllergy>, ICountMapper<StudentAllergy,Integer>,
        IUpdateMapper<StudentAllergy>, IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentAllergy>,ISelectMapper<StudentAllergy, List<StudentAllergy>>{
}