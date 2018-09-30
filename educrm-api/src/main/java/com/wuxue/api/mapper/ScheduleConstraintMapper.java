package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ScheduleConstraint;

import java.util.List;

public interface ScheduleConstraintMapper extends IInsertMapper<ScheduleConstraint>,ICountMapper<ScheduleConstraint,Integer>,
        IUpdateMapper<ScheduleConstraint>,IDeleteByPrimaryKeyMapper<Long>,
        ISelectByPrimaryKeyMapper<Long,ScheduleConstraint>,ISelectMapper<ScheduleConstraint,List<ScheduleConstraint>> {
   
}