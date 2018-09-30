package com.wuxue.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.StudentService;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    LinkmanMapper linkmanMapper;
    @Autowired
    StudentSpecialtyMapper studentSpecialtyMapper;
    @Autowired
    StudentActivityExpMapper studentActivityExpMapper;
    @Autowired
    StudentAwardsMapper studentAwardsMapper;
    @Autowired
    StudentCreditMapper studentCreditMapper;
    @Autowired
    StudentPlansMapper studentPlansMapper;
    @Autowired
    StudentBehaviorRecordMapper studentBehaviorRecordMapper;
    @Autowired
    StudentEduExperienceMapper studentEduExperienceMapper;
    @Autowired
    StudentInterviewRecordMapper studentInterviewRecordMapper;
    @Autowired
    StudentWorksPortfolioMapper studentWorksPortfolioMapper;
    @Autowired
    StudentTestPlansScoresMapper studentTestPlansScoresMapper;
    @Autowired
    StudentTestPlansMapper studentTestPlansMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    SysAutoCodeService sysAutoCodeService;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    ClassinfoEmployeeMapper classinfoEmployeeMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    ClassinfoMapper classinfoMapper;
    @Autowired
    private StudentPhaseRecordMapper studentPhaseRecordMapper;
    @Autowired
    private ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    private StudentLinkmanMapper studentLinkmanMapper;
    @Autowired
    private StudentShiftMapper studentShiftMapper;

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
            Student student = studentMapper.selectByPrimaryKey(primaryKey);
            student.setIsvalid(0);
            iReuslt = studentMapper.updateByPrimaryKeySelective(student);
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
    public Response find(Request<Student> tParams) {
        Response response = Response.newResponse();
        Student student = tParams.getData();

        if (student == null && tParams.getCurrentUser() != null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = student.getPkStudent();
        if (primaryKey != null && !primaryKey.equals("")) {
            Student byPrimaryKey = studentMapper.selectByPrimaryKey(primaryKey);

//          监护人资料
//            Linkman linkman = new Linkman();
//
//            linkman.setPkStudent(byPrimaryKey.getPkStudent());
            List<Linkman> linkmanList = linkmanMapper.selectByStudent(byPrimaryKey.getPkStudent());

            byPrimaryKey.put(Light.LINKMAN, linkmanList);

//          特长
//            StudentSpecialty studentSpecialty = new StudentSpecialty();
//
//            studentSpecialty.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentSpecialty> studentSpecialtyList = studentSpecialtyMapper.select(studentSpecialty);
//
//            byPrimaryKey.put(Light.STUDENT_SPECIALTY, studentSpecialtyList);

//          计划
//            StudentPlans studentPlans = new StudentPlans();
//
//            studentPlans.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentPlans> studentPlansList = studentPlansMapper.select(studentPlans);
//
//            byPrimaryKey.put(Light.STUDENT_PLANS, studentPlansList);

//          教育经历
//            StudentEduExperience studentEduExperience = new StudentEduExperience();
//
//            studentEduExperience.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentEduExperience> studentEduExperienceList = studentEduExperienceMapper.select(studentEduExperience);
//
//            byPrimaryKey.put(Light.STUDENT_EDU_EXPERIENCE, studentEduExperienceList);

//          学分
//            StudentCredit studentCredit = new StudentCredit();
//
//            studentCredit.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentCredit> studentCreditList = studentCreditMapper.select(studentCredit);
//
//            byPrimaryKey.put(Light.STUDENT_CREDIT, studentCreditList);

//          活动经历
//            StudentActivityExp studentActivityExp = new StudentActivityExp();
//
//            studentActivityExp.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentActivityExp> studentActivityExpList = studentActivityExpMapper.select(studentActivityExp);
//
//            byPrimaryKey.put(Light.STUDENT_ACTIVITY_EXP, studentActivityExpList);

//          年级奖项
//            StudentAwards studentAwards = new StudentAwards();
//
//            studentAwards.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentAwards> studentAwardsList = studentAwardsMapper.select(studentAwards);
//
//            byPrimaryKey.put(Light.STUDENT_AWARDS, studentAwardsList);

//          作品集
//            StudentWorksPortfolio studentWorksPortfolio = new StudentWorksPortfolio();
//
//            studentWorksPortfolio.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentWorksPortfolio> studentWorksPortfolioList = studentWorksPortfolioMapper.select(studentWorksPortfolio);
//
//            if (studentWorksPortfolioList.size() > 0) {
//                for (StudentWorksPortfolio worksPortfolio : studentWorksPortfolioList) {
//                    utilsService.setSysDictKeyValue(worksPortfolio, worksPortfolio.getGrade(), LinkEntity.GTADE_ENTITY);
//                    utilsService.setSysDictKeyValue(worksPortfolio, worksPortfolio.getTypeWork(), LinkEntity.TYPE_WORK_ENTITY);
//                }
//            }
//            byPrimaryKey.put(Light.STUDENT_WOEKS_PORTFOLIO, studentWorksPortfolioList);

//          考试成绩
//            StudentTestPlansScores studentTestPlansScores = new StudentTestPlansScores();
//            StudentTestPlans studentTestPlans = new StudentTestPlans();
//
//            studentTestPlans.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentTestPlans> studentTestPlansList = studentTestPlansMapper.select(studentTestPlans);
//
//            if (studentTestPlansList.size() > 0) {
//                studentTestPlansScores.setPkStudentTestPlans(studentTestPlansList.get(0).getPkStudentTestPlans());
//                List<StudentTestPlansScores> studentTestPlansScoresList = studentTestPlansScoresMapper.select(studentTestPlansScores);
//
//                byPrimaryKey.put(Light.STUDENT_TEST_PLANS_SCORES, studentTestPlansScoresList);
//            }

//          访谈记录
//            StudentInterviewRecord studentInterviewRecord = new StudentInterviewRecord();
//
//            studentInterviewRecord.setPkStudent(byPrimaryKey.getPkStudent());
//            studentInterviewRecord.setIsType(byPrimaryKey.getMentality());
//            List<StudentInterviewRecord> studentInterviewRecordList = studentInterviewRecordMapper.select(studentInterviewRecord);
//
//            byPrimaryKey.put(Light.STUDENT_INTERVIEW_RECORD, studentInterviewRecordList);

//          纪律行为信息
//            StudentBehaviorRecord studentBehaviorRecord = new StudentBehaviorRecord();
//
//            studentBehaviorRecord.setPkStudent(byPrimaryKey.getPkStudent());
//            List<StudentBehaviorRecord> studentBehaviorRecordList = studentBehaviorRecordMapper.select(studentBehaviorRecord);
//
//            byPrimaryKey.put(Light.STUDENT_BEHAVIOR_RECORD, studentBehaviorRecordList);

            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        } else {
//          获取当前用户的职工信息
            // Employee employee = employeeMapper.selectByUser(tParams.getCurrentUser());

            PageHelper.startPage(student.getPageNo(), student.getPageSize());
            //student.setPkEmployee(employee.getPkEmployee());
            List<Student> studentList = studentMapper.select(student);
            PageInfo page = new PageInfo(studentList);
            response.setTotal(page.getTotal());

            //assertEquals(10, studentList.size());
            //assertEquals(239, page.getTotal());
            if (studentList.size() > 0) {
                for (Student list : studentList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getPkSysUser(), LinkEntity.USER_ENTITY);
                    utilsService.setLinkManKeyValue(list, list.getPkStudent(), LinkEntity.LINKMAN_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getGrade(), LinkEntity.GTADE_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getTracksource(), LinkEntity.CHANNEL_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getNationality(), LinkEntity.COUNTRY_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getNation(), LinkEntity.NATION_ENTITY);
                    if (list.getIstype() != 0) {
                        ClassinfoStudent classinfoStu = getClassinfoStu(list.getPkStudent());
                        utilsService.setClassInfoKeyValue(list, classinfoStu.getPkClassinfo(), LinkEntity.CLASS_INFO_ENTITY);

                    }
                }
            }

            response.setData(studentList);
            //response.setTotal(studentMapper.countBy(student));

        }
        return response;
    }

    /**
     * 中航油学生列表
     *
     * @param tParams
     * @return
     */
    @Override
    public Response<PageInfo<Student>> findzhyou(Request<Student> tParams) {
        Response response = Response.newResponse();
        Student student = tParams.getData();

        PageHelper.startPage(student.getPageNo(), student.getPageSize());
        List<Student> studentList = studentMapper.select(student);
        PageInfo<Course> pageInfo = new PageInfo(studentList);
        response.setData(pageInfo);

      /*  response.setTotal(page.getTotal());

        if (studentList.size() > 0) {
            for (Student list : studentList) {
                utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                utilsService.setSysUserKeyValue(list, list.getPkSysUser(), LinkEntity.USER_ENTITY);
            }
        }
        response.setData(studentList);*/

        return response;
    }

    @Override
    public Response findEmployeeStudent(Request<Student> tParams) {
        Response response = Response.newResponse();
        Student student = tParams.getData();

        if (student == null && tParams.getCurrentUser() != null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = student.getPkStudent();
//          获取当前用户的职工信息
        Employee employee = employeeMapper.selectByUser(tParams.getCurrentUser());

        List<Student> studentList = new ArrayList<>();
        PageHelper.startPage(student.getPageNo(), student.getPageSize());
        if (employee != null) {
            student.setPkEmployee(employee.getPkEmployee());
            studentList = studentMapper.selectStudentByEmployee(student);
        } else {
            studentList = studentMapper.select(student);
        }
        PageInfo page = new PageInfo(studentList);
        response.setTotal(page.getTotal());

        if (studentList.size() > 0) {
            for (Student list : studentList) {
                utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                utilsService.setSysUserKeyValue(list, list.getPkSysUser(), LinkEntity.USER_ENTITY);
                utilsService.setLinkManKeyValue(list, list.getPkStudent(), LinkEntity.LINKMAN_ENTITY);
                utilsService.setSysDictKeyValue(list, list.getTracksource(), LinkEntity.CHANNEL_ENTITY);
                utilsService.setSysDictKeyValue(list, list.getNationality(), LinkEntity.COUNTRY_ENTITY);
                utilsService.setSysDictKeyValue(list, list.getNation(), LinkEntity.NATION_ENTITY);
            }
        }
        response.setData(studentList);
        return response;
    }

    @Override
    public Response save(Request<Student> tParams) {
        Response response = Response.newResponse();
        Student student = tParams.getData();
        if (student == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = student.getPkStudent().trim();
        int iReuslt = 1;
        String message = "";
        try {
            Student studentTmp = null;
            if (primaryKey != null && !primaryKey.equals("")) {

                studentTmp = studentMapper.selectByPrimaryKey(primaryKey);
            }
            if (studentTmp != null) {
                //把学生阶段信息存储
                StudentPhaseRecord studentPhaseRecord = new StudentPhaseRecord();
                if (studentTmp.getIstype() != student.getIstype() && studentTmp.getIstype() != 0 && studentTmp.getIstype() != 4) {
                    if (student.getIstype() == 3) {//记录退学状态
                        studentPhaseRecord.setType(Light.DROP_SCHOOL_CODE);
                    }
                    if (student.getIstype() == 5) {//记录毕业状态
                        studentPhaseRecord.setType(Light.GRADUATION_CODE);
                    }
                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                    classinfoStudent.setPkStudent(student.getPkStudent());
                    List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.selectPkClassinfo(classinfoStudent);
                    if (classinfoStudentList.size() > 0) {
                        studentPhaseRecord.setPkClassinfo(classinfoStudentList.get(0).getPkClassinfo());
                    }
                    studentPhaseRecord.setPkDomain(tParams.getCurrendDomain());
                    studentPhaseRecord.setCode(sysAutoCodeService.getCode("phaserecordcode"));
                    studentPhaseRecord.setPkStudentPhaseRecord(GuidUtils.getGuid());
                    studentPhaseRecord.setStageTime(new Date());
                    iReuslt = studentPhaseRecordMapper.insertSelective(studentPhaseRecord);

                }

                student.setLasteditDate(new Date());
                iReuslt = studentMapper.updateByPrimaryKeySelective(student);
                // 删除学生绑定信息
                //学生特长
//                studentSpecialtyMapper.deleteByPrimaryKey(student.getPkStudent());
//                学生计划
//                studentPlansMapper.deleteByPrimaryKey(student.getPkStudent());
//                作品集
//                studentWorksPortfolioMapper.deleteByPrimaryKey(student.getPkStudent());
//                获奖经历
//                studentAwardsMapper.deleteByPrimaryKey(student.getPkStudent());
//                家庭信息
                Map<String, Object> map = student.getMap();
                if (map != null){

                }
                studentTmp.getPkClassinfo();
                // 之前未入行政班
                if((studentTmp.getPkClassinfo() == null || studentTmp.getPkClassinfo().equals("")) && student.getPkClassinfo() != null && !student.getPkClassinfo().equals("") ){
                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
//                    classinfoStudent.setIsvalid(0);
                    classinfoStudent.setPkStudent(studentTmp.getPkStudent());
                    classinfoStudent.setPkClassinfo(student.getPkClassinfo());
                    ClassinfoStudent classinfoStudent1 = classinfoStudentMapper.selectByPrimaryKey(classinfoStudent);
                    if (classinfoStudent1 != null){
                        classinfoStudent1.setIsvalid(1);
                        classinfoStudentMapper.updateByPrimaryKeySelective(classinfoStudent1);
                    }else {
                        classinfoStudent.setIsvalid(1);
                        classinfoStudent.setCreator(tParams.getCurrentUser());
                        classinfoStudent.setCreationDate(new Date());
                        classinfoStudent.setModifier(tParams.getCurrentUser());
                        classinfoStudent.setLasteditDate(new Date());
                        classinfoStudentMapper.insertSelective(classinfoStudent);
                    }
                }
                // 已入行政班并改变行政班
                if ((studentTmp.getPkClassinfo() != null && !studentTmp.getPkClassinfo().equals("")) && student.getPkClassinfo() != null && !student.getPkClassinfo().equals("")){
                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
//                    classinfoStudent.setIsvalid(0);
                    classinfoStudent.setPkStudent(studentTmp.getPkStudent());
                    classinfoStudent.setPkClassinfo(studentTmp.getPkClassinfo());
                    ClassinfoStudent classinfoStudent1 = classinfoStudentMapper.selectByPrimaryKey(classinfoStudent);
                    if (classinfoStudent1 != null){
                        classinfoStudent1.setIsvalid(0);
                        classinfoStudentMapper.updateByPrimaryKeySelective(classinfoStudent1);
                    }
                    classinfoStudent.setPkStudent(student.getPkStudent());
                    classinfoStudent.setPkClassinfo(student.getPkClassinfo());
                    ClassinfoStudent classinfoStudent2 = classinfoStudentMapper.selectByPrimaryKey(classinfoStudent);
                    if (classinfoStudent2 != null){
                        classinfoStudent.setIsvalid(1);
                        classinfoStudentMapper.updateByPrimaryKeySelective(classinfoStudent2);
                    }
                    else {
                        classinfoStudent.setIsvalid(1);
                        classinfoStudent.setCreator(tParams.getCurrentUser());
                        classinfoStudent.setCreationDate(new Date());
                        classinfoStudent.setModifier(tParams.getCurrentUser());
                        classinfoStudent.setLasteditDate(new Date());
                        classinfoStudentMapper.insertSelective(classinfoStudent);
                    }
                    StudentShift studentShift = new StudentShift();
                    studentShift.setPkStudentShift(GuidUtils.getGuid());
                    studentShift.setPkStudent(studentTmp.getPkStudent());
                    studentShift.setPkParentClassinfo(studentTmp.getPkClassinfo());
                    studentShift.setPkClassinfo(student.getPkClassinfo());
                    studentShift.setIsvalid(1);
                    studentShift.setCreator(tParams.getCurrentUser());
                    studentShift.setCreationDate(new Date());
                    studentShift.setModifier(tParams.getCurrentUser());
                    studentShift.setLasteditDate(new Date());
                    studentShiftMapper.insertSelective(studentShift);
                }


//                linkmanMapper.deleteByPrimaryKey(student.getPkStudent());
//                studentLinkmanMapper.deleteByStudent(student.getPkStudent());
                utilsService.saveLog(studentTmp, student, "student", 1, primaryKey, tParams.getCurrentUser());
            } else {
                if (student.getPkStudent() == null || student.getPkStudent().equals("")) {
                    student.setPkStudent(GuidUtils.getGuid());
                }
                student.setCode(sysAutoCodeService.getCode("addstudent"));
                student.setCreationDate(new Date());
                student.setLasteditDate(new Date());
                student.setIsvalid(1);
                student.setPkSysUser(tParams.getCurrentUser());
                iReuslt = studentMapper.insertSelective(student);

                if (student.getPkClassinfo() != null && !"".equals(student.getPkClassinfo())){
                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                    classinfoStudent.setPkStudent(studentTmp.getPkStudent());
                    classinfoStudent.setPkClassinfo(student.getPkClassinfo());
                    ClassinfoStudent classinfoStudent1 = classinfoStudentMapper.selectByPrimaryKey(classinfoStudent);
                    if (classinfoStudent1 != null){
                        classinfoStudent1.setIsvalid(1);
                        classinfoStudentMapper.updateByPrimaryKeySelective(classinfoStudent1);
                    }else {
                        classinfoStudent.setIsvalid(1);
                        classinfoStudent.setCreator(tParams.getCurrentUser());
                        classinfoStudent.setCreationDate(new Date());
                        classinfoStudent.setModifier(tParams.getCurrentUser());
                        classinfoStudent.setLasteditDate(new Date());
                        classinfoStudentMapper.insertSelective(classinfoStudent);
                    }
                }

            }
//            保存学生的附加信息
            Map<String, Object> map = student.getMap();
            if (map != null) {

//                学生特长
//                List<StudentSpecialty> studentSpecialtyList = DataUtils.objectToList(map.get(Light.STUDENT_SPECIALTY), StudentSpecialty.class);
//                if (studentSpecialtyList != null && studentSpecialtyList.size() > 0) {
//                    for (StudentSpecialty studentSpecialty : studentSpecialtyList) {
//                        if (studentSpecialty.getPkStudentSpecialty() == null || studentSpecialty.getPkStudentSpecialty().equals("")) {
//                            studentSpecialty.setPkStudentSpecialty(GuidUtils.getGuid());
//                        }
//                        studentSpecialty.setPkStudent(student.getPkStudent());
//                        studentSpecialty.setCreationDate(new Date());
//                        studentSpecialty.setLasteditDate(new Date());
//                        studentSpecialtyMapper.insertSelective(studentSpecialty);
//                    }
//                }
//                学生计划
//                List<StudentPlans> studentPlansList = DataUtils.objectToList(map.get(Light.STUDENT_PLANS), StudentPlans.class);
//                if (studentPlansList != null && studentPlansList.size() > 0) {
//                    for (StudentPlans studentPlans : studentPlansList) {
//                        if (studentPlans.getPkStudentPlans() == null || studentPlans.getPkStudentPlans().equals("")) {
//                            studentPlans.setPkStudentPlans(GuidUtils.getGuid());
//                        }
//                        studentPlans.setPkStudent(student.getPkStudent());
//                        studentPlans.setCreationDate(new Date());
//                        studentPlans.setLasteditDate(new Date());
//                        studentPlansMapper.insertSelective(studentPlans);
//                    }
//                }
//                作品集
//                List<StudentWorksPortfolio> studentWorksPortfolioList = DataUtils.objectToList(map.get(Light.STUDENT_WOEKS_PORTFOLIO), StudentWorksPortfolio.class);
//                if (studentWorksPortfolioList != null && studentWorksPortfolioList.size() > 0) {
//                    for (StudentWorksPortfolio studentWorksPortfolio : studentWorksPortfolioList) {
//                        if (studentWorksPortfolio.getPkStudentWorksPortfolio() == null || studentWorksPortfolio.getPkStudentWorksPortfolio().equals("")) {
//                            studentWorksPortfolio.setPkStudentWorksPortfolio(GuidUtils.getGuid());
//                        }
//                        studentWorksPortfolio.setPkStudent(student.getPkStudent());
//                        studentWorksPortfolio.setCreationDate(new Date());
//                        studentWorksPortfolio.setLasteditDate(new Date());
//                        studentWorksPortfolioMapper.insertSelective(studentWorksPortfolio);
//                    }
//                }
//                获奖经历
//                List<StudentAwards> studentAwardsList = DataUtils.objectToList(map.get(Light.STUDENT_AWARDS), StudentAwards.class);
//                if (studentAwardsList != null && studentAwardsList.size() > 0) {
//                    for (StudentAwards studentAwards : studentAwardsList) {
//                        if (studentAwards.getPkStudentAwards() == null || studentAwards.getPkStudentAwards().equals("")) {
//                            studentAwards.setPkStudentAwards(GuidUtils.getGuid());
//                        }
//                        studentAwards.setPkStudent(student.getPkStudent());
//                        studentAwards.setCreationDate(new Date());
//                        studentAwards.setLasteditDate(new Date());
//                        studentAwardsMapper.insertSelective(studentAwards);
//                    }
//                }
//                家庭信息
                List<Linkman> linkmanList = DataUtils.objectToList(map.get(Light.LINKMAN), Linkman.class);
                if (linkmanList != null && linkmanList.size() > 0) {
                    for (Linkman linkman : linkmanList) {
                        if (linkman != null){
                            String pkLinkman = linkman.getPkLinkman();
                            if (pkLinkman != null && !"".equals(pkLinkman)){

                                Linkman linkman1 = linkmanMapper.selectByPrimaryKey(pkLinkman);

                                if (linkman1 != null){
                                    linkmanMapper.updateByPrimaryKeySelective(linkman);
                                    StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
                                    studentLinkmanKey.setPkStudent(student.getPkStudent());
                                    studentLinkmanKey.setPkLinkman(linkman.getPkLinkman());
                                    StudentLinkmanKey studentLinkmanKey1 = studentLinkmanMapper.selectByPrimaryKey(studentLinkmanKey);
                                    if (studentLinkmanKey1==null){
                                        studentLinkmanMapper.insertSelective(studentLinkmanKey);
                                    }
                                }else {
                                    if (linkman.getCode() == null || linkman.getCode().equals("")) {
                                        linkman.setCode(sysAutoCodeService.getCode("linkman"));
                                    }
                                    linkman.setCreationDate(new Date());
                                    linkman.setLasteditDate(new Date());
                                    linkmanMapper.insertSelective(linkman);
                                    StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
                                    studentLinkmanKey.setPkStudent(student.getPkStudent());
                                    studentLinkmanKey.setPkLinkman(linkman.getPkLinkman());
                                    StudentLinkmanKey studentLinkmanKey1 = studentLinkmanMapper.selectByPrimaryKey(studentLinkmanKey);
                                    if (studentLinkmanKey1==null){
                                        studentLinkmanMapper.insertSelective(studentLinkmanKey);
                                    }

                                }
                            }else {
                                linkman.setPkLinkman(GuidUtils.getGuid());
                                if (linkman.getCode() == null || linkman.getCode().equals("")) {
                                    linkman.setCode(sysAutoCodeService.getCode("linkman"));
                                }
                                linkman.setPkStudent(student.getPkStudent());
                                linkman.setCreationDate(new Date());
                                linkman.setLasteditDate(new Date());
                                linkmanMapper.insertSelective(linkman);
                                StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
                                studentLinkmanKey.setPkStudent(student.getPkStudent());
                                studentLinkmanKey.setPkLinkman(linkman.getPkLinkman());
                                studentLinkmanMapper.insertSelective(studentLinkmanKey);
                            }

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

    @Override
    public Response login(Request<Student> tParams) {
        if (tParams == null || tParams.getData() == null || tParams.getData().getPhone() == null) {
            return Response.newResponse().PARAMS_ISNULL();
        }
        Student student = tParams.getData();
        List<Student> studentList = studentMapper.select(student);
        if (studentList.size() <= 0) {
            return Response.newResponse().USER_NULL();
        }
        Student byPrimaryKey = studentList.get(0);
        if (byPrimaryKey == null || byPrimaryKey.getPhone() == null || byPrimaryKey.getPhone().equals("")) {
            return Response.newResponse().USER_NULL();
        }
        if (byPrimaryKey.getIsvalid() == null || byPrimaryKey.getIsvalid() != 1) {
            return Response.newResponse().USER_ISVALID();
        }
        if (byPrimaryKey.getPassword() != null && !byPrimaryKey.getPassword().equals(MD5Util.string2MD5(student.getPassword()))) {
            return Response.newResponse().LOGIN_FAIL();
        }
        Response response = Response.newResponse();
        byPrimaryKey.setPassword("");
        response.setData(byPrimaryKey);
        return response;
    }

    /**
     * 注册账号（进入数据库检索，判断该手机号码是否存在，若存在激活账号）
     *
     * @param tParams
     * @return
     */
    @Override
    public Response register(Request<Student> tParams) {
        if (tParams == null || tParams.getData() == null || tParams.getData().getPhone() == null) {
            return Response.newResponse().PARAMS_ISNULL();
        }
        Student student = tParams.getData();
        List<Student> studentList = studentMapper.select(student);
        if (studentList.size() <= 0) {
            return Response.newResponse().USER_NULL();
        }
        String message;
        try {
            studentList.get(0).setIsvalid(1); //激活账号
            studentMapper.updateByPrimaryKeySelective(studentList.get(0));
//            student.setPkStudent(GuidUtils.getGuid());
//            student.setCreationDate(new Date());
//            student.setLasteditDate(new Date());
//            student.setPassword(MD5Util.string2MD5(student.getPassword()));
//            student.setIsvalid(0);
//            studentMapper.insertSelective(student);
        } catch (Exception ex) {
            message = ex.getMessage();
            return Response.newResponse().REGISTER_FAIL(message);
        }
        return Response.newResponse();
    }

    /**
     * 修改电话 或者密码
     *
     * @param tParams
     * @return
     */
    @Override
    public Response updatePassword(Request<Student> tParams) {
        if (tParams == null || tParams.getData() == null || tParams.getData().getPhone() == null) {
            return Response.newResponse().PARAMS_ISNULL();
        }
        Student student = tParams.getData();
        if (student.getPassword() == null) {
            Student student1 = new Student();
            student1.setPhone(student.getPhone());
            List<Student> students = studentMapper.select(student1);
            if (students.size() > 0) {
                return Response.newResponse().PHONE_EXISTS();
            }
            //修改电话
            studentMapper.updateByPrimaryKeySelective(student);
        } else if (student.getPassword() != null) {
            Student student1 = new Student();
            student1.setPhone(student.getPhone());
            List<Student> studentList = studentMapper.select(student1); //根据电话查找出学生
            if (studentList.size() > 0) {
                //修改密码
                studentList.get(0).setPassword(MD5Util.string2MD5(student.getPassword()));
                studentMapper.updateByPrimaryKeySelective(studentList.get(0));
            } else {
                return Response.newResponse().USER_NULL();
            }
        }
        return Response.newResponse();
    }

    /**
     * 获得学生详情（不返回任何关联数据）
     */
    @Override
    public Response getStudent(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if (primaryKey == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }

        Student student = studentMapper.selectByPrimaryKey(primaryKey);
        utilsService.setSysUserKeyValue(student, student.getCreator(), LinkEntity.CREATOR_ENTITY);
        utilsService.setSysUserKeyValue(student, student.getModifier(), LinkEntity.MODIFIER_ENTITY);
        response.setData(student);

        return response;
    }

    /**
     * 中航后台新增、修改学生
     *
     * @param tParams
     * @return
     */
    @Override
    public Response saveStudentzhy(Request<Student> tParams) {
        Response response = Response.newResponse();
        int iReuslt = 1;
        Student student = tParams.getData();
        if (student == null) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            if (student.getPkStudent() != null && student.getPkStudent() != "") {
                iReuslt = studentMapper.updateByPrimaryKeySelective(student);
            } else if (student.getPkStudent() == null || student.getPkStudent() == "") {
                Student select = new Student();
                select.setPhone(student.getPhone());
                List<Student> studentList = studentMapper.select(select);
                if (studentList.size() > 0) {
                    return Response.newResponse().SAVE_DOUBLE();
                }
                student.setPkStudent(GuidUtils.getGuid());
                student.setPassword("e10adc3949ba59abbe56e057f20f883e");
                student.setCreationDate(new Date());
                student.setLasteditDate(new Date());
                student.setIsvalid(0);
                student.setShortCode("zh");
                student.setCode("zh");
                iReuslt = studentMapper.insertSelective(student);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return Response.newResponse().SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    /**
     * 根据电话查找学生
     *
     * @param tParams
     * @return
     */
    @Override
    public Response findStudentByPhone(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryPhone = tParams.getData();
        if (primaryPhone == null) {
            return response.PARAMS_ISNULL();
        }
        String message;
        try {
            Student student = new Student();
            student.setPhone(primaryPhone);
            List<Student> studentList = studentMapper.select(student);
            response.setData(studentList.get(0));
        } catch (Exception ex) {
            message = ex.getMessage();
            return Response.newResponse().REGISTER_FAIL(message);
        }
        return response;
    }

    @Override
    public Response findAll(Request<Student> tParams) {
        Response response = Response.newResponse();
        Student student = tParams.getData();

        if (student == null) {
            return response.PARAMS_ISNULL();
        }

        List<Student> studentList = studentMapper.select(student);
        response.setData(studentList);

        return response;
    }

    @Override
    public Response audit(Request<Student> tParams) {
        Response response = Response.newResponse();
        Student student = tParams.getData();

        if (student == null && student.getPkStudent() == null) {
            return response.PARAMS_ISNULL();
        }

        String message;
        try {
            Student select = studentMapper.selectByPrimaryKey(student.getPkStudent());
            select.setIsvalid(1);
            studentMapper.updateByPrimaryKeySelective(select);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ClassRoomMapper classRoomMapper;

    /**
     * jiedian  返回学生详情页
     *
     * @param tParams
     * @return
     */
    @Override
    public Response findjiedian(Request<Student> tParams) {
        Response response = Response.newResponse();
        Student student = tParams.getData();
        if (student == null) {
            return response.PARAMS_ISNULL();
        }
        if (student.getPkStudent() != null && student.getPkStudent() != "") {
            Student byPrimaryKey = studentMapper.selectByPrimaryKey(student.getPkStudent());

            //学生的所有课程
            Schedule schedule2 = new Schedule();
            schedule2.setPkStudent(byPrimaryKey.getPkStudent());
            List<Schedule> listschedul = scheduleMapper.select(schedule2);
            if (listschedul.size() > 0) {
                for (Schedule s : listschedul) {
                    Product product = productMapper.selectByPrimaryKey(s.getPkProduct());
                    if (product != null) {
                        s.put(Light.PRODUCTOBJ, product.getCaption());
                    } else {
                        s.put(Light.PRODUCTOBJ, null);
                    }
                    ClassRoom classRoom = classRoomMapper.selectByPrimaryKey(s.getPkClassRoom());
                    if (classRoom != null) {
                        s.put(Light.CLASSROOMOBJ, classRoom.getCaption());
                    } else {
                        s.put(Light.CLASSROOMOBJ, null);
                    }

                }
            }

            byPrimaryKey.put(Light.STU_SCH, listschedul);
            response.setData(byPrimaryKey);
        } else {
//            PageHelper.startPage(student.getPageNo(), student.getPageSize());
//            List<Employee> studentList = employeeMapper.select(student);
//            PageInfo<Employee> pageInfo = new PageInfo(studentList);
//            response.setData(pageInfo);
        }

        return response;
    }

    @Override
    public Response arrearsStudent(Request<Student> tParams) {
        Response response = Response.newResponse();
        Student student = tParams.getData();
        if (student == null) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            PageHelper.startPage(student.getPageNo(), student.getPageSize());
            List<Student> studentList = studentMapper.selectarrearsStudent(student);
            //student.setPkEmployee(employee.getPkEmployee());
            PageInfo page = new PageInfo(studentList);
            response.setTotal(page.getTotal());
            if (studentList.size() > 0) {
                for (Student list : studentList) {
                    ClassinfoStudent classinfoStu = getClassinfoStu(list.getPkStudent());
                    if (classinfoStu.getPkClassinfo() != null && !"".equals(classinfoStu.getPkClassinfo())) {
                        Classinfo classinfo = classinfoMapper.selectByPrimaryKey(classinfoStu.getPkClassinfo());
                        list.put(LinkEntity.CLASS_INFO_ENTITY, classinfo);
                        utilsService.setSysDictKeyValue(list, classinfo.getGrade(), LinkEntity.GTADE_ENTITY);
                    } else {
                        list.put(LinkEntity.CLASS_INFO_ENTITY, new Classinfo());
                    }


                }
            }
            response.setData(studentList);


        } catch (Exception e) {
            message = e.getMessage();
            return response.FIND_FAIL(message);
        }


        return response;
    }

    @Override
    public List<Student> selectByParent(String pkLinkman) {
        List<Student> studentList = studentMapper.selectByParent(pkLinkman);
        return studentList;
    }

    @Override
    public Response selectStudentListByPkClassInfo(ResultEntity entity) {

        Response response = Response.newResponse();
        String pkClassInfo = entity.getPkClassInfo();
        if (null == pkClassInfo || "".equals(pkClassInfo)) {
            return response.PARAMS_ISNULL();
        }
        Integer pageNo = entity.getPageNo();
        Integer pageSize = entity.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<ResultEntity> students = studentMapper.selectStudentListByPkClassInfo(entity);
        response.setData(students);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public Response saveForCollect(Request<Student> tParams) {

        Response response = Response.newResponse();
        Student student = tParams.getData();
        if (student == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = student.getPkStudent().trim();
        int iReuslt = 1;
        String message = "";
        try {

            if (student.getPkStudent() == null || student.getPkStudent().equals("")) {
                student.setPkStudent(GuidUtils.getGuid());
            }
            student.setCode(sysAutoCodeService.getCode("addstudent"));
            student.setCreationDate(new Date());
            student.setLasteditDate(new Date());
            student.setIsvalid(1);
            student.setPkSysUser(tParams.getCurrentUser());
            iReuslt = studentMapper.insertSelectiveCollect(student);

//            保存学生的附加信息
            Map<String, Object> map = student.getMap();
            if (map != null) {

//
//                家庭信息
                List<Linkman> linkmanList = DataUtils.objectToList(map.get(Light.LINKMAN), Linkman.class);
                if (linkmanList != null && linkmanList.size() > 0) {
                    for (Linkman linkman : linkmanList) {
                        if (linkman.getPkLinkman() == null || linkman.getPkLinkman().equals("")) {
                            linkman.setPkLinkman(GuidUtils.getGuid());
                        }
                        if (linkman.getCode() == null || linkman.getCode().equals("")) {
                            linkman.setCode(sysAutoCodeService.getCode("linkman"));
                        }
                        linkman.setPkStudent(student.getPkStudent());
                        linkman.setCreationDate(new Date());
                        linkman.setLasteditDate(new Date());
                        linkmanMapper.insertSelectiveCollect(linkman);
                        StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
                        studentLinkmanKey.setPkStudent(student.getPkStudent());
                        studentLinkmanKey.setPkLinkman(linkman.getPkLinkman());
                        studentLinkmanMapper.insertSelectiveCollect(studentLinkmanKey);
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
    public Response findByClassinfo(Request<Student> request) {
        Response response = Response.newResponse();
        Student data = request.getData();

        String message = "";

        if (data == null){
            return response.PARAMS_ISNULL();
        }
        List<Student> select = studentMapper.select(data);
        response.setData(select);

        return response;
    }

    @Override
    public Response getStudentIdCode(Request<String> tParm) {
        Response response = Response.newResponse();
        String pkClassinfo = tParm.getData();
        if (pkClassinfo == null || "".equals(pkClassinfo)){
            return response.PARAMS_ISNULL();
        }
        String message = "";

        String startCode = studentMapper.selectStudentIdStartCode(pkClassinfo);
        if (startCode == null || startCode.equals("")){
            startCode="2000";
        }
        String s = studentMapper.selectStudentIdEndCode(startCode);
        int i = Integer.parseInt(s);

        i = i +1;
        String str = String.format("%06d", i);
        String result = startCode + str;
        response.setData(result);


        return response;
    }

    public ClassinfoStudent getClassinfoStu(String pkStudent) {
        ClassinfoStudent student = new ClassinfoStudent();
        student.setPkStudent(pkStudent);
        List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.selectPkClassinfo(student);
        if (classinfoStudentList.size() > 0) {
            return classinfoStudentList.get(0);
        }
        return student;
    }


}
