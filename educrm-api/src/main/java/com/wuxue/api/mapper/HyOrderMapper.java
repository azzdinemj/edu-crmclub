package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.HyOrder;

import java.util.List;

public interface HyOrderMapper  extends IInsertMapper<HyOrder>,ICountMapper<HyOrder,Integer>,
        IUpdateMapper<HyOrder>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,HyOrder>,ISelectMapper<HyOrder,List<HyOrder>> {

}