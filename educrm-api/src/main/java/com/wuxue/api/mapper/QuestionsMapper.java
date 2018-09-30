package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Questions;

import java.util.List;

public interface QuestionsMapper extends IInsertMapper<Questions>,ICountMapper<Questions,Integer>,
        IUpdateMapper<Questions>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Questions>,ISelectMapper<Questions,List<Questions>>{
}