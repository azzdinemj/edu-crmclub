package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.DivisionGrade;

import java.util.List;

public interface DivisionGradeMapper extends IInsertMapper<DivisionGrade>,ICountMapper<DivisionGrade,Integer>,
        IUpdateMapper<DivisionGrade>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,DivisionGrade>,ISelectMapper<DivisionGrade,List<DivisionGrade>> {
    
}