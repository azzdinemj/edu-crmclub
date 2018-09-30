package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.SchoolBusStudent;
import com.wuxue.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SchoolBusStudentMapper extends IInsertMapper<SchoolBusStudent>,ICountMapper<SchoolBusStudent,Integer>,
        IUpdateMapper<SchoolBusStudent>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SchoolBusStudent>,ISelectMapper<SchoolBusStudent,List<SchoolBusStudent>>{
    List<SchoolBusStudent> selectBySSPrimaryKey(SchoolBusStudent tParams);
    List<Student> selectStudent(String tParams);
}