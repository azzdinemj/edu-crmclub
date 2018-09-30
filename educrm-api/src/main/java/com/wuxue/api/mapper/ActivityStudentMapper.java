package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ActivityStudent;

import java.util.List;

public interface ActivityStudentMapper extends IInsertMapper<ActivityStudent>,ICountMapper<ActivityStudent,Integer>,
        IUpdateMapper<ActivityStudent>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ActivityStudent>,ISelectMapper<ActivityStudent,List<ActivityStudent>>{
}