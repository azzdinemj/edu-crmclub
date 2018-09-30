package com.wuxue.api.utils;


import com.wuxue.api.mapper.ReceivableMapper;
import com.wuxue.api.mapper.StudentSignupMapper;
import com.wuxue.api.mapper.SysSetMapper;
import com.wuxue.base.BusinessPage;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogue on 2018/1/20.
 */
public class CommonUtils {

    @Autowired
    SysSetMapper sysSetMapper;
    @Autowired
    StudentSignupMapper studentSignupMapper;
    @Autowired
    ReceivableMapper receivableMapper;

    /**
     * @param businessPage
     * 审核验证
     * @return
     */
    public static Response auditVerification(BusinessPage businessPage) {
        if (businessPage != null) {
            if (businessPage.getIssubmit() == null || businessPage.getIssubmit() != 1) {
                return Response.newResponse().AUDIT_SUBMIT();
            }

            if (businessPage.getIscancel() != null && businessPage.getIscancel() == 1) {
                return Response.newResponse().AUDIT_CANCEL();
            }
        }
        return null;
    }

    /**
     * @param businessPage
     * 提交验证
     * @return
     */
    public static Response submitVerification(BusinessPage businessPage) {
        if (businessPage != null) {
            if (businessPage.getIsaudit() !=null && businessPage.getIsaudit() == 2) {
                return Response.newResponse().SUBMIT_AUDIT();
            }

            if (businessPage.getIscancel() != null && businessPage.getIscancel() != 1) {
                return Response.newResponse().SUBMIT_CANCEL();
            }
        }
        return null;
    }

    /**
     * @param businessPage
     * 作废验证
     * @return
     */
    public static Response cnacelVerification(BusinessPage businessPage) {

        if(businessPage.getIsaudit() != null && businessPage.getIsaudit() != 1){
            return Response.newResponse().CANCEL_AUDIT();
        }

        return null;
    }


    /**
     * 获取权限（查看用户是否设置了自动执行下一步操作的权限）
     * @param pkDomain
     * @param pkSysSet
     * @return
     */
    public Response getFlag(String pkDomain, String pkSysSet) {
        Response response = Response.newResponse();
        SysSetKey sysSetKey = new SysSetKey();
        sysSetKey.setPkDomain(pkDomain);
        sysSetKey.setPkSysSet(pkSysSet);
        SysSet sysSet = sysSetMapper.selectByPrimaryKey(sysSetKey);
        if (sysSet != null && "true".equals(sysSet.getValue())) {
            response = null;
        }
        return response;
    }


    /**
     * 获取学生的报名状态（查看是否交完费用）
     * @return
     */
    public Response getStudentSignupStatus(String student) {
        Response response = Response.newResponse();
//        获取到没有班级的报名学生
        StudentSignup studentSignup = new StudentSignup();

        if (student != null) {
            studentSignup.setPkStudent(student);
        }

        List<StudentSignup> studentSignupList = studentSignupMapper.getNotPlacement(studentSignup);
        List<StudentSignup> studentSignupArrayList = new ArrayList<>();
        response.setData(studentSignupList);
        return response;
    }

}
