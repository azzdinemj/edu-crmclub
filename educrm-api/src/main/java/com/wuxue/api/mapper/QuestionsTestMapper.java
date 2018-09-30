package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsTest;
import java.util.List;

public interface QuestionsTestMapper extends ISelectByPrimaryKeyMapper<String,QuestionsTest>
        ,ISelectMapper<QuestionsTest,List<QuestionsTest>>,IInsertMapper<QuestionsTest>
        ,IUpdateMapper<QuestionsTest>,ICountMapper<QuestionsTest,Integer> {

}

