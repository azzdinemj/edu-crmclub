package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.jndi.toolkit.url.GenericURLContext;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.CommonUtils;
import com.wuxue.api.utils.DateUtil;
import com.wuxue.api.utils.StudentUtiles;
import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.api.service.ClassinfoService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import com.wuxue.api.utils.GuidUtils;

@Service("classinfoService")
public class ClassinfoServiceImpl implements ClassinfoService {
    @Autowired
    ClassinfoMapper classinfoMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    StudentSignupMapper studentSignupMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SysAutoCodeService sysAutoCodeService;
    @Autowired
    ActivityStudentMapper activityStudentMapper;
    @Autowired
    ClassinfoEmployeeMapper classinfoEmployeeMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    private StudentScoresMapper studentScoresMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private ReceivableMapper receivableMapper;
    @Autowired
    private StudentPhaseRecordMapper studentPhaseRecordMapper;
    @Autowired
    private StudentUtiles studentUtiles;
    @Autowired
    private ClassinfoRoomMapper classinfoRoomMapper;
    @Autowired
    private DivisionGradeMapper divisionGradeMapper;

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
            iReuslt = classinfoMapper.deleteByPrimaryKey(primaryKey);
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
    public Response findClassForCourse(Request<Classinfo> tParams) {

        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();
        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }
        PageHelper.startPage(classinfo.getPageNo(), classinfo.getPageSize());
        List<Classinfo> classinfoList = new ArrayList<>();
        classinfoList = classinfoMapper.select(classinfo);
        PageInfo page = new PageInfo(classinfoList);
        response.setTotal(page.getTotal());
        if (classinfoList.size() > 0) {
            for (Classinfo list : classinfoList) {
                String doubleGrade = list.getDoubleGrade();
                String divisionGradeName= "";
                if (doubleGrade != null && !"".equals(doubleGrade)){
                    String[] split = doubleGrade.split(",");
                    if (split.length>0){
                        for (int i =0; i< split.length ;i++){

                            if (split[i] != null && !"".equals(split[i])){
                                DivisionGrade divisionGrade = divisionGradeMapper.selectByPrimaryKey(Integer.parseInt(split[i]));
                                if (divisionGrade != null ){
                                    if (i ==split.length-1){
                                        divisionGradeName = divisionGradeName + divisionGrade.getGradeName();
                                    }else {
                                        divisionGradeName = divisionGradeName + divisionGrade.getGradeName()+ ",";
                                    }

                                }
                            }
                        }
                    }
                }
                list.put("grade",divisionGradeName);
                utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setClassinfoScheduleStatus(list, list.getPkClassinfo(), LinkEntity.CLASSINFO_SCHEDULE_STATUS);
                utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                utilsService.setEmployeeKeyValue(list, list.getHeadTeacher(), LinkEntity.HEAD_TEACHER_ENTITY);
                utilsService.setEmployeeKeyValue(list, list.getSecondTeacher(), LinkEntity.SECOND_TEACHER_ENTITY);
                utilsService.setEmployeeKeyValue(list, list.getDirector(), LinkEntity.DIRECTOR_ENTITY);
                utilsService.setSysDictKeyValue(list, list.getGrade(), LinkEntity.GTADE_ENTITY);
                utilsService.setSysDictKeyValue(list, list.getProgram(), LinkEntity.SYSDIC_ENTITY);
                utilsService.setClassRoomKeyValue(list, list.getClassRoom(), LinkEntity.CLASSROOM_ENTITY);
            }
            response.setData(classinfoList);
        }
        return response;
    }

    /**
     * 班级详情/班级列表
     * 1、primaryKey为空查询班级列表，反之
     * 2、提供关联的用户
     */
    @Override
    public Response find(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();

        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = classinfo.getPkClassinfo();
        if (primaryKey != null && !primaryKey.equals("")) {
            Classinfo byPrimaryKey = classinfoMapper.selectByPrimaryKey(primaryKey);

//            获取班级学生
            ClassinfoStudent classinfoStudent = new ClassinfoStudent();
            classinfoStudent.setPkClassinfo(byPrimaryKey.getPkClassinfo());
            List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.select(classinfoStudent);

//            获取班级老师
            ClassinfoEmployee classinfoEmployee = new ClassinfoEmployee();
            classinfoEmployee.setPkClassinfo(byPrimaryKey.getPkClassinfo());
            List<ClassinfoEmployee> classinfoEmployeeList = classinfoEmployeeMapper.select(classinfoEmployee);

            List<Student> studentList = new ArrayList<>();
            List<Employee> employeeList = new ArrayList<>();
            List<StudentScores> studentScoresList = new ArrayList<>();

            if (classinfoStudentList.size() > 0) {
                for (ClassinfoStudent student : classinfoStudentList) {
                    if (student.getPkStudent() != null) {
                        Student selectByPrimaryKey = studentMapper.selectByPrimaryKey(student.getPkStudent());
//                        判断是否为空，防止数据库查不到数据（数据被误删）
                        if (selectByPrimaryKey != null) {
                            Receivable receivable = new Receivable();
                            receivable.setPkStudent(selectByPrimaryKey.getPkStudent());
                            receivable.setStatus(0);
                            List<Receivable> select = receivableMapper.select(receivable);
                            if (select != null && select.size() > 0) {
                                selectByPrimaryKey.put(Light.COST_TYPE, "costFalse");
                            } else {
                                selectByPrimaryKey.put(Light.COST_TYPE, "costTrue");
                            }
                            studentList.add(selectByPrimaryKey);
                        }
                    }
                }
            }

            if (classinfoEmployeeList.size() > 0) {
                for (ClassinfoEmployee employee : classinfoEmployeeList) {
                    if (employee.getPkEmployee() != null) {
                        Employee selectByPrimaryKey = employeeMapper.selectByPrimaryKey(employee.getPkEmployee());
//                        判断是否为空，防止数据库查不到数据（数据被误删）
                        if (selectByPrimaryKey != null) {
                            employeeList.add(selectByPrimaryKey);
                        }
                    }
                }
            }

            //学生成绩
            StudentScores studentScores = new StudentScores();
            studentScores.setPkClassinfo(primaryKey);
            List<StudentScores> scoresList = studentScoresMapper.select(studentScores);
//            for (ClassinfoStudent student : classinfoStudentList) {

            if (scoresList != null && scoresList.size() > 0) {
                for (StudentScores scores : scoresList) {
//                        if (scores.getPkStudent().equals(student.getPkStudent()) ){
                    //学生
                    utilsService.setStudentKeyValue(scores, scores.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                    //老师
                    utilsService.setEmployeeKeyValue(scores, scores.getPkEmployee(), LinkEntity.USER_ENTITY);
                    //课程
                    utilsService.setSysDictKeyValue(scores, scores.getPkCource(), LinkEntity.SYSDIC_ENTITY);
                    //班级
                    utilsService.setClassInfoKeyValue(scores, scores.getPkClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
                    //考试类型
                    utilsService.setSysDictKeyValue(scores, scores.getPkPlans(), LinkEntity.TEST_TYPE_ENTITY);
                    //考试名称
                    utilsService.setScoreCaptionKeyValue(scores, scores.getPkStudentTestPlans(), LinkEntity.SCORE_CAPTION_ENTITY);
                    //学期
                    utilsService.setScoreCaptionKeyValue(scores, scores.getTerm(), LinkEntity.TERM_ENTITY);
                    studentScoresList.add(scores);
                }

//                    }
            }
//            }


            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setClassinfoScheduleStatus(byPrimaryKey, byPrimaryKey.getPkClassinfo(), LinkEntity.CLASSINFO_SCHEDULE_STATUS);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getAuditor(), LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCancel(), LinkEntity.CANCEL_ENTITY);
            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getHeadTeacher(), LinkEntity.HEAD_TEACHER_ENTITY);
            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getSecondTeacher(), LinkEntity.SECOND_TEACHER_ENTITY);
            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getSecondTeacher(), LinkEntity.DIRECTOR_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getGrade(), LinkEntity.GTADE_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getProgram(), LinkEntity.SYSDIC_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getClassRoom(), LinkEntity.CLASSROOM_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getDivision(), LinkEntity.DIVISION_ENTITY);

            for (Student student : studentList) {
                utilsService.setStudentScoreKeyValue(student, byPrimaryKey.getPkClassinfo(), student.getPkStudent(), LinkEntity.STUDENT_SCORE_ENTITY);
                utilsService.setSysDictKeyValue(student, student.getNation(), LinkEntity.NATION_ENTITY);
            }
            byPrimaryKey.put(Light.STUDENT_LIST, studentList);
            byPrimaryKey.put(Light.EMPLOYEE, employeeList);
            byPrimaryKey.put(Light.STUDENT_TEST_PLANS_SCORES, studentScoresList);
            response.setData(byPrimaryKey);
        } else {

//            获取当前用户的教职工信息
            Employee employee = employeeMapper.selectByUser(tParams.getCurrentUser());
            PageHelper.startPage(classinfo.getPageNo(), classinfo.getPageSize());
            List<Classinfo> classinfoList = new ArrayList<>();
            if (employee != null) {
                classinfo.setPkEmployee(employee.getPkEmployee());
                classinfoList = classinfoMapper.selectClassinfoByEmployee(classinfo);
            } else {
                classinfoList = classinfoMapper.select(classinfo);
            }
            PageInfo page = new PageInfo(classinfoList);
            response.setTotal(page.getTotal());
            if (classinfoList.size() > 0) {
                for (Classinfo list : classinfoList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setClassinfoScheduleStatus(list, list.getPkClassinfo(), LinkEntity.CLASSINFO_SCHEDULE_STATUS);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getHeadTeacher(), LinkEntity.HEAD_TEACHER_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getSecondTeacher(), LinkEntity.SECOND_TEACHER_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getDirector(), LinkEntity.DIRECTOR_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getGrade(), LinkEntity.GTADE_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getProgram(), LinkEntity.SYSDIC_ENTITY);
                    utilsService.setClassRoomKeyValue(list, list.getClassRoom(), LinkEntity.CLASSROOM_ENTITY);
                }
            }
            response.setData(classinfoList);
            //response.setTotal(classinfoMapper.countBy(classinfo));
        }
        return response;
    }

    /**
     * 班级保存修改
     * 1.当班级主键存在的时候，修改最后修改人时间和内容
     * 2.当班级主键不存在的时候，执行保存方法
     * ·判断班级是否属于升级班级
     * ·班级属于升级班级获取升级班级主键和原班级孩子进行保存
     */
    @Override
    public Response save(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();

        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = classinfo.getPkClassinfo();
        Classinfo select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = classinfoMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message = "";
        try {
            if (select != null) {
                classinfo.setLasteditDate(new Date());
                iReuslt = classinfoMapper.updateByPrimaryKeySelective(classinfo);
            } else {
                classinfo.setPkClassinfo(GuidUtils.getGuid());
                classinfo.setCode(sysAutoCodeService.getCode("classinfo"));
                classinfo.setCreationDate(new Date());
                classinfo.setLasteditDate(new Date());
                iReuslt = classinfoMapper.insertSelective(classinfo);

//                判断班级的pk_parent存不存在，如果存在获取升级前班级的学生
                if (classinfo.getPkParent() != null && !classinfo.getPkParent().equals("")) {
                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                    classinfoStudent.setPkClassinfo(classinfo.getPkParent());
                    List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.select(classinfoStudent);

//                    把升级前的班级孩子复制粘贴跟新班级关联
                    if (classinfoStudentList != null && classinfoStudentList.size() > 0) {
                        for (ClassinfoStudent student : classinfoStudentList) {
                            student.setPkClassinfo(classinfo.getPkClassinfo());
                            student.setCreator(classinfo.getCreator());
                            student.setModifier(classinfo.getModifier());
                            student.setCreationDate(new Date());
                            student.setLasteditDate(new Date());
                            classinfoStudentMapper.insertSelective(student);
                        }
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

    /**
     * 学生进班
     * 1、保存学生班级关联表
     * 2、根据班级学生获取学生报名表反写最终班级到学生报名表
     */
    @Override
    public Response studentGoClass(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();
        CommonUtils commonUtils = new CommonUtils();

//        获取班级孩子
        Map<String, Object> map = classinfo.getMap();
        if (map != null) {
            List<ClassinfoStudent> classinfoStudentList = DataUtils.objectToList(map.get(Light.CLASSINFO_STUENT), ClassinfoStudent.class);
            if (classinfoStudentList.size() > 0) {
                for (ClassinfoStudent classinfoStudent : classinfoStudentList) {
                    Response status = commonUtils.getStudentSignupStatus(classinfoStudent.getPkStudent());
                    if (status != null) {
                        List<StudentSignup> studentSignupList = (List<StudentSignup>) status.getData();
                        classinfoStudent.setCreationDate(new Date());
                        classinfoStudent.setLasteditDate(new Date());
                        classinfoStudent.setIsvalid(1);
                        classinfoStudentMapper.insertSelective(classinfoStudent);

//                        反写最终班级到学生报名表
                        StudentSignup studentSignup = studentSignupMapper.selectByPrimaryKey(studentSignupList.get(0).getPkStudentSignup());
                        studentSignup.setPkClassinfo(classinfoStudent.getPkClassinfo());
                        studentSignupMapper.updateByPrimaryKey(studentSignup);
                    }
                }
            }
        }
        return response;
    }


    /**
     * 班级审核
     * 1、未取消、已提交的数据可以审核
     * 2、保存审核人、审核时间、审核状态、修改人、修改时间
     * 3、根据审核班级获取升班前班级学生并创建学生报名
     */
    @Override
    public Response audit(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();


        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = classinfo.getPkClassinfo();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                classinfoStudent.setPkClassinfo(classinfo.getPkClassinfo());
                Classinfo byPrimaryKey = classinfoMapper.selectByPrimaryKey(primaryKey);
                List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.select(classinfoStudent);

                Response verification = CommonUtils.auditVerification(byPrimaryKey);
                if (verification == null) {
                    if (byPrimaryKey.getAuditor() == null || "".equals(byPrimaryKey.getAuditor())) {
                        byPrimaryKey.setAuditor(classinfo.getAuditor());
                        byPrimaryKey.setIsaudit(1);
                        byPrimaryKey.setAuditDate(new Date());
                        byPrimaryKey.setModifier(classinfo.getAuditor());
                        byPrimaryKey.setLasteditDate(new Date());
                        classinfoMapper.updateByPrimaryKeySelective(byPrimaryKey);
                        if (classinfo.getPkParent() != null && !classinfo.getPkParent().equals("")) {
                            if (classinfoStudentList.size() > 0) {
                                for (ClassinfoStudent student : classinfoStudentList) {
                                    StudentSignup studentSignup = new StudentSignup();
                                    studentSignup.setPkStudentSignup(GuidUtils.getGuid());
                                    studentSignup.setPkDomain(byPrimaryKey.getPkDomain());
                                    studentSignup.setCode(sysAutoCodeService.getCode("signup"));
                                    studentSignup.setPkEmployee("");
                                    studentSignup.setPkStudent(student.getPkStudent());
                                    studentSignup.setGrade(byPrimaryKey.getGrade());
                                    studentSignup.setPkClassinfo(byPrimaryKey.getPkClassinfo());
                                    studentSignup.setCreator(byPrimaryKey.getAuditor());
                                    studentSignup.setModifier(byPrimaryKey.getAuditor());
                                    studentSignup.setDate(new Date());
                                    studentSignup.setCreationDate(new Date());
                                    studentSignup.setLasteditDate(new Date());
                                    studentSignupMapper.insertSelective(studentSignup);

                                    //把学生阶段信息存储(升班)
                                    StudentPhaseRecord studentPhaseRecord = new StudentPhaseRecord();
                                    studentPhaseRecord.setPkDomain(byPrimaryKey.getPkDomain());
                                    studentPhaseRecord.setCode(sysAutoCodeService.getCode("phaserecordcode"));
                                    studentPhaseRecord.setPkStudentPhaseRecord(GuidUtils.getGuid());
                                    studentPhaseRecord.setPkClassinfo(classinfo.getPkClassinfo());
                                    studentPhaseRecord.setType(Light.CLASS_ON_CODE);
                                    studentPhaseRecord.setStageTime(new Date());
                                    iReuslt = studentPhaseRecordMapper.insertSelective(studentPhaseRecord);

//                                报名信息增加完直接清班级学生关联
                                    classinfoStudentMapper.deleteByPrimaryKey(student);
                                }
                            }
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
    public Response goToSchool(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();


        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = classinfo.getPkClassinfo();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                classinfoStudent.setPkClassinfo(classinfo.getPkClassinfo());
                Classinfo byPrimaryKey = classinfoMapper.selectByPrimaryKey(primaryKey);
                List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.select(classinfoStudent);


                byPrimaryKey.setAuditor(tParams.getCurrentUser());
                byPrimaryKey.setIsaudit(1);
                byPrimaryKey.setIssubmit(1);
                byPrimaryKey.setSubmitor(tParams.getCurrentUser());
                byPrimaryKey.setAuditDate(new Date());
                byPrimaryKey.setModifier(classinfo.getAuditor());
                byPrimaryKey.setLasteditDate(new Date());
                classinfoMapper.updateByPrimaryKeySelective(byPrimaryKey);
                Map<String, Object> map = classinfo.getMap();
                List<String> list = DataUtils.objectToList(map.get(Light.STUDENT_LIST), String.class);
                if (list.size() > 0) {
                    for (String pkStudnet : list) {
                        StudentSignup studentSignup = new StudentSignup();
                        studentSignup.setPkStudentSignup(GuidUtils.getGuid());
                        studentSignup.setPkStudent(pkStudnet);
                        studentSignup.setGrade(byPrimaryKey.getGrade());
                        studentSignup.setPkClassinfo(byPrimaryKey.getPkClassinfo());
                        studentSignup.setCreator(byPrimaryKey.getAuditor());
                        studentSignup.setModifier(byPrimaryKey.getAuditor());
                        studentSignup.setCreationDate(new Date());
                        studentSignup.setLasteditDate(new Date());
                        studentSignup.setCode(sysAutoCodeService.getCode("signup"));

                        studentSignup.setSignupType(map.get(Light.SIGNUP_TYPE).toString());
                        studentSignup.setPkDomain(byPrimaryKey.getPkDomain());
                        studentSignup.setPkEmployee("");
                        studentSignupMapper.insertSelective(studentSignup);

                        //把学生阶段信息存储
                        StudentPhaseRecord studentPhaseRecord = new StudentPhaseRecord();
                        studentPhaseRecord.setPkDomain(byPrimaryKey.getPkDomain());
                        studentPhaseRecord.setCode(sysAutoCodeService.getCode("phaserecordcode"));
                        studentPhaseRecord.setPkStudentPhaseRecord(GuidUtils.getGuid());
                        studentPhaseRecord.setPkClassinfo(classinfo.getPkClassinfo());
                        studentPhaseRecord.setType(map.get(Light.SIGNUP_TYPE).toString());
                        studentPhaseRecord.setStageTime(new Date());
                        iReuslt = studentPhaseRecordMapper.insertSelective(studentPhaseRecord);

//                                报名信息增加完直接清班级学生关联
                        ClassinfoStudent classinfoStudent1 = new ClassinfoStudent();
                        classinfoStudent1.setPkClassinfo(primaryKey);
                        classinfoStudent1.setPkStudent(pkStudnet);
                        classinfoStudent1.setIsvalid(0);
                        classinfoStudentMapper.updateByPrimaryKeySelective(classinfoStudent1);
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


    /**
     * 班级提交
     * 1、未审核、未取消的可提交
     * 2、保存提交人、提交时间、提交状态、修改人、修改时间
     */
    @Override
    public Response submit(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();


        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = classinfo.getPkClassinfo();
        int iReuslt = 1;
        String message = "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                Classinfo byPrimaryKey = classinfoMapper.selectByPrimaryKey(primaryKey);

                Response verification = CommonUtils.submitVerification(byPrimaryKey);
                if (verification == null) {
                    if (byPrimaryKey.getSubmitor() == null || "".equals(byPrimaryKey.getSubmitor())) {
                        byPrimaryKey.setSubmitor(classinfo.getSubmitor());
                        byPrimaryKey.setIssubmit(1);
                        byPrimaryKey.setSubmitDate(new Date());
                        byPrimaryKey.setModifier(classinfo.getSubmitor());
                        byPrimaryKey.setLasteditDate(new Date());
                        classinfoMapper.updateByPrimaryKeySelective(byPrimaryKey);
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

    //成绩输入
    @Override
    public Response findClassStudentScores(Request<ClassinfoStudent> tParams) {
        Response response = Response.newResponse();
        ClassinfoStudent classinfoStudent = tParams.getData();
        //获取班级学生
        List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.select(classinfoStudent);
        Employee employee = employeeMapper.selectByUser(tParams.getCurrentUser());

        if (classinfoStudentList != null && classinfoStudentList.size() > 0) {
            //查询班级学生成绩
            StudentScores studentScores = new StudentScores();
            studentScores.setPkClassinfo(classinfoStudent.getPkClassinfo());
            List<StudentScores> select = studentScoresMapper.select(studentScores);
            for (ClassinfoStudent student : classinfoStudentList) {
                //学生
                utilsService.setStudentKeyValue(student, student.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                if (employee != null && employee.getPkEmployee() != null) {
                    utilsService.setEmployeeKeyValue(student, employee.getPkEmployee(), LinkEntity.EMP_ENTITY);
                }
                //班级
                utilsService.setClassInfoKeyValue(student, student.getPkClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
                if (select != null && select.size() > 0) {
                    for (StudentScores scores : select) {
                        if (scores.getPkStudent().equals(student.getPkStudent())) {
                            //老师
                            utilsService.setEmployeeKeyValue(scores, scores.getPkEmployee(), LinkEntity.USER_ENTITY);
                            //课程
                            utilsService.setSysDictKeyValue(scores, scores.getPkCource(), LinkEntity.SYSDIC_ENTITY);
                            //考试类型
                            utilsService.setSysDictKeyValue(scores, scores.getPkPlans(), LinkEntity.TEST_TYPE_ENTITY);
                            //考试名称
                            utilsService.setScoreCaptionKeyValue(scores, scores.getPkStudentTestPlans(), LinkEntity.SCORE_CAPTION_ENTITY);
                            student.put(LinkEntity.STUDENT_SCORE_ENTITY, scores);
                        }
                    }
                }
            }
        }

        response.setData(classinfoStudentList);
        return response;
    }

    @Override
    public Response selectiveCheck(Request<Schedule> tParams) {
        Response response = Response.newResponse();
        Schedule schedule = tParams.getData();


        if (schedule.getPkSchedule() == null) {
            return response.PARAMS_ISNULL();
        }
        //根据家长账号查找学生信息
        String pkStudent = studentUtiles.getPkStudent(tParams.getCurrentUser());
        if (pkStudent == null || "".equals(pkStudent)) {
            return response.STUDENT_NULL();
        }

        // 查询学生对应班级的所有排课信息
        List<Schedule> scheduleList = scheduleMapper.selectClasByStudent(pkStudent);
        if (scheduleList == null || scheduleList.size() == 0) {
            return response;
        } else {
            // 该课程信息
            Schedule byPrimaryKey = scheduleMapper.selectByPrimaryKey(schedule.getPkSchedule());

            //判断日期是周几
            Date startTime = byPrimaryKey.getStartTime();
            Date endTime = byPrimaryKey.getEndTime();
            int i = DateUtils.dayForWeek(startTime);
            //获取所选课程的上课时间 时分
            Date startTimeHM = DateTimeUtils.getDate(startTime, "hh:mm");
            Date endTimeHM = DateTimeUtils.getDate(endTime, "hh:mm");

            for (Schedule select : scheduleList) {
                int y = DateUtils.dayForWeek(select.getStartTime());
                if (i == y) {
                    Date seleteStart = DateTimeUtils.getDate(select.getStartTime(), "hh:mm");
                    Date seleteEnd = DateTimeUtils.getDate(select.getEndTime(), "hh:mm");
                    if (startTimeHM.getTime() <= seleteStart.getTime() && endTimeHM.getTime() >= seleteStart.getTime()) {
                        return response.PARAMS_ISNULL();
                    }
                    if (startTimeHM.getTime() >= seleteStart.getTime() && startTimeHM.getTime() <= seleteEnd.getTime()) {
                        return response.PARAMS_ISNULL();
                    }
                }
            }
        }
//        Schedule schedule1 = new Schedule();
//        schedule1.setPkStudent(pkStudent);
//        schedule1.setStartTime(byPrimaryKey.getStartTime());
//        schedule1.setEndTime(byPrimaryKey.getEndTime());
//        List<Schedule> select = scheduleMapper.select(schedule1);
//        schedule1.setPkProduct(byPrimaryKey.getPkProduct());
//        schedule1.setPkEmployee(byPrimaryKey.getPkEmployee());
//        if (select.size() > 0) {
//            return response.CONFLICT();
//        }

        return response;
    }

    @Override
    public Response selectBy(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();

        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }

        List<Classinfo> classinfoList = classinfoMapper.select(classinfo);
        response.setData(classinfoList);
        return response;
    }

    @Override
    public Response selectByType(Classinfo classinfo) {
        Response response = Response.newResponse();
        PageHelper.startPage(classinfo.getPageNo(), classinfo.getPageSize());
        List<Classinfo> list = classinfoMapper.selectByType(classinfo);
        PageInfo page = new PageInfo(list);
        response.setTotal(page.getTotal());
        response.setData(list);

        return response;
    }

    @Override
    public Response studentGoInterestClass(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();

//        获取班级孩子
        Map<String, Object> map = classinfo.getMap();
        if (map != null) {
            List<ClassinfoStudent> classinfoStudentList = DataUtils.objectToList(map.get(Light.CLASSINFO_STUENT), ClassinfoStudent.class);
            if (classinfoStudentList.size() > 0) {
                for (ClassinfoStudent classinfoStudent : classinfoStudentList) {
                    classinfoStudent.setCreator("admin");
                    classinfoStudent.setModifier("admin");
                    classinfoStudent.setCreationDate(new Date());
                    classinfoStudent.setLasteditDate(new Date());
                    classinfoStudent.setIsvalid(1);
                    classinfoStudentMapper.insertSelective(classinfoStudent);

                    ClassinfoStudent classinfoStudent1 = new ClassinfoStudent();
                    classinfoStudent1.setPkClassinfo(classinfoStudent.getPkClassinfo());
                    Classinfo byPrimaryKey = classinfoMapper.selectByPrimaryKey(classinfoStudent.getPkClassinfo());
                    List<ClassinfoStudent> select = classinfoStudentMapper.select(classinfoStudent1);
                    if (select.size() > 0) {
                        byPrimaryKey.setExistingNumbers(select.size());
                        classinfoMapper.updateByPrimaryKeySelective(byPrimaryKey);
                    }
                }
            }
        }
        return response;
    }


    @Override
    public Response findByIsPay(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();

        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = classinfo.getPkClassinfo();
        if (primaryKey != null && !primaryKey.equals("")) {
            Classinfo byPrimaryKey = classinfoMapper.selectByPrimaryKey(primaryKey);

//            获取排课记录
            Schedule schedule = new Schedule();
            schedule.setPkStudent(byPrimaryKey.getPkClassinfo());
            List<Schedule> scheduleList = scheduleMapper.select(schedule);

            byPrimaryKey.put(Light.SCHEDULE, scheduleList);

//            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
//            utilsService.setClassinfoScheduleStatus(byPrimaryKey, byPrimaryKey.getPkClassinfo(), LinkEntity.CLASSINFO_SCHEDULE_STATUS);
//            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
//            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
//            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getAuditor(), LinkEntity.AUDITOR_ENTITY);
//            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCancel(), LinkEntity.CANCEL_ENTITY);
//            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getHeadTeacher(), LinkEntity.HEAD_TEACHER_ENTITY);
//            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getSecondTeacher(), LinkEntity.SECOND_TEACHER_ENTITY);
//            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getSecondTeacher(), LinkEntity.DIRECTOR_ENTITY);
//            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getGrade(), LinkEntity.GTADE_ENTITY);
//            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getProgram(), LinkEntity.SYSDIC_ENTITY);
//            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getClassRoom(), LinkEntity.CLASSROOM_ENTITY);
//            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getDivision(), LinkEntity.DIVISION_ENTITY);

            response.setData(byPrimaryKey);
        } else {

//            获取当前用户的教职工信息
            PageHelper.startPage(classinfo.getPageNo(), classinfo.getPageSize());
            classinfo.setType(4);
            List<Classinfo> classinfoList = classinfoMapper.select(classinfo);
            PageInfo page = new PageInfo(classinfoList);
            response.setTotal(page.getTotal());
//            if (classinfoList.size() > 0) {
//                for (Classinfo list : classinfoList) {
//                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
//                    utilsService.setClassinfoScheduleStatus(list, list.getPkClassinfo(), LinkEntity.CLASSINFO_SCHEDULE_STATUS);
//                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
//                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
//                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
//                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
//                    utilsService.setEmployeeKeyValue(list, list.getHeadTeacher(), LinkEntity.HEAD_TEACHER_ENTITY);
//                    utilsService.setEmployeeKeyValue(list, list.getSecondTeacher(), LinkEntity.SECOND_TEACHER_ENTITY);
//                    utilsService.setEmployeeKeyValue(list, list.getDirector(), LinkEntity.DIRECTOR_ENTITY);
//                    utilsService.setSysDictKeyValue(list, list.getGrade(), LinkEntity.GTADE_ENTITY);
//                    utilsService.setSysDictKeyValue(list, list.getProgram(), LinkEntity.SYSDIC_ENTITY);
//                    utilsService.setSysDictKeyValue(list, list.getClassRoom(), LinkEntity.CLASSROOM_ENTITY);
//                }
//            }
            response.setData(classinfoList);
        }
        return response;
    }

    @Override
    public List<String> getClassIdsByTeacherId(String teacherId) {
        List<Classinfo> classinfos = classinfoMapper.selectClassInfoListByTeacherId(teacherId);
        if (CollectionUtils.isEmpty(classinfos)) {
            return new ArrayList<>();
        }
        List<String> ids = new ArrayList<>();
        for (Classinfo classinfo : classinfos) {
            ids.add(classinfo.getPkClassinfo());
        }
        return ids;
    }

    @Override
    public List<String> getClassIdsByStudentId(String studentId) {
        List<Classinfo> classinfos = classinfoMapper.selectClassInfoIdListByStudentId(studentId);
        if (CollectionUtils.isEmpty(classinfos)) {
            return new ArrayList<>();
        }
        List<String> ids = new ArrayList<>();
        for (Classinfo classinfo : classinfos) {
            ids.add(classinfo.getPkClassinfo());
        }
        return ids;
    }

    @Override
    public Response updateStatus(Request<Classinfo> tParams) {
        Response response = Response.newResponse();
        Classinfo classinfo = tParams.getData();

        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = classinfo.getPkClassinfo();
        String message = "";
        try {
            classinfo.setLasteditDate(new Date());
            classinfo.setModifier(tParams.getCurrentUser());
            classinfoMapper.updateByPrimaryKeySelective(classinfo);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response classOn(Request<Classinfo> classinfoRequest) {
        Response response = Response.newResponse();
        String message = "";
        try {
            classinfoMapper.classOn();
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Override
    public Response saveForSche(Request<String> tParams) {

        Response response = Response.newResponse();
        String pkClassinfo = tParams.getData();
        String message = "";
        String pkStudent = studentUtiles.getPkStudent(tParams.getCurrentUser());

        try {
            if (pkClassinfo != null && !"".equals(pkClassinfo)) {
                ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                classinfoStudent.setPkStudent(pkStudent);
                classinfoStudent.setPkClassinfo(pkClassinfo);
                ClassinfoStudent select = classinfoStudentMapper.selectByPrimaryKey(classinfoStudent);
                if (select != null) {
                    select.setIsvalid(1);
                    classinfoStudentMapper.updateByPrimaryKeySelective(select);
                } else {
                    classinfoStudent.setIsvalid(1);
                    classinfoStudent.setCreator(tParams.getCurrentUser());
                    classinfoStudent.setCreationDate(new Date());
                    classinfoStudent.setModifier(tParams.getCurrentUser());
                    classinfoStudent.setLasteditDate(new Date());
                    classinfoStudentMapper.insertSelective(classinfoStudent);
                }
                Classinfo classinfo = classinfoMapper.selectByPrimaryKey(pkClassinfo);
                classinfo.setExistingNumbers(classinfo.getExistingNumbers() + 1);
                classinfoMapper.updateByPrimaryKeySelective(classinfo);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }
//    @Override
//    public Response saveForSche(Request<List<String>> tParams) {
//
//        Response response = Response.newResponse();
//        List<String> data = tParams.getData();
//        String message = "";
//        String pkStudent = "";
//        Student student = studentMapper.selectByPrimaryKey(tParams.getCurrentUser());
//        if (student != null) {
//            pkStudent = student.getPkStudent();
//        } else {
//            StudentLinkmanKey studentLinkmanKey = studentLinkmanMapper.selectStuByParPhone(tParams.getCurrentUser());
//            if (studentLinkmanKey != null){
//                pkStudent = studentLinkmanKey.getPkStudent();
//            }
//            else {
//                Linkman linkman = linkmanMapper.selectByPhone(tParams.getCurrentUser());
//                if (linkman != null) {
//                    pkStudent = linkman.getPkStudent();
//                }
//            }
//        }
//        try {
//            if (data.size() >0){
//                for (String pkClassinfo : data) {
//                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
//                    classinfoStudent.setPkStudent(pkStudent);
//                    classinfoStudent.setPkClassinfo(pkClassinfo);
//                    classinfoStudent.setIsvalid(1);
//                    classinfoStudent.setCreator(tParams.getCurrentUser());
//                    classinfoStudent.setCreationDate(new Date());
//                    classinfoStudent.setModifier(tParams.getCurrentUser());
//                    classinfoStudent.setLasteditDate(new Date());
//                    classinfoStudentMapper.insertSelective(classinfoStudent);
//                    Classinfo classinfo = classinfoMapper.selectByPrimaryKey(pkClassinfo);
//                    classinfo.setExistingNumbers(classinfo.getExistingNumbers()+1);
//                    classinfoMapper.updateByPrimaryKeySelective(classinfo);
//                }
//            }
//        }catch (Exception ex){
//            message = ex.getMessage();
//            return response.SAVE_FAIL(message);
//        }
//
//
//        return response;
//    }

    /**
     * 仅查询班级名称等简要信息
     *
     * @param tParm
     * @return
     */
    @Override
    public Response getClassinfo(Request<Classinfo> tParm) {
        Response response = Response.newResponse();
        String message = "";
        Classinfo data = tParm.getData();
        try {
            List<KeyValue> select = classinfoMapper.selectKeyValue(data);
            response.setData(select);

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Override
    public Response saveElective(Request<Classinfo> classinfoRequest) {
        Response response = Response.newResponse();
        Classinfo classinfo = classinfoRequest.getData();
        if (classinfo == null) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            String pkClassinfo = classinfo.getPkClassinfo();
            Classinfo select = classinfoMapper.selectByPrimaryKey(pkClassinfo);
            if (select == null) {
                if (pkClassinfo == null || "".equals(pkClassinfo)) {
                    classinfo.setPkClassinfo(GuidUtils.getGuid());
                }
                classinfo.setCreationDate(new Date());
                classinfo.setLasteditDate(new Date());
                int i = classinfoMapper.insertSelective(classinfo);
                if (i > 0) {
                    classinfo.getTeacherId();
                    if (classinfo.getTeacherId() != null && !"".equals(classinfo.getTeacherId())) {
                        ClassinfoEmployee classinfoEmployee = new ClassinfoEmployee();
                        classinfoEmployee.setPkClassinfo(classinfo.getPkClassinfo());
                        classinfoEmployee.setPkEmployee(classinfo.getTeacherId());
                        classinfoEmployee.setIsvalid(1);
                        classinfoEmployee.setCreator(classinfo.getCreator());
                        classinfoEmployee.setCreationDate(new Date());
                        classinfoEmployee.setLasteditDate(new Date());
                        classinfoEmployee.setModifier(classinfo.getModifier());
                        classinfoEmployeeMapper.insertSelective(classinfoEmployee);

                    }
                    if (classinfo.getClassRoom() != null && !"".equals(classinfo.getClassRoom())) {
                        ClassinfoRoom classinfoRoom = new ClassinfoRoom();
                        classinfoRoom.setPkClassinfo(classinfo.getPkClassinfo());
                        classinfoRoom.setPkClassinfoRoom(GuidUtils.getGuid());
                        classinfoRoom.setPkClassroom(classinfo.getClassRoom());
                        classinfoRoom.setIsvalid(1);
                        classinfoRoom.setCreator(classinfo.getCreator());
                        classinfoRoom.setCreationDate(new Date());
                        classinfoRoom.setLasteditDate(new Date());
                        classinfoRoom.setModifier(classinfo.getModifier());
                        classinfoRoom.setPkDomain(classinfo.getPkDomain());
                        classinfoRoomMapper.insertSelective(classinfoRoom);
                    }
                }
            } else {

                int i = classinfoMapper.updateByPrimaryKeySelective(classinfo);
                if (i >0){
                    if (classinfo.getTeacherId() != null && !"".equals(classinfo.getTeacherId())){
                        ClassinfoEmployee classinfoEmployee = new ClassinfoEmployee();
                        classinfoEmployee.setPkEmployee(classinfo.getTeacherId());
                        classinfoEmployee.setPkClassinfo(select.getPkClassinfo());
                        ClassinfoEmployee classinfoEmployee1 = classinfoEmployeeMapper.selectByPrimaryKey(classinfoEmployee);
                        if (classinfoEmployee1 == null){
                            classinfoEmployee.setIsvalid(1);
                            classinfoEmployee.setCreator(classinfo.getCreator());
                            classinfoEmployee.setCreationDate(new Date());
                            classinfoEmployee.setLasteditDate(new Date());
                            classinfoEmployee.setModifier(classinfo.getModifier());
                            classinfoEmployeeMapper.insertSelective(classinfoEmployee);
                        }else {
                            if (classinfoEmployee1.getIsvalid() == null || classinfoEmployee1.getIsvalid()!= 1){
                                classinfoEmployee.setIsvalid(1);
                                classinfoEmployeeMapper.updateByPrimaryKeySelective(classinfoEmployee);
                            }
                        }
                    }
                }
                if (classinfo.getClassRoom() != null && !"".equals(classinfo.getClassRoom())){
                    ClassinfoRoom classinfoRoom = new ClassinfoRoom();
                    classinfoRoom.setPkClassinfo(pkClassinfo);
                    classinfoRoom.setPkClassroom(classinfo.getClassRoom());
                    ClassinfoRoom classinfoRoom1 = classinfoRoomMapper.selectByUnique(classinfoRoom);
                    if (classinfoRoom1== null){
                        classinfoRoom.setIsvalid(1);
                        classinfoRoom.setPkDomain(select.getPkDomain());
                        classinfoRoom.setPkClassinfoRoom(GuidUtils.getGuid());
                        classinfoRoom.setCreator(classinfo.getCreator());
                        classinfoRoom.setCreationDate(new Date());
                        classinfoRoom.setLasteditDate(new Date());
                        classinfoRoom.setPkDomain(classinfo.getPkDomain());
                        classinfoRoom.setModifier(classinfo.getModifier());
                        classinfoRoomMapper.insertSelective(classinfoRoom);
                    }else {
                        if (classinfoRoom1.getIsvalid() == null || classinfoRoom1.getIsvalid() != 1){
                            classinfoRoom.setIsvalid(1);
                            classinfoRoom.setPkClassinfoRoom(classinfoRoom1.getPkClassinfoRoom());
                            classinfoRoomMapper.insertSelective(classinfoRoom);
                        }
                    }
                }

            }


        } catch (Exception e) {
            message = e.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

}
