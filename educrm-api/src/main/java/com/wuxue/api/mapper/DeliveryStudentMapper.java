package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.DeliveryStudent;

import java.util.List;

public interface DeliveryStudentMapper extends IInsertMapper<DeliveryStudent>,ICountMapper<DeliveryStudent,Integer>,
        IUpdateMapper<DeliveryStudent>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,DeliveryStudent>,ISelectMapper<DeliveryStudent,List<DeliveryStudent>>{
    List<DeliveryStudent> selectByPkDelivery(String pkDelivery);

}