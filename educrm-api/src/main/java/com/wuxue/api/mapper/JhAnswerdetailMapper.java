package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.JhAnswerdetail;

import java.util.List;

public interface JhAnswerdetailMapper extends IInsertMapper<JhAnswerdetail>,ICountMapper<JhAnswerdetail,Integer>,
        IUpdateMapper<JhAnswerdetail>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,JhAnswerdetail>,ISelectMapper<JhAnswerdetail,List<JhAnswerdetail>> {
}