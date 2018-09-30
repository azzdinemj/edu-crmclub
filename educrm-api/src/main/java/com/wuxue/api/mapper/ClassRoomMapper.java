package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassRoom;
import com.wuxue.model.ClassinfoActivityDetails;

import java.util.List;

public interface ClassRoomMapper extends IInsertMapper<ClassRoom>,ICountMapper<ClassRoom,Integer>,
        IUpdateMapper<ClassRoom>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,ClassRoom>,ISelectMapper<ClassRoom,List<ClassRoom>> {
}