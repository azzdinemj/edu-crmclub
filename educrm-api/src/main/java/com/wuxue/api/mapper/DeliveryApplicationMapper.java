package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.DeliveryApplication;

import java.util.List;

public interface DeliveryApplicationMapper extends IInsertMapper<DeliveryApplication>,ICountMapper<DeliveryApplication,Integer>,
        IUpdateMapper<DeliveryApplication>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,DeliveryApplication>,ISelectMapper<DeliveryApplication,List<DeliveryApplication>>{
}