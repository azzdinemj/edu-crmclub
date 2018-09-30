package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.IInsertMapper;
import com.wuxue.api.interfaces.ISelectMapper;
import com.wuxue.model.DormRoomPerNum;

import java.util.List;

public interface DormRoomPerNumMapper extends IInsertMapper<DormRoomPerNum>,ISelectMapper<DormRoomPerNum,List<DormRoomPerNum>> {
}