package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsProject;

import java.util.List;

public interface QuestionsProjectMapper extends ISelectByPrimaryKeyMapper<String,QuestionsProject>
        ,ISelectMapper<QuestionsProject,List<QuestionsProject>>,IInsertMapper<QuestionsProject>
        ,IUpdateMapper<QuestionsProject>,ICountMapper<QuestionsProject,Integer> {

}