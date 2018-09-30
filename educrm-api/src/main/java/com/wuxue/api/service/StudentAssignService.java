package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.StudentAssign;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface StudentAssignService extends ISaveService<StudentAssign>,IFindService<StudentAssign>,IDeleteService<String> {
    /**
     * 课程学习进度
     * */
    Response getStudentCourseStatus(Request<StudentAssign> studentAssign);
}
