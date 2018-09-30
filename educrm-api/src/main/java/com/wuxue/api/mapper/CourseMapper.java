package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper extends IInsertMapper<Course>,ICountMapper<Course,Integer>,
        IUpdateMapper<Course>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Course>,ISelectMapper<Course,List<Course>> {

}