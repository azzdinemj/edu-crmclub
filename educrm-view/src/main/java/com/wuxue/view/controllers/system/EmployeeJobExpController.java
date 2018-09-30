package com.wuxue.view.controllers.system;

import com.wuxue.model.Employee;
import com.wuxue.model.EmployeeJobExp;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.EmployeeJobExpClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.DateTimeUtils;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
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
 * 宿舍管理
 */
@Controller
@RequestMapping(value = "/system/employeeJobExp")
public class EmployeeJobExpController extends BaseController
        implements IQueryController<EmployeeJobExp,String>,ISaveController<EmployeeJobExp,String>,IQueryByPagingController<EmployeeJobExp,Map<String,Object>>,
        ICreateController<EmployeeJobExp,String>,IEditController<EmployeeJobExp,String>,IDeleteController<EmployeeJobExp,String> {


    @InitBinder("employeeJobExp")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("ejob.");
    }


    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private EmployeeJobExpClient employeeJobExpClient;
    @Autowired
    EmployeeClient employeeClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, EmployeeJobExp employeeJobExp)  {
//        Response<List<EmployeeJobExp>> listResponse = employeeJobExpClient.find(employeeJobExp);
//
//        model.addAttribute("list",listResponse.getData() );
        return "/system/employeeJobExpList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, EmployeeJobExp employeeJobExp, Integer sEcho, Integer start, Integer length) {
        employeeJobExp.setPageNo((start/length)+1);
        employeeJobExp.setPageSize(length);



        Response<List<EmployeeJobExp>> listResponse = employeeJobExpClient.find(employeeJobExp);
        List<EmployeeJobExp> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @Override
    public String create(HttpServletRequest request, Model model, EmployeeJobExp employeeJobExp) {

        Employee employee = getEmployee(employeeJobExp);
        model.addAttribute("employee",employee);
        employeeJobExp.setCreator(SessionCache.getUserCode());
        employeeJobExp.setCreationDate(new Date());
        employeeJobExp.setModifier(SessionCache.getUserCode());
        employeeJobExp.setLasteditDate(new Date());
        model.addAttribute("employeeJobExp",employeeJobExp);
        return "/system/empJob";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, EmployeeJobExp employeeJobExp) {
        return employeeJobExpClient.delete(employeeJobExp.getPkEmployeeJobExp());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, EmployeeJobExp employeeJobExp) {
        Response<EmployeeJobExp> byPrimaryKey = employeeJobExpClient.findByPrimaryKey(employeeJobExp);
        model.addAttribute("employeeJobExp",byPrimaryKey.getData());
        Employee employee = getEmployee(byPrimaryKey.getData());
        model.addAttribute("employee",employee);
        return "/system/empJob";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, EmployeeJobExp employeeJobExp) {
        String userName = SessionCache.getUserName();
        if(employeeJobExp.getPkEmployeeJobExp() == null && "".equals(employeeJobExp.getPkEmployeeJobExp())){
            employeeJobExp.setPkEmployeeJobExp(GuidUtils.getGuid());
            employeeJobExp.setModifier(userName);
            employeeJobExp.setCreator(userName);
        }else {
            employeeJobExp.setModifier(userName);
        }
        employeeJobExp.setEnddate(DateTimeUtils.stringToDate(employeeJobExp.getEnddateTime()));
        employeeJobExp.setStartdate(DateTimeUtils.stringToDate(employeeJobExp.getStartdateTime()));

        return employeeJobExpClient.save(employeeJobExp);
    }

    /**
     * 获取员工信息
     * @param employeeJobExp
     * @return
     */
    public Employee getEmployee(EmployeeJobExp employeeJobExp){
        Employee employee = new Employee();
        employee.setPkEmployee(employeeJobExp.getPkEmployee());
        Response<Employee> byPrimaryKey = employeeClient.findByPrimaryKey(employee);
        return byPrimaryKey.getData();

    }


}
