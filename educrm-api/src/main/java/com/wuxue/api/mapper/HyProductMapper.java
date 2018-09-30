package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.HyProduct;

import java.util.List;

public interface HyProductMapper  extends IInsertMapper<HyProduct>,ICountMapper<HyProduct,Integer>,
        IUpdateMapper<HyProduct>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,HyProduct>,ISelectMapper<HyProduct,List<HyProduct>> {

}