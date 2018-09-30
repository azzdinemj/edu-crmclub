package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsOption;

import java.util.List;

public interface QuestionsOptionMapper  extends ISelectByPrimaryKeyMapper<String,QuestionsOption>
        ,ISelectMapper<QuestionsOption,List<QuestionsOption>>,IInsertMapper<QuestionsOption>
        ,IUpdateMapper<QuestionsOption> {

}