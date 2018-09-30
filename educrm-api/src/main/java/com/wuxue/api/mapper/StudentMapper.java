package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper extends IInsertMapper<Student>,ICountMapper<Student,Integer>,
        IUpdateMapper<Student>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Student>,ISelectMapper<Student,List<Student>> {
    List<Student> selectStudentByEmployee(Student student);
    List<Student> selectarrearsStudent(Student student);
    int updateByPrimaryKey(Student tParams);
    List<Student> selectByParent(String pkLinkman);
    Student selectByPhone(String phone);
    int updateByPhone(Student student);

    /**
     * 根据班级主键和学生姓名查询学生列表
     * @param entity
     * @return
     */
    List<ResultEntity> selectStudentListByPkClassInfo(ResultEntity entity);

    /**
     * 根据学生id集合查询学生信息
     * @param ids
     * @return
     */
    List<ResultEntity> selectStudentInfoByBusIds(@Param("ids") List<String> ids);

    int insertSelectiveCollect(Student student);

    String selectStudentIdStartCode(String pkClassinfo);
    String selectStudentIdEndCode(String startCode);
}