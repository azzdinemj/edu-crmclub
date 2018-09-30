package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.base.KeyValue;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoStudent;

import java.util.List;

public interface ClassinfoMapper extends IInsertMapper<Classinfo>,ICountMapper<Classinfo,Integer>,
        IUpdateMapper<Classinfo>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Classinfo>,ISelectMapper<Classinfo,List<Classinfo>> {
    List<Classinfo> selectClassinfoByEmployee(Classinfo classinfo);

    List<Classinfo> selectByType(Classinfo classinfo);

    /**
     * 根据老师id查询老师对应的班级列表
     * @param teacherId
     * @return
     */
    List<Classinfo> selectClassInfoListByTeacherId(String teacherId);

    /**
     * 根据学生id查询学生所在所有班级
     * @param studentId
     * @return
     */
    List<Classinfo> selectClassInfoIdListByStudentId(String studentId);

    int classOn();

    List<KeyValue> selectKeyValue(Classinfo data);

    String selectNotesByPrimaryKey(String pkClassinfo);

    int selectCoountBykey(ClassinfoStudent data);
}