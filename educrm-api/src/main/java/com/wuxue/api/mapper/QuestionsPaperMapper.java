package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsPaper;

import java.util.List;

public interface QuestionsPaperMapper extends ISelectByPrimaryKeyMapper<String,QuestionsPaper>
        ,ISelectMapper<QuestionsPaper,List<QuestionsPaper>>,IInsertMapper<QuestionsPaper>
        ,IUpdateMapper<QuestionsPaper>,ICountMapper<QuestionsPaper,Integer> {

}