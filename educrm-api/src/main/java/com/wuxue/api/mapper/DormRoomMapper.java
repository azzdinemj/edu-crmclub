package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.DormRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DormRoomMapper extends IInsertMapper<DormRoom>,ICountMapper<DormRoom,Integer>,
        IUpdateMapper<DormRoom>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,DormRoom>,ISelectMapper<DormRoom,List<DormRoom>> {
}