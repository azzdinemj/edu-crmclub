package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentSignup;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface StudentSignupService extends ISaveService<StudentSignup>,IFindService<StudentSignup>,
        IDeleteService<String>,ISubmitService<StudentSignup>,IAuditService<StudentSignup>,ICancelService<StudentSignup> {
    /**
     * 退回报名（未审核）
     * */
    Response retreat(Request<StudentSignup> studentSignup);

    /**
     * 未分班人员
     * @return
     */
    Response getNotPlacement(Request<StudentSignup> studentSignup);

    /**
     * 修改学生报名状态
     * */
    Response updateStatus(Request<StudentSignup> studentSignup);

    Response getCost(Request<StudentSignup> studentSignup);
}
