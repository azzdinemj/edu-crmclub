package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoEmployee;
import com.wuxue.model.ClassinfoEmployeeKey;

import java.util.List;

public interface ClassinfoEmployeeMapper extends IInsertMapper<ClassinfoEmployee>,ICountMapper<ClassinfoEmployee,Integer>,
        IUpdateMapper<ClassinfoEmployee>,IDeleteByPrimaryKeyMapper<ClassinfoEmployeeKey>,
        ISelectByPrimaryKeyMapper<ClassinfoEmployeeKey,ClassinfoEmployee>,ISelectMapper<ClassinfoEmployee,List<ClassinfoEmployee>>{

    List<ClassinfoEmployee> selectTeacher(ClassinfoEmployee tParams);

    List<ClassinfoEmployee> selectClassinfoTeacher(ClassinfoEmployee classinfoEmployee);

    List<ClassinfoEmployee> selectByTea(String pkEmployee);

    /**
     * 根据老师id查班级id
     *
     * @param employeeId
     * @return
     */
    List<String> findClassInfoIdsByEmployeeId(String employeeId);

}