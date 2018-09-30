package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.OptionalCourse;

import java.util.List;

public interface OptionalCourseMapper extends IInsertMapper<OptionalCourse>,ICountMapper<OptionalCourse,Integer>,
        IUpdateMapper<OptionalCourse>,IDeleteByPrimaryKeyMapper<OptionalCourse>,
        ISelectByPrimaryKeyMapper<String,OptionalCourse>,ISelectMapper<OptionalCourse,List<OptionalCourse>>{

}