package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.TurnRecorder;

import java.util.List;

public interface TurnRecorderMapper extends IInsertMapper<TurnRecorder>,ICountMapper<TurnRecorder,Integer>,
        IUpdateMapper<TurnRecorder>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,TurnRecorder>,ISelectMapper<TurnRecorder,List<TurnRecorder>>{
}