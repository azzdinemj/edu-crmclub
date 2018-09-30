package com.wuxue.view.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.wuxue.model.Course;
import com.wuxue.model.Employee;
import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.CourseClient;
import com.wuxue.view.client.teacher.EmployeeClient;
import com.wuxue.view.constant.Contsants;
import com.wuxue.view.controller.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 教师管理  中航油
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherListController extends BaseController implements IQueryController<Employee, String>,
       IEditController<Employee, String> {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private CourseClient courseClient;

    /**
     * 员工列表   caption 姓名， short_code 年龄，  sex 性别， address 头像， memo 详情
     * @param model
     * @param employee
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Employee employee) {
        //页码，每页数量
        String str=request.getParameter("pageNo");
        if(str!=null&&str!=""){
            int pageNo=Integer.parseInt(str);
            employee.setPageNo(pageNo);
        }else{
            employee.setPageNo(Contsants.PAGE_NO);
        }
        employee.setPageSize(Contsants.PAGE_SIZE);
        employee.setPkDomain(Contsants.PK_DOMAIN_ZHY);//数据分离
        Response<PageInfo<Employee>> response=employeeClient.findzhyou(employee);
        PageInfo<Employee> pageInfo=response.getData();

        model.addAttribute("employeelist", pageInfo);
        return "/teacher";
}

    /**
     * 查找教师课程页面
     * @param employee
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Employee employee) {
        if(employee.getPkEmployee()!=null&&employee.getPkEmployee()!=""){
            Response<Employee> byPrimaryKey = employeeClient.findByPrimaryKey(employee);
            Employee emp = byPrimaryKey.getData();
            findEmployeeCourse(model,employee);
            model.addAttribute("employee", emp);
            return "/teacherlist";
        }
        return "/teacher";
    }


    /**
     * 查找教师相关的课程
     * @param model
     * @param employee
     */
    public  void  findEmployeeCourse(Model model,Employee   employee){
        Course course=new Course();
        course.setTeacher(employee.getPkEmployee());
        //页码，每页数量
        if(employee.getPageNo()!=null){
            int pageNo=employee.getPageNo();
            employee.setPageNo(pageNo);
        }else{
            employee.setPageNo(Contsants.PAGE_NO);
        }
        Response<PageInfo<Course>> response=courseClient.find(course);
        model.addAttribute("courseList", response.getData());
    }





//    /*
//     *查询列表 modal框
//     *
//     */
//    @RequestMapping("/querylist")
//    public String querylist(HttpServletRequest request, Model model, SysMenu sysMenu) throws IOException {
//        return "/model/teacherModel";
//    }


    /**
     * 根据主键查找老师对象
     * @param employee
     * @return
     */
    @RequestMapping("/findByPrimaryKey")
    @ResponseBody
    public Response findByPrimaryKey(Employee employee) {
        return employeeClient.findByPrimaryKeyzhyou(employee);
    }

}
