package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Task;

import java.util.List;

public interface TaskMapper extends IInsertMapper<Task>,ICountMapper<Task,Integer>,
        IUpdateMapper<Task>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Task>,ISelectMapper<Task,List<Task>>{
}