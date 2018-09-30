package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.JhAnswer;

import java.util.List;

public interface JhAnswerMapper extends IInsertMapper<JhAnswer>,ICountMapper<JhAnswer,Integer>,
        IUpdateMapper<JhAnswer>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,JhAnswer>,ISelectMapper<JhAnswer,List<JhAnswer>> {

    /**
     * 根据题目和选项筛选用户列表
     * */
    List<JhAnswer> selectAnswerList(JhAnswer jhAnswer);
}