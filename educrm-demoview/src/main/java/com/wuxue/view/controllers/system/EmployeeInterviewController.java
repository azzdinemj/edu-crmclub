package com.wuxue.view.controllers.system;


import com.alibaba.fastjson.JSONObject;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Employee;
import com.wuxue.model.StudentInterview;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.InterviewClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.SysAutcodeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 员工面试
 */
@Controller
@RequestMapping(value = "/system/employeeInterview")
public class EmployeeInterviewController extends BaseController
        implements IQueryController<StudentInterview, String>, ISaveController<StudentInterview, String>,IQueryByPagingController<StudentInterview,Map<String,Object>>,
        ICreateController<StudentInterview, String>, IEditController<StudentInterview, String>, IDeleteController<StudentInterview, String>,
        ISubmitController<StudentInterview, String>, IAuditController<StudentInterview, String> {

    @InitBinder("studentInterview")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stu.");
    }

    @Autowired
    private InterviewClient interviewClient;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysAutcodeClient autcodeClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    @Autowired
    private EmployeeClient employeeClient;
    /**
     * 面试员工列表
     *
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentInterview studentInterview) {
        return "/system/employeeInterviewList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentInterview student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);
        String dateTime = student.getDateTime();

//        DateTimeUtils.StringSubToDate(dateTime);
        if (dateTime != null && !"".equals(dateTime)){

            student.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            student.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }
        student.setIstype(Contsants.EMPLOYEE_INTERVIEW);
        Response<List<StudentInterview>> listResponse = interviewClient.find(student);
        List<StudentInterview> data = listResponse.getData();
        if(data.size()>0){
            for (StudentInterview stu: data) {
                if(stu.getPkStudent()!=null&&!"".equals(stu.getPkStudent())){   //意向人主键
                    Employee employee=new Employee();
                    employee.setPkEmployee(stu.getPkStudent());
                    Response<Employee> response1=employeeClient.findByPrimaryKey(employee);
                    if(response1.getData()!=null){
                        stu.put("employee",response1.getData());
                    }
                }
                if(stu.getPkEmployee()!=null&&!"".equals(stu.getPkEmployee())){ //hr主键
                    Employee employee=new Employee();
                    employee.setPkEmployee(stu.getPkEmployee());
                    Response<Employee> response1=employeeClient.findByPrimaryKey(employee);
                    if(response1.getData()!=null){
                        stu.put("employeeAdmin",response1.getData());
                    }
                }
            }

        }

        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改
     *
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, StudentInterview studentInterview) {
        Response<StudentInterview> byPrimaryKey = interviewClient.findByPrimaryKey(studentInterview);
        StudentInterview data = byPrimaryKey.getData();

       // initPageAttribute(model, data.getPkDomain(),data.getPkEmployee());

//        Student student = studentUtils.getStudent(data.getPkStudent());
//        data.put("student", student);

        Employee employee=new Employee(); //查找意向员工姓名
        employee.setPkEmployee(data.getPkStudent());
        Response<Employee> response=employeeClient.findByPrimaryKey(employee);
        data.put("employee",response.getData());
        data.put("domain",domainUtils.getDomain(data.getPkDomain()));
        //查找hr姓名
        if(data.getPkEmployee()!=null&&!"".equals(data.getPkEmployee())){
            employee.setPkEmployee(data.getPkEmployee());
            Response<Employee> response1=employeeClient.findByPrimaryKey(employee);
            data.put("employeeAdmin",response1.getData());
        }

        model.addAttribute("students", byPrimaryKey.getData());
        return "/system/employeeInterview";
    }

    /**
     * 初始化下拉列表
     *
     * @param model
     * @param pkDomain
     */
    public void initPageAttribute(Model model, String pkDomain,String pkEmployee) {

        //校区
        domainUtils.setModeAttribute(model, "domain", pkDomain);
        //面试科目
        sysDictUtils.setModeAttribute(model, "project", SysDicEmnuUtils.STUDENT_PROJECT);
        //面试老师
        employeeUtils.setModeAttribute(model, "employee",pkEmployee);
    }


    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, StudentInterview studentInterview) {



        Employee employee=new Employee(); //查找意向员工姓名
        employee.setPkEmployee(studentInterview.getPkStudent());
        Response<Employee> response=employeeClient.findByPrimaryKey(employee);
        studentInterview.put("employee",response.getData());
        studentInterview.put("domain",domainUtils.getDomain(response.getData().getPkDomain()));
        //initPageAttribute(model, student.getPkDomain(),null);

        //临时编号
        String code = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.INTERVIEW);
        studentInterview.setCode(code);
        studentInterview.setDate(new Date());
        studentInterview.setPkStudentInterview(GuidUtils.getGuid());


        studentInterview.setIstype(Contsants.EMPLOYEE_INTERVIEW);
        studentInterview.setCreationDate(new Date());
        studentInterview.setLasteditDate(new Date());
        studentInterview.put(LinkEntity.CREATOR_ENTITY,EmployeeUtils.getSysUser());
        studentInterview.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        model.addAttribute("students", studentInterview);
        model.addAttribute("userKey","true");


        return "/system/employeeInterview";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentInterview studentInterview) {
        String userKey = request.getParameter("userKey");
        if(userKey!= null && userKey.equals("true")){
            studentInterview.setCreator(SessionCache.getUserCode());
        }
        studentInterview.setIstype(Contsants.EMPLOYEE_INTERVIEW);
        studentInterview.setModifier(SessionCache.getUserCode());
        studentInterview.setDate(DateTimeUtils.stringToDate(studentInterview.getDateTime()));

        return interviewClient.save(studentInterview);

    }


    /**
     * 删除
     *
     * @param studentSignup
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentInterview studentSignup) {
        String response = interviewClient.delete(studentSignup.getPkStudentInterview());
        return response;
    }


    /**
     * 审核
     *
     * @param request
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    @ResponseBody
    public String audit(HttpServletRequest request, Model model, StudentInterview studentInterview) {
//        studentInterview.setIsaudit(1);
        studentInterview.setAuditor(SessionCache.getUserCode());
        studentInterview.setDate(DateTimeUtils.stringToDate(studentInterview.getDateTime()));

        String str=interviewClient.audit(studentInterview);
        JSONObject jsStr = JSONObject.parseObject(str);
        if((int)jsStr.get("code")==0){
            Employee employee=new Employee();
            employee.setPkEmployee(studentInterview.getPkStudent());
            employee.setTypes(null); //null 为正式员工   0 为意向员工
            return  employeeClient.save(employee); //更改类型为正式员工
        }
        return str;
    }

    /**
     * 提交
     *
     * @param request
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    @ResponseBody
    public String submit(HttpServletRequest request, Model model, StudentInterview studentInterview) {
        studentInterview.setIssubmit(1);
        studentInterview.setSubmitor(SessionCache.getUserCode());
        studentInterview.setDate(DateTimeUtils.stringToDate(studentInterview.getDateTime()));

        return interviewClient.submit(studentInterview);
    }
}
