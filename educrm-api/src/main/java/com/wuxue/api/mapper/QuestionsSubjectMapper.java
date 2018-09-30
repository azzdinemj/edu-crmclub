package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsSubject;

import java.util.List;

public interface QuestionsSubjectMapper extends ISelectByPrimaryKeyMapper<String,QuestionsSubject>
        ,ISelectMapper<QuestionsSubject,List<QuestionsSubject>>,IInsertMapper<QuestionsSubject>
        ,IUpdateMapper<QuestionsSubject>,ICountMapper<QuestionsSubject,Integer> {

}