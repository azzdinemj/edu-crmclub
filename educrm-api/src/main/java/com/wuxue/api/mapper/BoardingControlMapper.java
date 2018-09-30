package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.BoardingControl;

import java.util.List;

public interface BoardingControlMapper extends IInsertMapper<BoardingControl>,ICountMapper<BoardingControl,Integer>,
        IUpdateMapper<BoardingControl>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,BoardingControl>,ISelectMapper<BoardingControl,List<BoardingControl>>{
}