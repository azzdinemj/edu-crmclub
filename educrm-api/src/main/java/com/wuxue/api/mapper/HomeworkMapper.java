package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Homework;

import java.util.List;
import java.util.Map;

public interface HomeworkMapper extends IInsertMapper<Homework>,ICountMapper<Homework,Integer>,
        IUpdateMapper<Homework>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Homework>,ISelectMapper<Homework,List<Homework>> {

    List<Homework> selectStuWork(Map map);

    List<Homework> selectByHeadTea(Homework homework);

    List<Homework> selectByTea(Homework homework);
}