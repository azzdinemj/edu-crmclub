package com.wuxue.view.controllers.system;

import com.wuxue.model.*;
import com.wuxue.model.Employee;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.*;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.enums.SysDictEnum;
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

@Controller
@RequestMapping(value = "/system/employee")
public class EmployeeController extends BaseController implements IQueryController<Employee, String>, ISaveController<Employee, String>,IQueryByPagingController<Employee,Map<String,Object>>,
        ICreateController<Employee, String>, IEditController<Employee, String>, IDeleteController<Employee, String> {

    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private SysAutcodeClient sysAutcodeClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private DepartmentUtils departmentUtils;
    @Autowired
    private SysUserClient sysUserClient;

    /**
     * 员工列表
     *
     * @param model
     * @param employee
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Employee employee) {

//        Response<List<Employee>> listResponse = employeeClient.find(employee);
//        model.addAttribute("list", listResponse.getData());

        //岗位
        sysDictUtils.setModeAttribute(model, "jobPosts", SysDicEmnuUtils.JOBPOSTS);

        return "/system/employeeList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Employee employee, Integer sEcho, Integer start, Integer length) {
        employee.setPageNo((start/length)+1);
        employee.setPageSize(length);
        String jobPost = employee.getJobPost();
        if (jobPost != null && !"".equals(jobPost)){
            employee.setJobPost(jobPost);
        }
        String dateTime = employee.getDateTime();
        if(dateTime != null && !"".equals(dateTime)){
            employee.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            employee.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }

        Response<List<Employee>> listResponse = employeeClient.find(employee);
        List<Employee> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 创建y员工跳转
     *
     * @param request
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Employee employee) {

        initPageAttribute(model,SessionCache.getPkdomain(),SessionCache.getfDeparement());


        SysAutocode sysAutocode = new SysAutocode();
        sysAutocode.setPkDomain(SessionCache.getPkdomain());
        sysAutocode.setPkSysAutocode(AutoCodeEnum.EMPLOYEE);
        String autoCode = sysAutcodeClient.getCode(sysAutocode);
        employee.setCode(autoCode);
        employee.setBirth(new Date());
        employee.setEnterdate(new Date());
        model.addAttribute("employee", employee);


        return "/system/employee";
    }

    public void initPageAttribute(Model model,String pkDomain,String pkDeparment){
        //证件类型
        sysDictUtils.setModeAttribute(model, "idkind", SysDicEmnuUtils.ID_KIND);

        //学历
        sysDictUtils.setModeAttribute(model, "educations", SysDicEmnuUtils.EDUCATIONS);
        //外语程度
        sysDictUtils.setModeAttribute(model, "foreignLanguages", SysDicEmnuUtils.FOREIGN_LANGUAGES);

        //岗位
        sysDictUtils.setModeAttribute(model, "jobPosts", SysDicEmnuUtils.JOBPOSTS);
        //健康状况
        sysDictUtils.setModeAttribute(model, "health", SysDicEmnuUtils.HEALTH);
        //婚姻状况
        sysDictUtils.setModeAttribute(model, "marital", SysDicEmnuUtils.MARITAL);
        //计算机状况
        sysDictUtils.setModeAttribute(model, "computerskill", SysDicEmnuUtils.COMPUTERSKILL);
        //政治面貌
        sysDictUtils.setModeAttribute(model, "politicalStatus", SysDicEmnuUtils.POLITICAL_STATUS);

        //校区
        domainUtils.setListModeAttribute(model,"domains",pkDomain);

        //部门
        departmentUtils.setListModeAttribute(model,"departments",pkDeparment);
        //民族
        sysDictUtils.setModeAttribute(model, "nation", SysDicEmnuUtils.NATION);
        //员工
        SysUser sysUser=new SysUser();
        sysUser.setPkDomain(SessionCache.getPkdomain());
        Response<List<SysUser>> listResponse = sysUserClient.find(sysUser);
        model.addAttribute("sysUser", listResponse.getData());

    }

    /**
     * 修改页面
     *
     * @param employee
     * @param model
     * @return
     */

    public String edit(HttpServletRequest request, Model model, Employee employee) {
        Response<Employee> byPrimaryKey = employeeClient.findByPrimaryKey(employee);
        Employee emp = byPrimaryKey.getData();
        model.addAttribute("employee", emp);

        Map<String, Object> map = emp.getMap();
        //员工证书
        LinkingUtils.setModeAttribute(model,"certificates",map.get(Light.EMPLOYEE_CERTIFICATE),EmployeeCertificate.class);
        //工作经历
        LinkingUtils.setModeAttribute(model,"employeeJobExps",map.get(Light.EMPLOYEE_JOB_EXP),EmployeeJobExp.class);
        //家庭资料
        LinkingUtils.setModeAttribute(model,"homeInfo",map.get(Light.EMPLOYEE_HOME_INFO),EmployeeHomeInfo.class);
        //任教课程
        LinkingUtils.setModeAttribute(model,"employeeCourse",map.get(Light.EMPLOYEE_COURSE),SysDictValues.class);

        initPageAttribute(model,emp.getPkDomain(),SessionCache.getfDeparement());


        return "/system/employee";
    }

    /**
     * 保存角色
     *
     * @param employee
     * @param request
     * @param model
     * @return
     */

    @ResponseBody
    @Override
    public String save(HttpServletRequest request, Model model, Employee employee) {
        return employeeClient.save(employee);
    }

    //
    @InitBinder("employee")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("emp.");
    }

    //
    @InitBinder("employeeJobExp")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("ejob.");
    }

    //
    @InitBinder("employeeHomeInfo")
    public void initBinder3(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("home.");
    }

    @InitBinder("employeeCertificate")
    public void initBinder4(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("cer.");
    }

    @ResponseBody
    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, Employee employee, EmployeeJobExp employeeJobExp,
                          EmployeeHomeInfo employeeHomeInfo,EmployeeCertificate  employeeCertificate) {
        String userName = SessionCache.getUserName();



//        employee.put(Light.EMPLOYEE_CERTIFICATE, employeeCertificate);
//        employee.put(Light.EMPLOYEE_HOME_INFO, employeeHomeInfo);
//        employee.put(Light.EMPLOYEE_JOB_EXP, employeeJobExp);
        if (employee.getPkEmployee() == null || "".equals(employee.getPkEmployee())) {
            employee.setPkEmployee(GuidUtils.getGuid());
            employee.setCreator(SessionCache.getUserCode());

        }
        employee.setModifier(SessionCache.getUserCode());
        employee.setEnterdate(DateTimeUtils.stringToDate(employee.getEnterdates()));
        employee.setLeavedate(DateTimeUtils.stringToDate(employee.getLeavedates()));
        employee.setBirth(DateTimeUtils.stringToDate(employee.getBirthTime()));


        return employeeClient.save(employee);
//        return null;
    }




    /**
     * 删除用户
     *
     * @param request
     * @param employee
     * @return
     */

    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Employee employee) {

        return employeeClient.delete(employee);
    }


}
