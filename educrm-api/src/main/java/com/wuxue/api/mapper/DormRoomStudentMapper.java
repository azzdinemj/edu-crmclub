package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.DormRoomStudent;

import java.util.List;

public interface DormRoomStudentMapper extends IInsertMapper<DormRoomStudent>,ICountMapper<DormRoomStudent,Integer>,
        IUpdateMapper<DormRoomStudent>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,DormRoomStudent>,ISelectMapper<DormRoomStudent,List<DormRoomStudent>>{
    List<DormRoomStudent> selectByDSPrimaryKey(DormRoomStudent key);

}