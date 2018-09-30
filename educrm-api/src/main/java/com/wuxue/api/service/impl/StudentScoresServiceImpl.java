package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.StudentScoresService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateTimeUtils;
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

@Service("studentScoresService")
public class StudentScoresServiceImpl implements StudentScoresService {

    @Autowired
    private StudentScoresMapper studentScoresMapper;
    @Autowired
    private UtilsService utilsService;
    @Autowired
    private ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SysDictValuesMapper sysDictValuesMapper;
    @Autowired
    private TeacherCommentMapper teacherCommentMapper;


    @Override
    public Response delete(Request<String> tParams) {
        return null;
    }

    @Override
    public Response find(Request<StudentScores> tParams) {
        Response response = Response.newResponse();
        StudentScores studentScores = tParams.getData();

        if (studentScores == null) {
            return response.PARAMS_ISNULL();
        }
        if (studentScores.getPkStudentTestPlansScores() != null && !"".equals(studentScores.getPkStudentTestPlansScores())) {
            StudentScores byPrimaryKey = studentScoresMapper.selectByPrimaryKey(studentScores.getPkStudentTestPlansScores());

            //学生
            utilsService.setStudentKeyValue(byPrimaryKey, byPrimaryKey.getPkStudent(), LinkEntity.STUDENT_ENTITY);
            //老师
            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getPkEmployee(), LinkEntity.USER_ENTITY);
            //课程
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getPkCource(), LinkEntity.SYSDIC_ENTITY);
            //班级
            utilsService.setClassInfoKeyValue(byPrimaryKey, byPrimaryKey.getPkClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
            //考试类型
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getPkPlans(), LinkEntity.TEST_TYPE_ENTITY);
            //考试名称
            utilsService.setScoreCaptionKeyValue(byPrimaryKey, byPrimaryKey.getPkStudentTestPlans(), LinkEntity.SCORE_CAPTION_ENTITY);
            //学期
            utilsService.setScoreCaptionKeyValue(byPrimaryKey, byPrimaryKey.getTerm(), LinkEntity.TERM_ENTITY);
            response.setData(byPrimaryKey);
        } else {
            //获取班级学生成绩
            PageHelper.startPage(studentScores.getPageNo(), studentScores.getPageSize());
            List<StudentScores> scoresList = studentScoresMapper.select(studentScores);
            PageInfo page = new PageInfo(scoresList);
            response.setTotal(page.getTotal());
            if (scoresList != null && scoresList.size() > 0) {
                for (StudentScores scores : scoresList) {
                    //学生
                    utilsService.setStudentKeyValue(scores, scores.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                    //老师
                    utilsService.setEmployeeKeyValue(scores, scores.getPkEmployee(), LinkEntity.USER_ENTITY);
                    //课程
                    utilsService.setSysDictKeyValue(scores, scores.getPkCource(), LinkEntity.SYSDIC_ENTITY);
                    //考试类型
                    utilsService.setSysDictKeyValue(scores, scores.getPkPlans(), LinkEntity.TEST_TYPE_ENTITY);
                    //考试名称
                    utilsService.setScoreCaptionKeyValue(scores, scores.getPkStudentTestPlans(), LinkEntity.SCORE_CAPTION_ENTITY);
                    utilsService.setSysDictKeyValue(scores, scores.getTerm(), LinkEntity.TERM_ENTITY);
                }
            }

            response.setData(scoresList);
        }


        return response;
    }

    @Override
    public Response save(Request<StudentScores> tParams) {
        Response response = Response.newResponse();
        StudentScores studentScores = tParams.getData();
        if (studentScores == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = studentScores.getPkStudentTestPlansScores();
        StudentScores select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = studentScoresMapper.selectByPrimaryKey(primaryKey);
        }
        int iResult = 0;
        String message = "";
        try {
            if (select != null) {
                studentScores.setLasteditDate(new Date());
                iResult = studentScoresMapper.updateByPrimaryKeySelective(studentScores);

            } else {
                studentScores.setCreationDate(new Date());
                studentScores.setLasteditDate(new Date());
                iResult = studentScoresMapper.insertSelective(studentScores);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iResult > 0) {
            return response;
        }

        return response.SAVE_FAIL(message);
    }

    @Override
    public Response saveAll(Request<List<StudentScores>> tParams) {
        Response response = Response.newResponse();
        List<StudentScores> scoresList = tParams.getData();
        if (scoresList == null || scoresList.size() == 0) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            for (StudentScores studentScores : scoresList) {

                String primaryKey = studentScores.getPkStudentTestPlansScores();
                StudentScores select = null;
                if (primaryKey != null && !"".equals(primaryKey)) {
                    select = studentScoresMapper.selectByPrimaryKey(primaryKey);
                }
                if (select != null) {
                    studentScores.setLasteditDate(new Date());
                    studentScoresMapper.updateByPrimaryKeySelective(studentScores);

                } else {
                    studentScores.setCreationDate(new Date());
                    studentScores.setLasteditDate(new Date());
                    studentScoresMapper.insertSelective(studentScores);
                }

            }
        } catch (Exception e) {
            message = e.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Override
    public Response findSchoolReport(Request<Student> listRequest) {
        Response response = Response.newResponse();
        Student data = listRequest.getData();

        if (data == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = data.getPkStudent();
        if (primaryKey != null && primaryKey != "") {

            Object dataYear = data.get(Light.YEAR);
            Object term = data.get(Light.TERM);
            Date date = DateTimeUtils.stringToDateYYYY(dataYear.toString());


            //查询学生的考试类型
            SysDictValues sysDictValues = new SysDictValues();
            sysDictValues.setPkSysDict(Light.SYS_DICT_TESTPLANS);
            List<SysDictValues> select1 = sysDictValuesMapper.select(sysDictValues);
            Student student = studentMapper.selectByPrimaryKey(data.getPkStudent());

            SysDictValues sysDictValues1 = sysDictValuesMapper.selectByPrimaryKey(term.toString());

            String title = dataYear.toString() + "年" + sysDictValues1.getCaption();
            student.put("title", title);

            //查询学生的所有课程
            StudentScores scoresCourse = new StudentScores();
            scoresCourse.setTerm(term.toString());
            scoresCourse.setYear(date);
            scoresCourse.setPkStudent(student.getPkStudent());
            List<StudentScores> scoresList = studentScoresMapper.selectCourseByStu(scoresCourse);
            if (scoresList.size() > 0) {
                for (StudentScores course : scoresList) {
                    utilsService.setSysDictKeyValue(course, course.getPkCource(), LinkEntity.COURSE_ENTITY);
                    if (select1.size() > 0) {
                        List<StudentScores> list = new ArrayList<>();
                        for (SysDictValues dictValues : select1) {
                            //查询该学生该课程该次考试类型下的成绩
                            StudentScores studentScores = new StudentScores();
                            studentScores.setPkStudent(student.getPkStudent());
                            studentScores.setYear(date);
                            studentScores.setTerm(term.toString());
                            studentScores.setPkPlans(dictValues.getPkSysDictValues());
                            studentScores.setPkCource(course.getPkCource());
                            StudentScores select = studentScoresMapper.selectByCourseAndPalns(studentScores);
                            list.add(select);
                        }
                        TeacherComment teacherComment1 = getTeacherComment(date, student.getPkStudent(), term.toString(), course.getPkCource(), 1);
                        course.put(Light.TEACHER_COMMENTS, teacherComment1);
                        course.put("sysDictValues", list);
                    }
                }
            }
            student.put(Light.SYS_DICT_TESTPLANS, select1);
            student.put("scoresList", scoresList);
            TeacherComment teacherComment = getTeacherComment(date, student.getPkStudent(), term.toString(), null, 0);
            student.put(Light.HEADMASTER_COMMENTS,teacherComment);
            response.setData(student);

        }
        return response;
    }

    public TeacherComment getTeacherComment(Date date, String pkStudent, String term, String cource,Integer type) {
        TeacherComment teacherComment = new TeacherComment();
        teacherComment.setYear(date);
        teacherComment.setSemester(term);
        teacherComment.setPkStudent(pkStudent);
        teacherComment.setDiscipline(cource);
        teacherComment.setType(type);
        List<TeacherComment> select = teacherCommentMapper.select(teacherComment);
        if(select.size()>0){
            return select.get(0);
        }

        return teacherComment;
    }
}
