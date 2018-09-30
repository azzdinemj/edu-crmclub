package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.junhwa.DayStudent;

import java.util.List;

public interface DayStudentMapper  extends IInsertMapper<DayStudent>,ICountMapper<DayStudent,Integer>,
        IUpdateMapper<DayStudent>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,DayStudent>,ISelectMapper<DayStudent,List<DayStudent>>{
    DayStudent selectByUnique(DayStudent dayStudent);
}