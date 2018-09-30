package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.DormRoomHousemaster;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DormRoomHousemasterMapper extends IInsertMapper<DormRoomHousemaster>,ICountMapper<DormRoomHousemaster,Integer>,
        IUpdateMapper<DormRoomHousemaster>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,DormRoomHousemaster>,ISelectMapper<DormRoomHousemaster,List<DormRoomHousemaster>> {
    String selectByDormRoom(String dormRoomId);

    int deleteByRoomId(String dormRoomId);
}