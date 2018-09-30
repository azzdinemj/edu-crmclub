package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.ClassinfoStudentKey;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface ClassinfoStudentService extends ISaveService<ClassinfoStudent>,IFindService<ClassinfoStudent>,IDeleteService<ClassinfoStudentKey> {

    /**
     * 学生转班
     * @param classinfoStudent
     * @return
     */
    Response studentReturnClass(Request<ClassinfoStudent> classinfoStudent);

    /**
     * 根据班级id集合获取所有学生id
     * @param classIds
     * @return
     */
    List<String> getStudentIdsByClassinfoIds(List<String> classIds);

    Response deleteByClassAndStu(Request<String> pkClassinfo);
}
