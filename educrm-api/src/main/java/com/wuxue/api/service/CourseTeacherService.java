package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.CourseTeacher;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface CourseTeacherService extends ISaveService<CourseTeacher>,IFindService<CourseTeacher>,IDeleteService<CourseTeacher> {
    /**
     * 批量保存
     * */
    Response saveAll(Request<CourseTeacher> courseTeacher);

    /**
     * 老师课程
     * */
    Response getTeacherCourse(Request<CourseTeacher> courseTeacher);

    List<CourseTeacher> selectByEmp(String pkEmployee, String jobPost);
}
