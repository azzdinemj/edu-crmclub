package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsPaperItem;


import java.util.List;

public interface QuestionsPaperItemMapper extends ISelectByPrimaryKeyMapper<String,QuestionsPaperItem>
        ,ISelectMapper<QuestionsPaperItem,List<QuestionsPaperItem>>,IInsertMapper<QuestionsPaperItem>
        ,IUpdateMapper<QuestionsPaperItem>,ICountMapper<QuestionsPaperItem,Integer> {

}