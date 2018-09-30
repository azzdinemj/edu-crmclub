package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.JhOption;

import java.util.List;

public interface JhOptionMapper extends IInsertMapper<JhOption>,ICountMapper<JhOption,Integer>,
        IUpdateMapper<JhOption>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,JhOption>,ISelectMapper<JhOption,List<JhOption>> {

    /**
     * 获取用户选项比例
     * */
    List<JhOption> statistics(JhOption jhOption);
}