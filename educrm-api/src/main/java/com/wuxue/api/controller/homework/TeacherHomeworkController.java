package com.wuxue.api.controller.homework;

import com.wuxue.api.service.*;
import com.wuxue.model.*;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/homework/teaHomework")
public class TeacherHomeworkController {


    @InitBinder("homework")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("work.");
    }
    @InitBinder("ids")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("ids.");
    }

    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private ClassinfoEmployeeService classinfoEmployeeService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private EmployeeService employeeService;


    /**
     * 选择班级
     * @param request
     * @param pkEmployee
     * @return
     */
    @RequestMapping(value = "/findClassByTea",method = RequestMethod.POST)
    public Response findStuWork(HttpServletRequest request,String pkEmployee){

        List<ClassinfoEmployee> classinfoEmployeeList = classinfoEmployeeService.selectByTea(pkEmployee);

        Response response = Response.newResponse();
        response.setData(classinfoEmployeeList);


        return response;
    }
    /**
     * 作业详情
     * @param request
     * @param pkHomework
     * @return
     */
    @RequestMapping(value = "/findBykey",method = RequestMethod.POST)
    public Response findBykey(HttpServletRequest request,String pkHomework){

        Response response = homeworkService.findBykey(pkHomework);


        return response;
    }

    /**
     * 保存作业
     * @param request
     * @param homework
     * @return
     */
    @RequestMapping(value = "/saveHomework",method = RequestMethod.POST)
    public Response saveHome(HttpServletRequest request,Homework homework,String pkEmployee,LinkmanList ids){

        List<String> pkClassinfos = ids.getIds();
        Employee employee = employeeService.findById(pkEmployee);
        if (homework.getPkHomework() == null || "".equals(homework.getPkHomework())){

            homework.setPkDomain(employee.getPkDomain());
            homework.setCreator(employee.getSysUser());
        }

        homework.setModifier(employee.getSysUser());
        homework.setHomeworkDate(DateTimeUtils.stringToDate(homework.getHomeworkDateTime()));

        Response response = homeworkService.save(homework,pkClassinfos);


        return response;
    }

    /**
     * 作业科目列表
     * @param request
     * @param pkEmployee
     * @return
     */
    @RequestMapping(value = "/findWorkType",method = RequestMethod.POST)
    public Response findWorkType(HttpServletRequest request,String pkEmployee,String jobPost){

        List<CourseTeacher> list = courseTeacherService.selectByEmp(pkEmployee,jobPost);

        Response response = Response.newResponse();
        response.setData(list);
        return response;
    }

    /**
     * 老师作业列表
     * @param request
     * @param homework
     * @return
     */
    @RequestMapping(value = "/findHomework",method = RequestMethod.POST)
    public Response findHomework(HttpServletRequest request,Homework homework,String jobPost,String pkEmployee){

        List<Homework> list = homeworkService.findHomeworkByTea(homework,jobPost,pkEmployee);

        Response response = Response.newResponse();
        response.setData(list);

        return response;
    }


}
