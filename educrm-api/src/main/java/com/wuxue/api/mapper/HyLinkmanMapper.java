package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.HyLinkman;

import java.util.List;

public interface HyLinkmanMapper extends IInsertMapper<HyLinkman>,ICountMapper<HyLinkman,Integer>,
        IUpdateMapper<HyLinkman>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,HyLinkman>,ISelectMapper<HyLinkman,List<HyLinkman>> {

}