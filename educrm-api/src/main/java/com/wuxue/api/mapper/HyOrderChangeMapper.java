package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.HyOrderChange;

import java.util.List;

public interface HyOrderChangeMapper  extends IInsertMapper<HyOrderChange>,ICountMapper<HyOrderChange,Integer>,
        IUpdateMapper<HyOrderChange>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,HyOrderChange>,ISelectMapper<HyOrderChange,List<HyOrderChange>> {

}