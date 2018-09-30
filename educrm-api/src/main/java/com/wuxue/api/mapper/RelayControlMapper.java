package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.RelayControl;

import java.util.List;

public interface RelayControlMapper extends IInsertMapper<RelayControl>,ICountMapper<RelayControl,Integer>,
        IUpdateMapper<RelayControl>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,RelayControl>,ISelectMapper<RelayControl,List<RelayControl>>{
}