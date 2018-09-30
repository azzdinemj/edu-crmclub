package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.DormRoomEmployee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DormRoomEmployeeMapper  extends IInsertMapper<DormRoomEmployee>,ICountMapper<DormRoomEmployee,Integer>,
        IUpdateMapper<DormRoomEmployee>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,DormRoomEmployee>,ISelectMapper<DormRoomEmployee,List<DormRoomEmployee>> {

    List<DormRoomEmployee> selectByREPrimaryKey(DormRoomEmployee tParams);
}