package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivity;

import java.util.List;

public interface ClassinfoActivityMapper extends IInsertMapper<ClassinfoActivity>,ICountMapper<ClassinfoActivity,Integer>,
        IUpdateMapper<ClassinfoActivity>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ClassinfoActivity>,ISelectMapper<ClassinfoActivity,List<ClassinfoActivity>> {
}