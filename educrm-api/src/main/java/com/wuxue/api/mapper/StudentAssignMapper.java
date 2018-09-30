package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentAssign;

import java.util.List;

public interface StudentAssignMapper extends IInsertMapper<StudentAssign>,ICountMapper<StudentAssign,Integer>,
        IUpdateMapper<StudentAssign>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentAssign>,ISelectMapper<StudentAssign,List<StudentAssign>> {
}