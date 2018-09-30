package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.CommonUtils;
import com.wuxue.base.Page;
import com.wuxue.model.*;
import com.wuxue.api.service.StudentSignupService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentSignupService")
public class StudentSignupServiceImpl implements StudentSignupService {
    @Autowired
    StudentSignupMapper studentSignupMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SysSetMapper sysSetMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    ReceivableMapper receivableMapper;

    @Autowired
    ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    StudentSignupDetailsMapper studentSignupDetailsMapper;

    @Autowired
    SysAutoCodeService sysAutoCodeService;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    private ReceiptMapper receiptMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if (primaryKey == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = studentSignupMapper.deleteByPrimaryKey(primaryKey);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<StudentSignup> tParams) {
        Response response = Response.newResponse();
        StudentSignup studentSignup = tParams.getData();

        if (studentSignup == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = studentSignup.getPkStudentSignup();
        if (primaryKey != null && !primaryKey.equals("")) {
            StudentSignup byPrimaryKey = studentSignupMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getAuditor(), LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCancel(), LinkEntity.CANCEL_ENTITY);
            utilsService.setStudentKeyValue(byPrimaryKey, byPrimaryKey.getPkStudent(), LinkEntity.STUDENT_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getGrade(), LinkEntity.GTADE_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getProgram(), LinkEntity.SYSDIC_ENTITY);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(studentSignup.getPageNo(), studentSignup.getPageSize());
            List<StudentSignup> signupList = studentSignupMapper.select(studentSignup);
            PageInfo page = new PageInfo(signupList);
            response.setTotal(page.getTotal());
            if (signupList.size() > 0) {
                for (StudentSignup list : signupList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                    utilsService.setStudentKeyValue(list, list.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getGrade(), LinkEntity.GTADE_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getProgram(), LinkEntity.SYSDIC_ENTITY);
                    utilsService.setClassInfoKeyValue(list, list.getPkClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
                }
            }
            response.setData(signupList);
            //response.setTotal(studentSignupMapper.countBy(studentSignup));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentSignup> tParams) {
        Response response = Response.newResponse();
        StudentSignup studentSignup = tParams.getData();

        if (studentSignup == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = studentSignup.getPkStudentSignup();
        StudentSignup signup = new StudentSignup();
        if (studentSignup.getPkClassinfo() != null && !"".equals(studentSignup.getPkClassinfo())) {
            signup.setPkStudent(studentSignup.getPkStudent());
            signup.setPkClassinfo(studentSignup.getPkClassinfo());
            List<StudentSignup> list = studentSignupMapper.select(signup);
            //检查相同班级是否重复报名
            if (list != null && list.size() > 0) {
                return response.REPEAT_SIGNUP();
            }
        }
        if (!primaryKey.equals("") || primaryKey != null) {
            signup = studentSignupMapper.selectByPrimaryKey(primaryKey);
//            studentSignup.setPkDomain(signup.getPkDomain());
        }
        int iReuslt = 1;
        String message = "";
        try {
            if (signup != null) {
                studentSignup.setLasteditDate(new Date());
                iReuslt = studentSignupMapper.updateByPrimaryKeySelective(studentSignup);
                studentSignupDetailsMapper.deleteByPrimaryKey(studentSignup.getPkStudentSignup());
            } else {
                if (studentSignup.getPkStudentSignup() == null || studentSignup.getPkStudentSignup().equals("")) {
                    studentSignup.setPkStudentSignup(GuidUtils.getGuid());
                }
                studentSignup.setCode(sysAutoCodeService.getCode("signup"));
                studentSignup.setCreationDate(new Date());
                studentSignup.setLasteditDate(new Date());
                iReuslt = studentSignupMapper.insertSelective(studentSignup);
            }
//            获取费用明细
            Map<String, Object> map = studentSignup.getMap();
            if (map != null) {
                List<StudentSignupDetails> studentSignupDetailsList = DataUtils.objectToList(map.get(Light.STUDENT_SIGNUP_DETAILS), StudentSignupDetails.class);
                if (studentSignupDetailsList != null && studentSignupDetailsList.size() > 0) {
                    for (StudentSignupDetails studentSignupDetails : studentSignupDetailsList) {
                        if (studentSignupDetails.getPkStudentSignupDetails() == null || studentSignup.getPkStudentSignup().equals("")) {
                            studentSignupDetails.setPkStudentSignupDetails(GuidUtils.getGuid());
                        }
                        studentSignupDetails.setPkDomain(studentSignup.getPkDomain());
                        studentSignupDetails.setPkStudentSignup(studentSignup.getPkStudentSignup());
                        studentSignupDetailsMapper.insertSelective(studentSignupDetails);
                    }
                }
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response audit(Request<StudentSignup> tParams) {
        Response response = Response.newResponse();
        StudentSignup studentSignup = tParams.getData();
        if (studentSignup == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = studentSignup.getPkStudentSignup();
        int iReuslt = 0;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                StudentSignup signup = studentSignupMapper.selectByPrimaryKey(primaryKey);
                Response verification = CommonUtils.auditVerification(signup);
                if (verification == null) {
//                    if (signup.getAuditor() != null) {
//                    if (signup.getAuditor() == null) {
                    if (signup.getAuditor() == null || "".equals(signup.getAuditor())) {
                        signup.setIsaudit(1);
                        signup.setAuditor(studentSignup.getAuditor());
                        signup.setModifier(studentSignup.getAuditor());
                        signup.setLasteditDate(new Date());
                        signup.setAuditDate(new Date());
//                      直接新增未入班学生
//                        signup.setStatus(1);
                        signup.setReviewStatus(2);
//
                        CommonUtils commonUtils = new CommonUtils();
                        //Response flag = Response.newResponse();

////                    是否生成应收单表数据
////                        根据币种生成对应的应收单
                        Receivable receivable = new Receivable();
                        receivable.setPkParent(signup.getPkStudentSignup());
                        receivable.setPkStudent(signup.getPkStudent());
                        receivable.setPkDomain(signup.getPkDomain());
                        receivable.setDate(new Date());
                        receivable.setCreator(signup.getAuditor());
                        receivable.setCreationDate(new Date());
                        receivable.setModifier(signup.getAuditor());
                        receivable.setLasteditDate(new Date());
                        receivable.setIssubmit(1);
                        receivable.setSchoolYear(signup.getSchoolYear());
                        receivable.setSubmitor(signup.getAuditor());
                        receivable.setSubmitDate(new Date());
                        receivable.setIsaudit(1);
                        receivable.setAuditor(signup.getAuditor());
                        receivable.setAuditDate(new Date());
                        if (signup.getMny() != null && signup.getMny().compareTo(BigDecimal.ZERO) == 1) {
                            receivable.setCode(sysAutoCodeService.getCode("receivable"));
                            receivable.setPkReceivable(GuidUtils.getGuid());
                            receivable.setCost(signup.getMny());
                            receivable.setCurrency("CNY");
                            receivableMapper.insertSelective(receivable);
                        }
                        if (signup.getMnyUsd() != null && signup.getMnyUsd().compareTo(BigDecimal.ZERO) == 1) {
                            receivable.setCode(sysAutoCodeService.getCode("receivable"));
                            receivable.setPkReceivable(GuidUtils.getGuid());
                            receivable.setCost(signup.getMnyUsd());
                            receivable.setCurrency("USD");
                            receivableMapper.insertSelective(receivable);
                        }

                        //任务
                        Task task = new Task();
                        task.setPkTask(GuidUtils.getGuid());
                        task.setIsdel(1);
                        task.setContent("报名费用审核");
                        // 0财务 1其他
                        task.setType(0);
                        task.setCreator(tParams.getCurrentUser());
                        task.setModifier(tParams.getCurrentUser());
                        task.setCreationDate(new Date());
                        task.setLasteditDate(new Date());
                        taskMapper.insertSelective(task);

                        //是否有班级
                        if (signup.getPkClassinfo() != null && !signup.getPkClassinfo().equals("")) {
                            ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                            classinfoStudent.setPkClassinfo(signup.getPkClassinfo());
                            classinfoStudent.setPkStudent(signup.getPkStudent());
                            classinfoStudent.setKind(1);
                            classinfoStudent.setIsvalid(1);
                            classinfoStudent.setCreator(studentSignup.getAuditor());
                            classinfoStudent.setCreationDate(new Date());
                            classinfoStudent.setModifier(studentSignup.getAuditor());
                            classinfoStudent.setLasteditDate(new Date());
                            classinfoStudentMapper.insertSelective(classinfoStudent);
                        }
                        Student student = new Student();
                        student.setPkStudent(signup.getPkStudent());
                        student.setModifier(studentSignup.getAuditor());
                        student.setLasteditDate(new Date());
                        student.setIstype(1);
                        studentMapper.updateByPrimaryKeySelective(student);
                        iReuslt = studentSignupMapper.updateByPrimaryKeySelective(signup);

                    }
                } else {
                    return verification;
                }
            } catch (Exception ex) {
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        } else {
            return response.PARAMS_ISNULL();
        }
        if (iReuslt > 0) {
            return response;
        }

        return response.SAVE_FAIL(message);
    }

    @Override
    public Response submit(Request<StudentSignup> tParams) {
        Response response = Response.newResponse();
        StudentSignup studentSignup = tParams.getData();

        if (studentSignup == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = studentSignup.getPkStudentSignup();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                StudentSignup signup = studentSignupMapper.selectByPrimaryKey(primaryKey);
                Response verification = CommonUtils.submitVerification(signup);

                if (verification == null) {
                    if (signup.getIssubmit() != null && signup.getIssubmit() != 1) {
                        /*if(studentSignup.getMny() == null || studentSignup.getMnyUsd() == null || signup.getMny() == null || signup.getMnyUsd() == null){
                            response.setMessage("金额为空不可提交");
                            response.setCode(912);
                            return response;
                        }*/
                        signup.setIssubmit(1);
                        signup.setSubmitor(tParams.getCurrentUser());
                        signup.setSubmitDate(new Date());
                        signup.setModifier(tParams.getCurrentUser());
                        signup.setLasteditDate(new Date());
                        //                      直接新增未入班学生
                        signup.setStatus(1);
                        String value = utilsService.getSysSetValue(signup.getPkDomain(), "generate_receivable_student_signup");

//                        获取路线 1直接提交应收单  2提交审核
                        if (value.equals("1")) {
                            signup.setReviewStatus(2);
                        } else if (value.equals("2")) {
                            signup.setReviewStatus(1);
                        }
                        iReuslt = studentSignupMapper.updateByPrimaryKeySelective(signup);
//                        studentSignupMapper.updateByPrimaryKeySelective(studentSignup);


                        //                    是否生成应收单表数据
                        value = utilsService.getSysSetValue(signup.getPkDomain(), "generate_receivable_student_signup");
//                        根据币种生成对应的应收单
                        if (value.equals("1")) {
                            Receivable receivable = new Receivable();
                            receivable.setPkParent(signup.getPkStudentSignup());
                            receivable.setPkStudent(signup.getPkStudent());
                            receivable.setPkDomain(signup.getPkDomain());
                            receivable.setDate(new Date());
                            receivable.setCreator(tParams.getCurrentUser());
                            receivable.setCreationDate(new Date());
                            receivable.setModifier(tParams.getCurrentUser());
                            receivable.setLasteditDate(new Date());
//                            value = utilsService.getSysSetValue(signup.getPkDomain(), "generate_receivable_student_signup_submit");
//                            if (value.equals("true")) {
                            receivable.setIssubmit(1);
                            receivable.setSchoolYear(signup.getSchoolYear());
                            receivable.setSubmitor(tParams.getCurrentUser());
                            receivable.setSubmitDate(new Date());
//                            }
//                            value = utilsService.getSysSetValue(signup.getPkDomain(), "generate_receivable_student_signup_audit");
//                            if (value.equals("true")) {
                            receivable.setIsaudit(1);
                            receivable.setAuditor(tParams.getCurrentUser());
                            receivable.setAuditDate(new Date());
//                            }
                            if (signup.getMny() != null && signup.getMny().compareTo(BigDecimal.ZERO) == 1) {
                                receivable.setCode(sysAutoCodeService.getCode("receivable"));
                                receivable.setPkReceivable(GuidUtils.getGuid());
                                receivable.setCost(signup.getMny());
                                receivable.setCurrency("CNY");
                                receivableMapper.insertSelective(receivable);
                            }
                            if (signup.getMnyUsd() != null && signup.getMnyUsd().compareTo(BigDecimal.ZERO) == 1) {
                                receivable.setCode(sysAutoCodeService.getCode("receivable"));
                                receivable.setPkReceivable(GuidUtils.getGuid());
                                receivable.setCost(signup.getMnyUsd());
                                receivable.setCurrency("USD");
                                receivableMapper.insertSelective(receivable);
                            }

                            //任务
                            Task task = new Task();
                            task.setPkTask(GuidUtils.getGuid());
                            task.setIsdel(1);
                            task.setContent("报名费用审核");
                            // 0财务 1其他
                            task.setType(0);
                            task.setCreator(tParams.getCurrentUser());
                            task.setModifier(tParams.getCurrentUser());
                            task.setCreationDate(new Date());
                            task.setLasteditDate(new Date());
                            taskMapper.insertSelective(task);

                            //是否有班级
                            if (signup.getPkClassinfo() != null && !signup.getPkClassinfo().equals("")) {
                                ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                                classinfoStudent.setPkClassinfo(signup.getPkClassinfo());
                                classinfoStudent.setPkStudent(signup.getPkStudent());
                                classinfoStudent.setKind(1);
                                classinfoStudent.setCreator(tParams.getCurrentUser());
                                classinfoStudent.setCreationDate(new Date());
                                classinfoStudent.setModifier(tParams.getCurrentUser());
                                classinfoStudent.setLasteditDate(new Date());
                                classinfoStudentMapper.insertSelective(classinfoStudent);
                            }
                            Student student = new Student();
                            student.setPkStudent(signup.getPkStudent());
                            student.setModifier(tParams.getCurrentUser());
                            student.setLasteditDate(new Date());
                            student.setIstype(1);
                            studentMapper.updateByPrimaryKeySelective(student);
//                        iReuslt = studentSignupMapper.updateByPrimaryKeySelective(signup);
                        }
                    } else {
                        return response.SAVE_DOUBLE();
                    }
                }
            } catch (Exception ex) {
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        } else {
            return response.PARAMS_ISNULL();
        }
        if (iReuslt > 0) {
            return response;
        }

        return response.SAVE_FAIL(message);
    }

    @Override
    public Response retreat(Request<StudentSignup> tParams) {
        Response response = Response.newResponse();
        StudentSignup studentSignup = tParams.getData();

        if (studentSignup == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = studentSignup.getPkStudentSignup();
        int iReuslt = 1;
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                StudentSignup signup = studentSignupMapper.selectByPrimaryKey(primaryKey);
                if (signup.getIsaudit() != null && signup.getIsaudit() == 1) {
                    response.setMessage("审核后，不能取消");
                    return response;
                }
                if (signup.getIssubmit() == 1) {
                    signup.setIscancel(1);
                    signup.setCancel(studentSignup.getCancel());
                    signup.setSubmitDate(new Date());
                    iReuslt = studentSignupMapper.updateByPrimaryKey(signup);
                } else {
                    return response.SAVE_DOUBLE();
                }
            } catch (Exception ex) {
                response.setMessage("审核后，不能取消");
                return response;
            }
        } else {
            return response.PARAMS_ISNULL();
        }

        if (iReuslt > 0) {
            return response;
        }

        response.setMessage("审核后，不能取消");
        return response;
    }

    @Override
    public Response cancel(Request<StudentSignup> tParams) {
        Response response = Response.newResponse();
        StudentSignup studentSignup = tParams.getData();

        if (studentSignup == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = studentSignup.getPkStudentSignup();
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                StudentSignup signup = studentSignupMapper.selectByPrimaryKey(primaryKey);
                Response verification = CommonUtils.auditVerification(signup);
                if (verification == null) {
                    signup.setModifier(tParams.getCurrentUser());
                    signup.setLasteditDate(new Date());
                    signup.setReviewStatus(4);
                    signup.setIssubmit(0);
                    studentSignupMapper.updateByPrimaryKey(signup);

                    if (signup.getPkClassinfo() != null) {
                        ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                        classinfoStudent.setPkStudent(signup.getPkStudent());
                        classinfoStudent.setPkClassinfo(signup.getPkClassinfo());
                        classinfoStudentMapper.deleteByPrimaryKey(classinfoStudent);
                    }
                }
            } catch (Exception ex) {
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        } else {
            return response.PARAMS_ISNULL();
        }

        return response;
    }


    @Override
    public Response getNotPlacement(Request<StudentSignup> tParams) {
        Response response = Response.newResponse();
        StudentSignup data = tParams.getData();
        PageHelper.startPage(data.getPageNo(), data.getPageSize());
        List<StudentSignup> studentSignups = studentSignupMapper.getNotPlacement(data);
        PageInfo page = new PageInfo(studentSignups);
        response.setTotal(page.getTotal());
        if (studentSignups != null && studentSignups.size() > 0) {
            for (StudentSignup studentSignup : studentSignups) {
                utilsService.setStudentKeyValue(studentSignup, studentSignup.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                utilsService.setSysDictKeyValue(studentSignup, studentSignup.getGrade(), LinkEntity.GTADE_ENTITY);
                utilsService.setSysDictKeyValue(studentSignup, studentSignup.getProgram(), LinkEntity.SYSDIC_ENTITY);
                utilsService.setEmployeeKeyValue(studentSignup, studentSignup.getPkEmployee(), LinkEntity.EMP_ENTITY);
                utilsService.setClassInfoKeyValue(studentSignup, studentSignup.getPkClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
            }
        }

        response.setData(studentSignups);
        return response;
    }

    @Override
    public Response updateStatus(Request<StudentSignup> tParams) {
        Response response = Response.newResponse();
        StudentSignup studentSignup = tParams.getData();

        if (studentSignup == null && studentSignup.getPkStudentSignup() == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = studentSignup.getPkStudentSignup();
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                StudentSignup byPrimaryKey = studentSignupMapper.selectByPrimaryKey(studentSignup.getPkStudentSignup());
                studentSignup.setLasteditDate(new Date());
                studentSignupMapper.updateByPrimaryKeySelective(studentSignup);

                Student student = studentMapper.selectByPrimaryKey(byPrimaryKey.getPkStudent());
                student.setIstype(1);
                student.setModifier(studentSignup.getModifier());
                student.setLasteditDate(new Date());
                studentMapper.updateByPrimaryKeySelective(student);
            } catch (Exception ex) {
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        } else {
            return response.PARAMS_ISNULL();
        }

        return response;
    }

    @Override
    public Response getCost(Request<StudentSignup> request) {
        Response response = Response.newResponse();
        StudentSignup studentSignup = request.getData();
        if (studentSignup.getPkStudent()== null || "".equals(studentSignup.getPkStudent()) || studentSignup.getSchoolYear()==null || "".equals(studentSignup.getSchoolYear())){
            return response.PARAMS_ISNULL();
        }

        Map<String,Object> map = studentSignupMapper.selectForFrop(studentSignup);
        BigDecimal money = receiptMapper.selectSum(studentSignup);
        map.put("receipts",money);
        response.setData(map);
        return response;
    }
}
