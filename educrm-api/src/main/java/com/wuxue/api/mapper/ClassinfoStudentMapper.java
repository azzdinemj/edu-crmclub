package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.ClassinfoStudentKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassinfoStudentMapper extends IInsertMapper<ClassinfoStudent>,ICountMapper<ClassinfoStudent,Integer>,
        IUpdateMapper<ClassinfoStudent>,IDeleteByPrimaryKeyMapper<ClassinfoStudentKey>,
        ISelectByPrimaryKeyMapper<ClassinfoStudentKey,ClassinfoStudent>,ISelectMapper<ClassinfoStudent,List<ClassinfoStudent>> {
        List<ClassinfoStudent> selectPkClassinfo(ClassinfoStudent classinfoStudent);

        /**
         * 根据学生id找出学生所在正式班级的id
         * @param studentId 学生id
         * @return
         */
        String selectClassInfoIdByStudentId(@Param("studentId") String studentId);

        /**
         * 根據班級id集合找出班級下所有学生ID
         * @param ids
         * @return
         */
         List<String> selectStudentIdsByClassinfoIds(@Param("ids") List<String> ids);

    List<ClassinfoStudent> selectByStudent(String pkStudent);

    int selectCountByPkClassinfo(String pkClassinfo);

    int selectCoountBykey(ClassinfoStudent data);

    int updateByPkStudent(String pkStudent);
}