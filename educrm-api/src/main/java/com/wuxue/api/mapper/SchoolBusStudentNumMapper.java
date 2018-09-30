package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.SchoolBusStudentNum;

import java.util.List;

public interface SchoolBusStudentNumMapper extends IInsertMapper<SchoolBusStudentNum>,
        ISelectMapper<SchoolBusStudentNum,List<SchoolBusStudentNum>> {
    int insert(SchoolBusStudentNum record);

}