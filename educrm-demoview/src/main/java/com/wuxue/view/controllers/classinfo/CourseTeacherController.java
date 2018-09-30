package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.CourseTeacher;
import com.wuxue.model.Employee;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.CourseTeacherClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/classinfo/courseTeacher")
public class CourseTeacherController extends BaseController
        implements IQueryController<CourseTeacher, String>, ISaveController<CourseTeacher, String>,
        ICreateController<CourseTeacher, String>, IEditController<CourseTeacher, String>, IDeleteController<CourseTeacher, String>,IQueryByPagingController<CourseTeacher,Map<String,Object>> {

    @Autowired
    private CourseTeacherClient courseTeacherClient;
    @Autowired
    private EmployeeClient employeeClient;

    @InitBinder("courseTeacher")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("cou.");
    }

    /**
     *
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, CourseTeacher courseTeacher) {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/classinfo/courseTeacherList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, CourseTeacher courseTeacher, Integer sEcho, Integer start, Integer length) {
        courseTeacher.setPageNo((start/length)+1);
        courseTeacher.setPageSize(length);

        Response<List<CourseTeacher>> listResponse = courseTeacherClient.find(courseTeacher);
        List<CourseTeacher> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     *
     * @param courseTeacher
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, CourseTeacher courseTeacher) {
        Response<CourseTeacher> byPrimaryKey = courseTeacherClient.findByPrimaryKey(courseTeacher);
        model.addAttribute("courseTeacher", byPrimaryKey.getData());
        return "/classinfo/editCourseTeacher";
    }



    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, CourseTeacher courseTeacher) {
//        String oldPkClassinfo = request.getParameter("oldPkClassinfo");
//        courseTeacher.put(Light.OLD_PK_CLASSINFO,oldPkClassinfo);
        courseTeacher.setCreator(SessionCache.getUserCode());
        courseTeacher.setModifier(SessionCache.getUserCode());
        courseTeacher.setIsvalid(1);

        String response = courseTeacherClient.save(courseTeacher);
        return response;

    }


    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, CourseTeacher courseTeacher) {

        Employee employee = new Employee();
        employee.setJobPost("1");
        Response<List<Employee>> listResponse = employeeClient.find(employee);
        model.addAttribute("employee",listResponse.getData());


        return "/model/courselistModel";
    }

    /**
     * 删除
     *
     * @param courseTeacher
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, CourseTeacher courseTeacher) {
        courseTeacher.setIsvalid(0);
        String response = courseTeacherClient.delete(courseTeacher);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/getTeacherCourse",method = RequestMethod.POST)
    public Response<List<CourseTeacher>> getTeacherCourse(HttpServletRequest request, Model model, CourseTeacher courseTeacher) {
        Response<List<CourseTeacher>> teacherCourse = courseTeacherClient.getTeacherCourse(courseTeacher);
        return teacherCourse;
    }




}
