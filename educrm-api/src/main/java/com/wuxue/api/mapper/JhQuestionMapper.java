package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.JhQuestion;

import java.util.List;

public interface JhQuestionMapper extends IInsertMapper<JhQuestion>,ICountMapper<JhQuestion,Integer>,
        IUpdateMapper<JhQuestion>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,JhQuestion>,ISelectMapper<JhQuestion,List<JhQuestion>> {

    List<JhQuestion> selectByUser(JhQuestion jhQuestion);

    /**
     * 查询所有评论
     * */
    List<JhQuestion> selectOther(JhQuestion question);
}