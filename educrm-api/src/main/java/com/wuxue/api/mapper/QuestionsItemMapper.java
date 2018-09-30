package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.QuestionsItemWithBLOBs;
import com.wuxue.model.QuestionsOption;
import com.wuxue.model.QuestionsSubject;

import java.util.List;

public interface QuestionsItemMapper extends ISelectByPrimaryKeyMapper<String,QuestionsItem>
        ,ISelectMapper<QuestionsItem,List<QuestionsItem>>,IInsertMapper<QuestionsItem>
        ,IUpdateMapper<QuestionsItem>,ICountMapper<QuestionsItem,Integer> {


}