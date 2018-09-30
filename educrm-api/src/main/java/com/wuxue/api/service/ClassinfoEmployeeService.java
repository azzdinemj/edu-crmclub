package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ClassinfoEmployee;
import com.wuxue.model.ClassinfoEmployeeKey;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface ClassinfoEmployeeService extends ISaveService<ClassinfoEmployee>,IFindService<ClassinfoEmployee>,IDeleteService<ClassinfoEmployee> {
    /**
     * 批量保存
     * */
    Response saveAll(Request<ClassinfoEmployee> classinfoEmployee);
    /**
     * 查询所有班级老师
     * */
    Response findTeacher(Request<ClassinfoEmployee> classinfoEmployee);

    /**
     * 查询班级老师
     * */
    Response findClassinfoTeacher(Request<ClassinfoEmployee> classinfoEmployee);

    /**
     * 查询老师名下班级
     * @param pkEmployee
     * @return
     */
    List<ClassinfoEmployee> selectByTea(String pkEmployee);

    /**
     * 根据老师id查询对应班级id
     *
     * @param employeeId
     * @return
     */
    List<String> findClassInfoIdsByEmployeeId(String employeeId);
}
