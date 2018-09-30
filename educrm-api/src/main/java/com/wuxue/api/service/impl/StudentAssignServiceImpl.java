package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.StudentAssignService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.Course;
import com.wuxue.model.CourseLessonChapter;
import com.wuxue.model.StudentAssign;
import com.wuxue.model.StudentLesson;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentAssignService")
public class StudentAssignServiceImpl implements StudentAssignService {
    @Autowired
    StudentAssignMapper studentAssignMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    CourseLessonChapterMapper courseLessonChapterMapper;
    @Autowired
    StudentLessonMapper studentLessonMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    EmployeeMapper employeeMapper;

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
            iReuslt = studentAssignMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentAssign> tParams) {
        Response response = Response.newResponse();
        StudentAssign studentAssign = tParams.getData();

        if (studentAssign == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = studentAssign.getPkStudentAssign();
        if (primaryKey != null && !primaryKey.equals("")) {
            StudentAssign byPrimaryKey = studentAssignMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(studentAssign.getPageNo(), studentAssign.getPageSize());
            List<StudentAssign> timeList = studentAssignMapper.select(studentAssign);
            List<StudentAssign> studentAssignList = new ArrayList<>();
            if (timeList.size() > 0) {
                for (StudentAssign list : timeList) {
                    Course course = courseMapper.selectByPrimaryKey(list.getPkCourse());
                    if(course!=null){
                        if(employeeMapper.selectByPrimaryKey(course.getTeacher())!=null){
                            course.put("teacherName",employeeMapper.selectByPrimaryKey(course.getTeacher()).getCaption());
                        }
                    }
                    list.put(Light.COURSE, course);
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    studentAssignList.add(list);
                }
            }
            PageInfo<StudentAssign> pageInfo = new PageInfo<>(studentAssignList);
            response.setData(pageInfo);
            //response.setTotal(studentAssignMapper.countBy(studentAssign));
        }
        return response;
    }

    @Override
    public Response save(Request<StudentAssign> tParams) {
        Response response = Response.newResponse();
        StudentAssign studentAssign = tParams.getData();

        if (studentAssign == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = studentAssign.getPkStudentAssign();
        StudentAssign select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentAssignMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message = "";
        try {
            if (select != null) {
                studentAssign.setLasteditDate(new Date());
                iReuslt = studentAssignMapper.updateByPrimaryKeySelective(studentAssign);
            } else {
                //判断学生是否已经学习过该课程
                if(studentAssign.getPkCourse()!=null&&studentAssign.getPkStudent()!=null){
                    List<StudentAssign> list= studentAssignMapper.select(studentAssign);
                    if(list.size()>0){
                        return response.SAVE_DOUBLE();
                    }
                }
                studentAssign.setPkStudentAssign(GuidUtils.getGuid());
                studentAssign.setCreationDate(new Date());
                studentAssign.setLasteditDate(new Date());
                studentAssign.setIsdel(0);
                studentAssign.setStatus(0);
                iReuslt = studentAssignMapper.insertSelective(studentAssign);
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
     * 获取
     */
    @Override
    public Response getStudentCourseStatus(Request<StudentAssign> tParams) {
        Response response = Response.newResponse();
        StudentAssign studentAssign = tParams.getData();

        if (studentAssign == null && studentAssign.getPkStudent() == null) {
            return response.PARAMS_ISNULL();
        }

        String message;
        try {
            if (studentAssign.getPkCourse() != null) {

                String schedule = getSchedule(studentAssign);
                response.setData(schedule);

            } else {

                List<StudentAssign> assignList = studentAssignMapper.select(studentAssign);
                List<String> scheduleList = new ArrayList<>();
                if (assignList.size() > 0) {
                    for (StudentAssign assign : assignList) {
                        String schedule = getSchedule(assign);
                        scheduleList.add(schedule);
                    }
                }

                response.setData(scheduleList);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SERVER_ERROR(message);
        }

        return response;
    }

    public String getSchedule(StudentAssign studentAssign) {
        //            获取课程下的所有章节课时
        CourseLessonChapter courseLessonChapter = new CourseLessonChapter();
        courseLessonChapter.setPkCourse(studentAssign.getPkCourse());
        List<CourseLessonChapter> chapterList = courseLessonChapterMapper.select(courseLessonChapter);

//            获取学生已看完的章节课时
        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setPkCourse(studentAssign.getPkCourse());
        studentLesson.setPkStudent(studentAssign.getPkStudent());
        studentLesson.setStatus(1);
        List<StudentLesson> lessonList = studentLessonMapper.select(studentLesson);

        DecimalFormat df = new DecimalFormat("#.00");
        String schedule = df.format((lessonList.size() / chapterList.size()) * 100);

        return schedule;
    }
}
