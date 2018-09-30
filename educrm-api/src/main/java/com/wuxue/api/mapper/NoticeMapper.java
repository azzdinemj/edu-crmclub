package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Notice;

import java.util.List;

public interface NoticeMapper extends IInsertMapper<Notice>,ICountMapper<Notice,Integer>,
        IUpdateMapper<Notice>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Notice>,ISelectMapper<Notice,List<Notice>>{
}