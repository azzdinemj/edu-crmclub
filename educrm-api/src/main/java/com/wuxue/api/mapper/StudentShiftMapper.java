package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentShift;

import java.util.List;

public interface StudentShiftMapper extends IInsertMapper<StudentShift>,ICountMapper<StudentShift,Integer>,
        IUpdateMapper<StudentShift>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,StudentShift>,ISelectMapper<StudentShift,List<StudentShift>> {
}