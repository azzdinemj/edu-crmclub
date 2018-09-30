package com.wuxue.view.controllers.zhyou.course;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Course;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.CourseClient;
import com.wuxue.view.client.course.CourseLessonChapterClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 我的课程管理
 */
@Controller
@RequestMapping(value = "/course/courseListUs")
public class CourseListUsController extends BaseController  implements
        IQueryController<Course,String>,IEditController<Course,String>{

    @Autowired
    CourseClient courseClient;
    @Autowired
    CourseLessonChapterClient courseLessonChapterClient;

    @Autowired
    SysDictValuesClient sysDictValuesClient;

    /**
     * 当前登录用户的课程列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Course course)  {
        //当前登录用户的usercode
       String strcode= SessionCache.getUserCode();
       course.setCode(strcode);

        //页码，每页数量
        String str=request.getParameter("pageNo");
        if(str!=null&&str!=""){
            int pageNo=Integer.parseInt(str);
            course.setPageNo(pageNo);
        }else{
            course.setPageNo(Contsants.PAGE_NO);
        }
        course.setPageSize(Contsants.PAGE_SIZE);

        Response<PageInfo<Course>> response=courseClient.find(course);
        PageInfo<Course> pageInfo=response.getData();

        model.addAttribute("course", pageInfo);
        return "/zhyou/course/courselistus";
    }


    /**
     * 编辑课程  我的
     * @param request
     * @param model
     * @param course
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Course course) {
        //查询课程分类（数据字典）
        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict("zhy");
        Response<List<SysDictValues>> responsesysDictValues= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("dict",responsesysDictValues.getData());

        if(course.getPkCourse()!=null&&course.getPkCourse()!=""){
            Response<Course> response=courseClient.findByPrimaryKey(course);
            model.addAttribute("course",response.getData());
        }else{
            //新增
            model.addAttribute("courseAdd",1);
        }
        return "/zhyou/course/addcoursesus";
    }



}
