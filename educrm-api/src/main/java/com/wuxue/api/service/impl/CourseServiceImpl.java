package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.CourseService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @Description: 课程表  接口实现类
* @author wanghao
* @date  12:30 2018/3/13
* @version V1.0
*/
@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseEvaluateMapper courseEvaluateMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseLessonChapterMapper courseLessonChapterMapper;
    @Autowired
    private StudentAssignMapper studentAssignMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 课程集合
     * @param tParams  条件
     * @return
     */
    @Override
    public Response<PageInfo<Course>> find(Request<Course> tParams) {
        Response response = Response.newResponse();
        Course course = tParams.getData();

        if(course== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = course.getPkCourse();
        if(primaryKey !=null && !primaryKey.equals("")){
            Course byPrimaryKey = courseMapper.selectByPrimaryKey(primaryKey);
            if(byPrimaryKey.getTeacher()!=null&&byPrimaryKey.getTeacher()!=""){
                Employee employee=employeeMapper.selectByPrimaryKey(byPrimaryKey.getTeacher());
                byPrimaryKey.put("teacherName",employee.getCaption());
            }

//            获取关联的课程章目表
            CourseLessonChapter courseLessonChapter = new CourseLessonChapter();
            courseLessonChapter.setPkCourse(byPrimaryKey.getPkCourse());
            List<CourseLessonChapter> chapterList = courseLessonChapterMapper.select(courseLessonChapter);
            byPrimaryKey.put(Light.COURSE_LESSON_CHAPTER,chapterList);

//            获取课程下学习学员
            StudentAssign studentAssign = new StudentAssign();
            studentAssign.setPkCourse(byPrimaryKey.getPkCourse());
            List<StudentAssign> assignList = studentAssignMapper.select(studentAssign);
            List<Student> studentList = new ArrayList<>();
            if (assignList.size() > 0) {
                for (StudentAssign assign : assignList) {
                    Student student = studentMapper.selectByPrimaryKey(assign.getPkStudent());
                    studentList.add(student);
                }
            }
            byPrimaryKey.put(Light.COURSE_STUDENT,studentList);

//            获取课程的所有评价
            CourseEvaluate courseEvaluate = new CourseEvaluate();
            courseEvaluate.setPkCourse(byPrimaryKey.getPkCourse());
            List<CourseEvaluate> evaluateList = courseEvaluateMapper.select(courseEvaluate);
            List<CourseEvaluate> evaluateList2 = new ArrayList<>();
            if (evaluateList.size() > 0) {
                for (CourseEvaluate evaluate : evaluateList) {
                    Student student = studentMapper.selectByPrimaryKey(evaluate.getPkStudent());
                    evaluate.put(Light.COURSE_EVALUATE_STUDENT,student);
                    evaluateList2.add(evaluate);
                }
            }
            byPrimaryKey.put(Light.COURSE_EVALUATE,evaluateList2);

            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(course.getPageNo(),course.getPageSize());
            if(tParams.getCurrentUser() != null) {
                course.setCreator(tParams.getCurrentUser());
                Employee employee1 = new Employee();
                employee1.setSysUser(tParams.getCurrentUser());
                List<Employee> employeeList = employeeMapper.select(employee1);
                if (employeeList.size() > 0) {
                    course.setTeacher(employeeList.get(0).getPkEmployee());
                    course.setIsType(0);
                } else {
                    course.setIsType(1);
                }
            }
            List<Course> courseList = courseMapper.select(course);
            PageInfo<Course> pageInfo = new PageInfo<>(courseList);
            if(pageInfo.getList().size()>0){
                for ( Course c:pageInfo.getList()) {
                    if(c.getTeacher()!=null&&c.getTeacher()!=""){
                        Employee employee=employeeMapper.selectByPrimaryKey(c.getTeacher());
                        if(employee!=null){
                            c.put("teacherName",employee.getCaption());
                        }
                    }else{
                        c.put("teacherName","未指定老师");
                    }

                }
            }


            response.setData(pageInfo);
            //response.setTotal(linkmanMapper.countBy(linkman));

        }
        return response;
    }

    @Override
    public Response save(Request<Course> tParams) {
        Response response = Response.newResponse();
        Course course = tParams.getData();

        if(course== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = course.getPkCourse();
        Course select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = courseMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                course.setLasteditDate(new Date());
                iReuslt = courseMapper.updateByPrimaryKeySelective(course);
            } else {
//                linkman.setPkLinkman(GuidUtils.getGuid());
                course.setPkCourse(GuidUtils.getGuid());
                course.setCreator(tParams.getCurrentUser());
                course.setModifier(tParams.getCurrentUser());
                course.setCreationDate(new Date());
                course.setLasteditDate(new Date());
                course.setIsdel(0);
                iReuslt = courseMapper.insertSelective(course);
                response.setData(course.getPkCourse());
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=courseMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response audit(Request<Course> tParams) {
        Response response = Response.newResponse();
        Course course = tParams.getData();

        if(course== null && course.getPkCourse() == null){
            return  response.PARAMS_ISNULL();
        }

        String message;
        try {
            Review review = new Review();
            review.setPkData(course.getPkCourse());
            review.setPkDataCaption(course.getCaption());
            review.setIsdel(0);
            review.setType(0);
            List<Review> reviewList = reviewMapper.select(review);
            if (reviewList.size() > 0) {
                //若是驳回的请求，再次提交。则更改review状态为0
                if(reviewList.get(0).getStatus()==3){
                    reviewList.get(0).setStatus(0);
                    reviewList.get(0).setCotent("课程第一次审核");
                    reviewMapper.updateByPrimaryKeySelective(reviewList.get(0));
                }else {
                    return response.SAVE_DOUBLE();
                }
            }else{
                review.setStatus(0);
                review.setPkReview(GuidUtils.getGuid());
                review.setCotent("课程第一次审核");
                review.setCreator(course.getModifier());
                review.setModifier(course.getModifier());
                review.setCreationDate(new Date());
                review.setLasteditDate(new Date());
                reviewMapper.insertSelective(review);
            }

        }catch (Exception ex){
            message = ex.getMessage();
            return response.AUDIT_FAIL(message);
        }
        return response;
    }
}
