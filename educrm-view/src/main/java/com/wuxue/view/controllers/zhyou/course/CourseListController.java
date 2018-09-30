package com.wuxue.view.controllers.zhyou.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.CourseClient;
import com.wuxue.view.client.course.CourseLessonChapterClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.client.system.SysRoleClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 课程管理
 */
@Controller
@RequestMapping(value = "/course/courseList")
public class CourseListController extends BaseController implements IQueryByPagingController<Course,Map<String,Object>>,
        IQueryController<Course,String>,IEditController<Course,String>,
        IDeleteController<Course,String>,ISaveController<Course,String>{

    @Autowired
    CourseClient courseClient;
    @Autowired
    CourseLessonChapterClient courseLessonChapterClient;
    @Autowired
    SysDictValuesClient sysDictValuesClient;
    @Autowired
    SysRoleClient sysRoleClient;

    /**
     * 课程列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Course course)  {
        String[] split = SessionCache.getSysRole().split(",");
        SysRole sysRole;
        int flag = 0;
        for (int i = 0; i < split.length; i++) {
            sysRole = new SysRole();
            sysRole.setPkSysRole(split[i]);
            Response<SysRole> byPrimaryKey = sysRoleClient.findByPrimaryKey(sysRole);
            if(byPrimaryKey.getData().getCode() != null && byPrimaryKey.getData().getCode().equals("admin")){
                flag = 1;
            }
        }
        model.addAttribute("flag",flag);
        return "/zhyou/course/courselist";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Course course, Integer sEcho, Integer start, Integer length) {
        course.setPageNo((start/length)+1);
        course.setPageSize(length);

        Response<PageInfo<Course>> resp=courseClient.find(course);
        List<Course> data = resp.getData().getList();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(resp.getData().getTotal(),resp.getData().getTotal(),data);
    }


    /**
     * 编辑课程 / 跳转页面
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
            //编辑
            Response<Course> response=courseClient.findByPrimaryKey(course);
            model.addAttribute("course",response.getData());
        }else{
            //新增
            model.addAttribute("courseAdd",1);
        }
        return "/zhyou/course/addcourses";
    }


    /**
     * 删除课程
     * @param request
     * @param imodel
     * @param course
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model imodel, Course course) {
        String  pk=course.getPkCourse();
        return courseClient.delete(pk);
    }

    /**
     * 添加课程
     * @param request
     * @param model
     * @param course
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Course course) {
        String response;
        //若主键为空，则执行添加对象赋值
        if(course.getPkCourse()==null||course.getPkCourse()==""){
            course.setCreator(SessionCache.getUserCode());
            course.setModifier(SessionCache.getUserCode());
            course.setIsissue(0);//草稿
            //save
            response=courseClient.save(course);
            return  response;
        }else{
            //添加修改人usercode
            course.setModifier(SessionCache.getUserCode());
            response= courseClient.save(course);
        }

        return response;
    }

    //提交课程,创建审核对象
    @RequestMapping("/submitCourse")
    @ResponseBody
    public String submitCourse(Course course){
        course.setIsissue(1);//提交
        course.setModifier(SessionCache.getUserCode()); //修改用户
//        course.setPkCourse(GuidUtils.getGuid());
        String save = courseClient.save(course);//修改 课程 isissue 状态
        Response response = JSON.parseObject(save,new TypeReference<Response>(){});
        course.setPkCourse((String) response.getData());
        return  courseClient.audit(course);//创建 审核对象
    }

    /**
     * 查询章节
     * @param request
     * @param model
     * @param courseLessonChapter
     * @return
     */
    @RequestMapping(value ="/courseware")
    public String courseware(HttpServletRequest request, Model model, CourseLessonChapter courseLessonChapter) {
        String pkCourse=request.getParameter("pkCourse");
        String flag=request.getParameter("flag");
        courseLessonChapter.setPkCourse(pkCourse);

        courseLessonChapter.setPageSize(null);
        courseLessonChapter.setPageNo(null);
        Course course = new Course();
        course.setPkCourse(pkCourse);
        Response<Course> courseResponse = courseClient.findByPrimaryKey(course);
        Response<List<CourseLessonChapter>> response=courseLessonChapterClient.find(courseLessonChapter);

        model.addAttribute("pkCourse",pkCourse);
        model.addAttribute("course",courseResponse.getData());
        model.addAttribute("lescha",response.getData());
        if(flag != null && !flag.equals("")) {
            model.addAttribute("flag", Integer.valueOf(flag));
        }
        return "/zhyou/course/courseware";
    }



    /**
     * 节  编辑、添加/页面跳转
     * @param request
     * @param model
     * @param courseLessonChapter
     * @return
     */
    @RequestMapping(value ="/addware")
    public String addware(HttpServletRequest request, Model model, CourseLessonChapter courseLessonChapter) {
        String str=request.getParameter("pkCourse");
        String strCH=request.getParameter("pkChapter");
        String strCL=request.getParameter("pkCourseLesson");

        if(str!=null&&str!=""){
            //添加节
            model.addAttribute("isNot",1);
            courseLessonChapter.setPkCourse(str);
            courseLessonChapter.setPkChapter(strCH);
            model.addAttribute("Lessonname",courseLessonChapter);
        }else if (strCL!=null&&strCL!=""){
            //编辑
            courseLessonChapter.setPkCourseLesson(strCL);
            Response response=courseLessonChapterClient.findbuPk(courseLessonChapter);

            model.addAttribute("Lessonname",response.getData());
            model.addAttribute("isNot",0);
        }
        return "/zhyou/course/class";
    }

    /**
     * 添加资料
     * @param request
     * @param model
     * @param linkman
     * @return
     */
    @RequestMapping(value ="/adddata")
    public String adddata(HttpServletRequest request, Model model, Linkman linkman) {
        return "/zhyou/course/data";
    }
    /**
     * 添加章、编辑  --页面跳转
     * @param request
     * @param model
     * @param courseLessonChapter
     * @return
     */
    @RequestMapping(value ="/chaptername")
    public String chaptername(HttpServletRequest request, Model model,CourseLessonChapter courseLessonChapter) {

        String str=request.getParameter("pkCourse");
        String strCL=request.getParameter("pkCourseLesson");
        if(str!=null&&str!=""){
            //添加章
            model.addAttribute("isNot",1);
            courseLessonChapter.setPkCourse(str);
            model.addAttribute("chaptername",courseLessonChapter);
        }else if (strCL!=null&&strCL!=""){
            //编辑
            courseLessonChapter.setPkCourseLesson(strCL);
            Response response=courseLessonChapterClient.findbuPk(courseLessonChapter);

            model.addAttribute("chaptername",response.getData());
            model.addAttribute("isNot",0);
        }
        return "/zhyou/course/chaptername";
    }


    /**
     *编辑/添加章节  操作
     * @return
     */
    @RequestMapping(value="/editOrAddChapter")
    @ResponseBody
    public String editOrAddChapter(HttpServletRequest request, Model model,
                                    CourseLessonChapter courseLessonChapter){
        if (courseLessonChapter.getPkCourse()!=null
                   &&courseLessonChapter.getPkCourse()!=""
                   &&(courseLessonChapter.getPkCourseLesson()==null
                   ||courseLessonChapter.getPkCourseLesson()=="")){

                if(courseLessonChapter.getPkChapter()==""||courseLessonChapter.getPkChapter()==null){
                    courseLessonChapter.setPkChapter(GuidUtils.getGuid());//新建章
                }

                courseLessonChapter.setPkCourseLesson(GuidUtils.getGuid());
                courseLessonChapter.setStatus(0);
                courseLessonChapter.setIsdel(1);
                courseLessonChapter.setCreator(SessionCache.getUserName());
                courseLessonChapter.setCreationDate(new Date());
                courseLessonChapter.setModifier(SessionCache.getUserName());
                courseLessonChapter.setLasteditDate(new Date());
                return  courseLessonChapterClient.save(courseLessonChapter);
           }else{
                return courseLessonChapterClient.save(courseLessonChapter);
           }

    }

    /**
     * sort
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/sortlist")
    @ResponseBody
    public  String sortlist(HttpServletRequest request, Model model,String id){
      /*  String []  strs= id.split(",");
        List<CourseLessonChapter> lessonChapterrs=new ArrayList<>();
        for (String s: strs) {
            CourseLessonChapter courseLessonChapter=new CourseLessonChapter();
            courseLessonChapter.setPkCourseLesson(s);
            lessonChapterrs.add(courseLessonChapter);
        }*/
        return courseLessonChapterClient.updatesort(id);
    }


    @ResponseBody
    @RequestMapping(value ="/deleteChapter")
    public String deleteChapter(HttpServletRequest request, Model model,CourseLessonChapter courseLessonChapter) {
//        String s = courseLessonChapter.getPkCourseLesson();
        return courseLessonChapterClient.delete(courseLessonChapter.getPkCourseLesson());
    }


}
