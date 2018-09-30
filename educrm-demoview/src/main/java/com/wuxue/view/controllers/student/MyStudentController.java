package com.wuxue.view.controllers.student;


import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.InterviewClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.student.StudentSpecialtyClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 意向学生
 */
@Controller
@RequestMapping(value = "/student/mystudent")
public class MyStudentController extends BaseController
        implements IQueryController<Student, String>, ISaveController<Student, String>,IQueryByPagingController<Student,Map<String,Object>>,
        ICreateController<Student, String>, IEditController<Student, String>, IDeleteController<Student, String>{

    @InitBinder("student")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stu.");
    }

    @Autowired
    private StudentClient studentClient;
    @Autowired
    private StudentGetDataUtils studentGetDataUtils;
    @Autowired
    private StudentGetDataUtils linkMansUtils;
    @Autowired
    private StudentSpecialtyClient studentSpecialtyClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private InterviewClient interviewClient;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    /**
     * 初始化界面下拉参数
     *
     * @param model
     * @param pkDomain
     */
    private void initPageAttribute(Model model, String pkDomain) {
        //证件类型
        sysDictUtils.setModeAttribute(model, "idkind", SysDicEmnuUtils.ID_KIND);
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        //校区
        domainUtils.setModeAttribute(model, "domain", pkDomain);
        //学历
        sysDictUtils.setModeAttribute(model, "educations", SysDicEmnuUtils.EDUCATIONS);

        //学生分类
        sysDictUtils.setModeAttribute(model, "studentclass", SysDicEmnuUtils.STUDENT_CLASS);
        //身体健康
        sysDictUtils.setModeAttribute(model, "health", SysDicEmnuUtils.HEALTH);
        //心里健康
        sysDictUtils.setModeAttribute(model, "mentalhealth", SysDicEmnuUtils.MENTAL_HEALTH);
        //线索来源
        sysDictUtils.setModeAttribute(model, "channel", SysDicEmnuUtils.CHANNEL);
        //国籍
        sysDictUtils.setModeAttribute(model, "country", SysDicEmnuUtils.COUNTRY);
        //母语
        sysDictUtils.setModeAttribute(model, "motherlan", SysDicEmnuUtils.MOTHER_LAN);
        //民族
        sysDictUtils.setModeAttribute(model, "nation", SysDicEmnuUtils.NATION);
        //招生老师
        employeeUtils.setModeAttribute(model, "employees");
    }

    /**
     * 意向学生列表
     *
     * @param model
     * @param student
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Student student) {
        /*Response<List<Student>> listResponse = studentClient.find(student);
        List<Student> data = listResponse.getData();
        List<Student> students = new ArrayList<>();
        if (data != null && data.size() > 0) {
            for (Student datum : data) {
                if (datum.getIstype() == 0) {
                    students.add(datum);
                }
            }
        }
        model.addAttribute("student", students);
        model.addAttribute("sEcho", null);
        model.addAttribute("iTotalRecords", 500);
        model.addAttribute("iTotalDisplayRecords",500);*/
        model.addAttribute("isType",3);

        return "/student/studentList";
    }


    /**
     * 修改学生
     *
     * @param model
     * @param student
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Student student) {

        model.addAttribute("URL",request.getServletPath());
        model.addAttribute("empkey",2);

        Response<Student> byPrimaryKey = studentClient.findByPrimaryKey(student);
        Student stu = byPrimaryKey.getData();
        initPageAttribute(model, stu.getPkDomain());
        model.addAttribute("student", stu);
        Map<String, Object> map = stu.getMap();
        //监护人资料
        LinkingUtils.setModeAttribute(model,"linkMans",map.get(Light.LINKMAN),Linkman.class);
        //特长爱好
        LinkingUtils.setModeAttribute(model,"specialty",map.get(Light.STUDENT_SPECIALTY),StudentSpecialty.class);
        model.addAttribute("bykey", 1);
        //招生老师
        String pkEmployee = request.getParameter("pkEmployee");
        if(pkEmployee == null || "".equals(pkEmployee)){
            employeeUtils.setModeAttribute(model,"employee",stu.getPkSysUser());
        }else {
            employeeUtils.setModeAttribute(model,"employee",pkEmployee);
        }



        //面试信息
        StudentInterview studentInterview = new StudentInterview();
        Response<List<StudentInterview>> listResponse = interviewClient.find(studentInterview);
        List<StudentInterview> data = listResponse.getData();
        List<StudentInterview> interviews = new ArrayList<>();
        if (data != null && data.size() > 0) {
            for (StudentInterview datum : data) {
                if (student.getPkStudent().equals(datum.getPkStudent())) {
                    interviews.add(datum);
                }
            }
            if (interviews.size() > 0) {
                //面试科目
                sysDictUtils.setModeAttribute(model, "project", SysDicEmnuUtils.STUDENT_PROJECT);
                //面试老师
//                List<Employee> employeelist = employeeUtils.getEmployees();
                // model.addAttribute("employees",employees);
                model.addAttribute("interviews", interviews);
//                model.addAttribute("interviewkey", 2);
            }
        }
        return "/student/student";
    }

    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Student student) {
        initPageAttribute(model, SessionCache.getPkdomain());
        model.addAttribute("empkey",1);
        String servletPath = request.getServletPath();
        model.addAttribute("URL",servletPath);

        student.setPkStudent(GuidUtils.getGuid());
        student.setCode(sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.ADD_STUDENT));
        student.setBirthday(new Date());
        student.setPassportDate(new Date());
        student.setCreator(SessionCache.getUserCode());
        student.setCreationDate(new Date());
        student.setModifier(SessionCache.getUserCode());
        student.setLasteditDate(new Date());
        model.addAttribute("student", student);
        return "/student/student";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Student student) {
        student.setPkDomain(SessionCache.getPkdomain());
        String byKey = request.getParameter("byKey");
        if (byKey != null) {
            int i = Integer.parseInt(byKey);
            if (i == 1) {
                student.setCreator(SessionCache.getUserCode());
            }
        }
        student.setModifier(SessionCache.getUserCode());
        return studentClient.save(student);
    }

    /**
     * 添加面试
     *
     * @param student
     * @param model
     * @return
     */
    @RequestMapping(value = "/addInterview")
    public String interview(Student student, Model model) {
        Response<Student> byPrimaryKey = studentClient.findByPrimaryKey(student);
        model.addAttribute("stu", byPrimaryKey);
        return "/student/addInterview";
    }

    /**
     * 删除
     *
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Student student) {
        String response = studentClient.delete(student.getPkStudent());
        return response;
    }

    /**
     * 员工跳转页面
     * @param request
     * @param model
     * @param student
     * @return
     */
    @RequestMapping(value = "/getEmployees",method = RequestMethod.GET)
    public String getEmployees(HttpServletRequest request, Model model, Student student) {

        List<Employee> employees = employeeUtils.getEmployees();
        model.addAttribute("employees",employees);

        String empid = request.getParameter("empid");
        String capid = request.getParameter("capid");
        model.addAttribute("empid",empid);
        model.addAttribute("capid",capid);

        return "/model/employeeslistModel";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Student student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);
        //根据登录用户查询学生
        student.setPkSysUser(SessionCache.getUserCode());
        if(student.getIstype()==3){
            student.setIstype(null);
        }

        Response<List<Student>> listResponse = studentClient.find(student);
        List<Student> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }
}
