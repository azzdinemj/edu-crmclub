package com.wuxue.view.controllers.tkemployee;

import com.wuxue.model.Employee;
import com.wuxue.model.SysAutocode;
import com.wuxue.model.SysDictValues;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.SysAutcodeClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.AutoCodeEnum;
import com.wuxue.view.utils.DateTimeUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/guanqiao/employee")
public class tkEmployeeController extends BaseController implements IQueryController<Employee, String>, ISaveController<Employee, String>,IQueryByPagingController<Employee,Map<String,Object>>,
       IDeleteController<Employee, String> ,ICreateController<Employee,  Response>, IEditController<Employee,  Response>{

    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private SysAutcodeClient sysAutcodeClient;

    /**
     * 员工列表
     *
     * @param model
     * @param employee
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Employee employee) {
        return "systemhtml/employeeList";
    }

    @RequestMapping(value = "/createHtml",method = RequestMethod.GET)
    public String createHtml(HttpServletRequest request, Model model, Employee employee) {

        return "systemhtml/addEmployee";
    }
    @RequestMapping(value = "/editHtml",method = RequestMethod.GET)
    public String editHtml(HttpServletRequest request, Model model, Employee employee) {

        return "systemhtml/editEmployee";
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

        Response<List<Employee>> listResponse = employeeClient.find(employee);
        List<Employee> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 创建员工跳转
     *
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public  Response create(HttpServletRequest request, Model model, Employee employee) {

        SysAutocode sysAutocode = new SysAutocode();
        sysAutocode.setPkDomain(SessionCache.getPkdomain());
        sysAutocode.setPkSysAutocode(AutoCodeEnum.EMPLOYEE);
        String autoCode = sysAutcodeClient.getCode(sysAutocode);
        employee.setCode(autoCode);
        employee.setBirth(new Date());
        employee.setEnterdate(new Date());
        employee.setPkDomain(SessionCache.getPkdomain());
        Response response = Response.newResponse();
        response.setData(employee);

        return response;
    }

    @Autowired
    SysUserClient sysUserClient;
    /**
     * 员工对应的用户
     * @return
     */
    @RequestMapping(value="/getSysUser",method = RequestMethod.GET)
    @ResponseBody
    public  Response getSysUser(SysDictValues sysDictValues){
        //员工
        SysUser sysUser=new SysUser();
        sysUser.setPageNo(1);
        sysUser.setPageSize(500);
        Response<List<SysUser>> listResponse = sysUserClient.find(sysUser);
        return listResponse;
    }


    /**
     * 修改页面
     *
     * @param employee
     * @param model
     * @return
     */
    @Override
    @ResponseBody
    public Response edit(HttpServletRequest request, Model model, Employee employee) {
        Response<Employee> byPrimaryKey = employeeClient.findByPrimaryKey(employee);

        return byPrimaryKey;
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
        if(employee.getPkEmployee()==null||"".equals(employee.getPkEmployee())){
            //新建
            //查看该电话是否存在
            Employee employee1=new Employee();
            employee1.setPhone(employee.getPhone());
            Response<List<Employee>> listResponse=employeeClient.find(employee1);
            if(listResponse.getData().size()>0){
                return "{\"code\":1,\"message\":\"该电话已存在!!\"}";
            }
        }else{
            //编辑
            //查看该电话是否存在
            Employee employee1=new Employee();
            employee1.setPhone(employee.getPhone());
            Response<List<Employee>> listResponse=employeeClient.find(employee1);
            if(listResponse.getData().size()>0){
                if(!listResponse.getData().get(0).getPkEmployee().equals(employee.getPkEmployee())){
                    return "{\"code\":1,\"message\":\"该电话已存在!!\"}";
                }
            }
        }

        employee.setEnterdates("2018-06-28");
        employee.setPkDepartment("201816544800594420");
        employee.setBirth(DateTimeUtils.stringToDate(employee.getBirthTime()));
        if("".equals(employee.getSysUser())){
            employee.setSysUser(null);
        }
        return employeeClient.save(employee);
    }


    @InitBinder("employee")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("emp.");
    }

    /**
     * 删除用户
     *
     * @param request
     * @param employee
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Employee employee) {
        return employeeClient.delete(employee);
    }

}
