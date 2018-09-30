package com.wuxue.view.controllers.system;

import com.wuxue.model.Employee;
import com.wuxue.model.EmployeeHomeInfo;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.EmployeeHomeInfoClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
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
import java.util.Date;
import java.util.List;

/**
 *
 * 员工家庭管理
 */
@Controller
@RequestMapping(value = "/system/employeeHomeInfo")
public class EmployeeHomeInfoController extends BaseController
        implements IQueryController<EmployeeHomeInfo,String>,ISaveController<EmployeeHomeInfo,String>,
        ICreateController<EmployeeHomeInfo,String>,IEditController<EmployeeHomeInfo,String>,IDeleteController<EmployeeHomeInfo,String> {


    @InitBinder("employeeHomeInfo")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("home.");
    }

    @Autowired
    private EmployeeHomeInfoClient employeeHomeInfoClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private EmployeeClient employeeClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, EmployeeHomeInfo employeeHomeInfo)  {
        Response<List<EmployeeHomeInfo>> listResponse = employeeHomeInfoClient.find(employeeHomeInfo);

        model.addAttribute("list",listResponse.getData() );
        return "/system/employeeHomeInfoList";
    }



    @Override
    public String create(HttpServletRequest request, Model model, EmployeeHomeInfo employeeHomeInfo) {

        Employee employee = getEmployee(employeeHomeInfo);
        model.addAttribute("employee",employee);
        employeeHomeInfo.setCreator(SessionCache.getUserCode());
        employeeHomeInfo.setCreationDate(new Date());
        employeeHomeInfo.setModifier(SessionCache.getUserCode());
        employeeHomeInfo.setLasteditDate(new Date());
        model.addAttribute("employeeHomeInfo",employeeHomeInfo);

        return "/system/empHome";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, EmployeeHomeInfo employeeHomeInfo) {
        return employeeHomeInfoClient.delete(employeeHomeInfo.getPkEmployeeHomeInfo());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, EmployeeHomeInfo employeeHomeInfo) {


        Response<EmployeeHomeInfo> byPrimaryKey = employeeHomeInfoClient.findByPrimaryKey(employeeHomeInfo);
        model.addAttribute("employeeHomeInfo",byPrimaryKey.getData());
        Employee employee = getEmployee(byPrimaryKey.getData());

        model.addAttribute("employee",employee);
        return "/system/empHome";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, EmployeeHomeInfo employeeHomeInfo) {
        String userName = SessionCache.getUserName();
        if(employeeHomeInfo.getPkEmployeeHomeInfo() == null && "".equals(employeeHomeInfo.getPkEmployeeHomeInfo())){
            employeeHomeInfo.setPkEmployeeHomeInfo(GuidUtils.getGuid());
            employeeHomeInfo.setModifier(userName);
            employeeHomeInfo.setCreator(userName);
        }else {
            employeeHomeInfo.setModifier(userName);
        }

        return employeeHomeInfoClient.save(employeeHomeInfo);
    }

    public Employee getEmployee(EmployeeHomeInfo employeeHomeInfo){

        Employee employee = new Employee();
        employee.setPkEmployee(employeeHomeInfo.getPkEmployee());
        Response<Employee> emp = employeeClient.findByPrimaryKey(employee);
        return  emp.getData();

    }


}
