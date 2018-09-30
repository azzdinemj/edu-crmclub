package com.wuxue.view.controllers.jiedian.teacher;

import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.SysAutcodeClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.client.system.TbAllareaClient;
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

@Controller("jiedianemployee")
@RequestMapping(value = "/jiedian/employee")
public class EmployeeController extends BaseController implements IQueryController<Employee, String>,IQueryByPagingController<Employee,Map<String,Object>>,
        ICreateController<Employee, String>, IEditController<Employee, String>,IDeleteController<Employee, String>{

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
    @Autowired
    private TbAllareaClient tbAllareaClient;
    @Autowired
    private AdressUtils adressUtils;

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

        return "/jiedian/teacher/query";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Employee employee, Integer sEcho, Integer start, Integer length) {
        employee.setPageNo((start/length)+1);
        employee.setPageSize(length);

        String dateTime = employee.getDateTime();
        if(dateTime != null && !"".equals(dateTime)){
            employee.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            employee.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }
        if (employee.getIsvalid() == null){
            employee.setIsvalid(1);
        }
        Response<List<Employee>> listResponse = employeeClient.find(employee);
        List<Employee> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }
    /**
     * 学生列表
     *
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkphone", method = RequestMethod.POST)
    public String checkphone(HttpServletRequest request, Model model, Employee employee) {
    	Response<List<Employee>> listResponse = employeeClient.find(employee);
    	String pkEmployee=request.getParameter("pkemp");
        List<Employee> data = listResponse.getData();
        
        if(data.size()>1) {
        	
        	return "{\"valid\":false}";
        }else {
        	if(data.size()==1) {
        		Employee emp=new Employee();
        		emp=data.get(0);
        		if(emp.getPkEmployee()==pkEmployee) {
        			return "{\"valid\":false}";
        		}else {
        			return "{\"valid\":true}";
        		}
        	}
        	return "{\"valid\":true}";
        }
       
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


        return "/jiedian/teacher/employee";
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
      //员工
        SysUser sysUser=new SysUser();
        sysUser.setPkDomain(SessionCache.getPkdomain());
        Response<List<SysUser>> listResponse = sysUserClient.find(sysUser);
        model.addAttribute("sysUser", listResponse.getData());

        //省
        adressUtils.setModeAttribute(model,"province",0);

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

        initPageAttribute(model,emp.getPkDomain(),SessionCache.getfDeparement());

        //市
//        adressUtils.setModeAttribute(model,"areas",emp.getProvince());
//        //县
//        adressUtils.setModeAttribute(model,"towns",emp.getArea());

        return "/jiedian/teacher/employee";
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


    @Override
    public String delete(HttpServletRequest request, Model model, Employee employee) {


        return employeeClient.delete(employee);
    }
}
