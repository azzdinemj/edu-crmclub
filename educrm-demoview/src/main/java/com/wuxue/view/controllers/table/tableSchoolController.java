package com.wuxue.view.controllers.table;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Course;
import com.wuxue.model.CourseLessonChapter;
import com.wuxue.model.Linkman;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.CourseClient;
import com.wuxue.view.client.course.CourseLessonChapterClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IDeleteController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 课程管理
 */
@Controller
@RequestMapping(value = "/table/tableschool")
public class tableSchoolController extends BaseController implements
        IQueryController<Course,String>,IEditController<Course,String>,
        IDeleteController<Course,String>,ISaveController<Course,String>{

    @Autowired
    CourseClient courseClient;
    @Autowired
    CourseLessonChapterClient courseLessonChapterClient;
    @Autowired
    SysDictValuesClient sysDictValuesClient;

    /**
     * 课程列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Course course)  {
        return "/table/tableschoollist";
    }


    @RequestMapping("/query2")
    public String query2(HttpServletRequest request, Model model, Course course)  {
        return "/table/tablezhiyinglist";
    }



    @RequestMapping("/query3")
    public String query3(HttpServletRequest request, Model model, Course course)  {
        return "/table/tablejiamenglist";
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

        return "/course/addcourses";
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
        courseClient.save(course);//修改 课程 isissue 状态
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
        courseLessonChapter.setPkCourse(pkCourse);

        courseLessonChapter.setPageSize(null);
        courseLessonChapter.setPageNo(null);
        Response<List<CourseLessonChapter>> response=courseLessonChapterClient.find(courseLessonChapter);

        model.addAttribute("pkCourse",pkCourse);
        model.addAttribute("lescha",response.getData());
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

        Random random=new Random();
        int i=random.nextInt(1000000)+10000;

        if (courseLessonChapter.getPkCourse()!=null
                   &&courseLessonChapter.getPkCourse()!=""
                   &&(courseLessonChapter.getPkCourseLesson()==null
                   ||courseLessonChapter.getPkCourseLesson()=="")){

                if(courseLessonChapter.getPkChapter()==""||courseLessonChapter.getPkChapter()==null){
                    courseLessonChapter.setPkChapter(String.valueOf(i));//新建章
                }

                courseLessonChapter.setPkCourseLesson(String.valueOf(i));

                courseLessonChapter.setStatus(1);
                courseLessonChapter.setIsdel(1);
                courseLessonChapter.setCreator(String.valueOf(i));
                courseLessonChapter.setCreationDate(new Date());
                courseLessonChapter.setModifier(String.valueOf(i));
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



}
