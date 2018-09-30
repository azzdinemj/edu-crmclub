package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.AskLeaveStudent;

import java.util.List;

public interface AskLeaveStudentMapper extends IInsertMapper<AskLeaveStudent>,ICountMapper<AskLeaveStudent,Integer>,
        IUpdateMapper<AskLeaveStudent>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,AskLeaveStudent>,ISelectMapper<AskLeaveStudent,List<AskLeaveStudent>>{

    List<AskLeaveStudent> selectByPkAskForLeave(String pkAskForLeave);

    int deleteByLeaveId(String pkAskForLeave);
}