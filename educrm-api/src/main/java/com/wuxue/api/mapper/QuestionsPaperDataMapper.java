package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsPaperData;

import java.util.List;

public interface QuestionsPaperDataMapper extends ISelectByPrimaryKeyMapper<String,QuestionsPaperData>
        ,ISelectMapper<QuestionsPaperData,List<QuestionsPaperData>>,IInsertMapper<QuestionsPaperData>
        ,IUpdateMapper<QuestionsPaperData>,ICountMapper<QuestionsPaperData,Integer>  {

}