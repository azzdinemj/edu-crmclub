package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TkProductMark;

import java.util.List;

public interface TkProductMarkMapper extends IInsertMapper<TkProductMark>,ICountMapper<TkProductMark,Integer>,
        IUpdateMapper<TkProductMark>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,TkProductMark>,ISelectMapper<TkProductMark,List<TkProductMark>>{

}