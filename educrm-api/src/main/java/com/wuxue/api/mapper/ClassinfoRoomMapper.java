package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoRoom;

import java.util.List;

public interface ClassinfoRoomMapper extends IInsertMapper<ClassinfoRoom>,ICountMapper<ClassinfoRoom,Integer>,
        IUpdateMapper<ClassinfoRoom>,IDeleteByPrimaryKeyMapper<ClassinfoRoom>,
        ISelectByPrimaryKeyMapper<ClassinfoRoom,ClassinfoRoom>,ISelectMapper<ClassinfoRoom,List<ClassinfoRoom>>{

    List<ClassinfoRoom> selectBy(ClassinfoRoom classinfoRoom);

    ClassinfoRoom selectByUnique(ClassinfoRoom classinfoRoom);
}