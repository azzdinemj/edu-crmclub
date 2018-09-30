package com.wuxue.view.controller.course;

import com.github.pagehelper.PageInfo;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.CourseClient;
import com.wuxue.view.client.course.CourseEvaluateClient;
import com.wuxue.view.client.course.StudentAssignClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.client.teacher.EmployeeClient;
import com.wuxue.view.constant.Contsants;
import com.wuxue.view.controller.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @Description: 课程页面控制器
* @author wanghao
* @date  13:01 2018/3/9
* @version V1.0
*/
@Controller
@RequestMapping("/course")
public class   CourseController extends BaseController
        implements IQueryController<Course, String>{
    @Autowired
    private CourseClient courseClient;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private SysDictValuesClient sysDictValuesClient;

    @Autowired
    private CourseEvaluateClient courseEvaluateClient;

    @Autowired
    private StudentAssignClient studentAssignClient;

    /**
     * 主页查询课程   /详情
     * @param request
     * @param model
     * @param course
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Course course) {
        String strPage=request.getParameter("pageNo");
        String strPk=request.getParameter("pkCourse");
        String types=request.getParameter("types");
        String num=request.getParameter("date2");
        String date=request.getParameter("date");

        //若有主键为查询详情
        if (strPk!=null&&!strPk.equals("")){
            Response<Course> response=courseClient.findByPrimaryKey(course);
            Course courseRemake=response.getData();
            //课程对象
            model.addAttribute("course1",courseRemake);
            Map<String,Object> objectMap=courseRemake.getMap();
            //课程目录
            model.addAttribute("lessonchapter",objectMap.get(Light.COURSE_LESSON_CHAPTER));
            //课程学生
            model.addAttribute("student",objectMap.get(Light.COURSE_STUDENT));
            //课程评价
            model.addAttribute("evaluate",objectMap.get(Light.COURSE_EVALUATE));

            return "/class_details";
        }


        //加载课程类型
        initDict(model);
        //查询课程列表  类型 热度
        if(types!=null&&!types.equals("")){ //类型查询
            course.setTypes(types);
            model.addAttribute("type",types);
        }
        if(num!=null&&!num.equals("")){//最热查询
            course.setNum(1);
            model.addAttribute("hot","hot");
        }
        if(date!=null&&!date.equals("")){//最新查询
            course.setLasteditDate(new Date());
            model.addAttribute("date","date");
        }
        //课程列表 分页
        if(strPage!=null&&!strPage.equals("")){
            int pageNo=Integer.parseInt(strPage);
            course.setPageNo(pageNo);
        }else{
            course.setPageNo(Contsants.PAGE_NO);
        }
        course.setIsissue(Contsants.Course_ISISSUE); //状态
        course.setPageSize(Contsants.PAGE_SIZE);     //每页数量
        course.setCreationDate(new Date());          //关闭倒序
        model.addAttribute("course", courseClient.find(course).getData());
        return "/home";
    }


    //加载课程分类（数据字典）
    private void initDict(Model model){
        SysDictValues sysDictValues=new SysDictValues();
        sysDictValues.setPkSysDict(Contsants.DICT_VALUECOURSE);
        Response<List<SysDictValues>> responsesysDictValues= sysDictValuesClient.find(sysDictValues);
        model.addAttribute("dict",responsesysDictValues.getData());
    }

    /**
     * 根据pkCourse查询所有章节
     * @param request
     * @param model
     * @param course
     * @return
     */
    @RequestMapping("/queryclass")
    public String queryclass(HttpServletRequest request, Model model, Course course){
        String strPk=request.getParameter("pkCourse");//课程主键
        String strclPk=request.getParameter("pkCourseLesson");//节主键
        if (strPk!=null&&strPk!=""&&strclPk!=""&&strclPk!=null){
            //添加学习课程、学生
            StudentAssign studentAssign=new StudentAssign();
            studentAssign.setPkStudent(SessionCache.getPkStudent());
            studentAssign.setPkCourse(strPk);
            studentAssign.setModifier(SessionCache.getF_CAPTION());
            studentAssign.setCreator(SessionCache.getF_CAPTION());
            studentAssignClient.save(studentAssign);

              // 课程、节主键都不为空则为播放视频。点击量加1操作。
            Response<Course> response=courseClient.findByPrimaryKey(course);
            Course course1=response.getData();
            if(course1.getNum()==null||course1.getNum()==0){
                course1.setNum(1);
            }else{
                course1.setNum(course1.getNum()+1);
            }
            courseClient.save(course1);
        }
        //若有主键为查询详情
        if (strPk!=null&&strPk!=""){
            Response<Course> response=courseClient.findByPrimaryKey(course);
            Course course1=response.getData();
            //课程对象
            //model.addAttribute("course1",course1);
            Map<String,Object> objectMap=course1.getMap();
            //课程章节对象
            model.addAttribute("lessonchapter",objectMap.get(Light.COURSE_LESSON_CHAPTER));
            model.addAttribute("pkCouLesson",strclPk);
        }
        return  "/have_class";
    }


    /**
     * 新增评论
     * @param courseEvaluaters
     * @return
     */
    @RequestMapping("/saveEvaluate")
    @ResponseBody
    public String saveEvaluate(CourseEvaluate courseEvaluaters){
        courseEvaluaters.setPkStudent(SessionCache.getPkStudent());
        courseEvaluaters.setCreator(SessionCache.getPhone());
        courseEvaluaters.setModifier(SessionCache.getPhone());
        return courseEvaluateClient.save(courseEvaluaters);
    }



}
