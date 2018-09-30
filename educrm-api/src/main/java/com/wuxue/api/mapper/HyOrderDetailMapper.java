package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.HyOrderDetail;

import java.util.List;

public interface HyOrderDetailMapper  extends IInsertMapper<HyOrderDetail>,ICountMapper<HyOrderDetail,Integer>,
        IUpdateMapper<HyOrderDetail>,IDeleteByPrimaryKeyMapper<Integer>,
        ISelectByPrimaryKeyMapper<Integer,HyOrderDetail>,ISelectMapper<HyOrderDetail,List<HyOrderDetail>> {

}