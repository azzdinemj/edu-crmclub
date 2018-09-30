package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsPaperType;

import java.util.List;

public interface QuestionsPaperTypeMapper extends ISelectByPrimaryKeyMapper<String,QuestionsPaperType>
        ,ISelectMapper<QuestionsPaperType,List<QuestionsPaperType>>,IInsertMapper<QuestionsPaperType>
        ,IUpdateMapper<QuestionsPaperType>,ICountMapper<QuestionsPaperType,Integer> {

}