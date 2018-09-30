package com.wuxue.view.controllers.system;

import com.wuxue.model.Employee;
import com.wuxue.model.EmployeeCertificate;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.EmployeeCertificateClient;
import com.wuxue.view.client.system.EmployeeClient;
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
 * 员工荣誉证书管理
 */
@Controller
@RequestMapping(value = "/system/employeeCertificate")
public class EmployeeCertificateController extends BaseController
        implements IQueryController<EmployeeCertificate,String>,ISaveController<EmployeeCertificate,String>,IQueryByPagingController<EmployeeCertificate,Map<String,Object>>,
        ICreateController<EmployeeCertificate,String>,IEditController<EmployeeCertificate,String>,IDeleteController<EmployeeCertificate,String> {


    @InitBinder("employeeCertificate")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("cer.");
    }

    @Autowired
    private EmployeeCertificateClient employeeCertificateClient;
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
    public String query(HttpServletRequest request, Model model, EmployeeCertificate employeeCertificate)  {
        Response<List<EmployeeCertificate>> listResponse = employeeCertificateClient.find(employeeCertificate);

        model.addAttribute("list",listResponse.getData() );
        return "/system/employeeCertificateList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, EmployeeCertificate employeeCertificate, Integer sEcho, Integer start, Integer length) {
        employeeCertificate.setPageNo((start/length)+1);
        employeeCertificate.setPageSize(length);


        Response<List<EmployeeCertificate>> listResponse = employeeCertificateClient.find(employeeCertificate);
        List<EmployeeCertificate> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @Override
    public String create(HttpServletRequest request, Model model, EmployeeCertificate employeeCertificate) {

        Employee employee = getEmployee(employeeCertificate);
        model.addAttribute("employee",employee);
        employeeCertificate.setCreator(SessionCache.getUserCode());
        employeeCertificate.setCreationDate(new Date());
        employeeCertificate.setModifier(SessionCache.getUserCode());
        employeeCertificate.setLasteditDate(new Date());
        model.addAttribute("employeeCertificate",employeeCertificate);
        return "/system/empCertificate";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, EmployeeCertificate employeeCertificate) {
        return employeeCertificateClient.delete(employeeCertificate.getPkEmployeeCertificate());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, EmployeeCertificate employeeCertificate) {
        Response<EmployeeCertificate> byPrimaryKey = employeeCertificateClient.findByPrimaryKey(employeeCertificate);
        model.addAttribute("employeeCertificate",byPrimaryKey.getData());
        Employee employee = getEmployee(byPrimaryKey.getData());
        model.addAttribute("employee",employee);
        return "/system/empCertificate";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, EmployeeCertificate employeeCertificate) {
        String userName = SessionCache.getUserName();
        if(employeeCertificate.getPkEmployeeCertificate() == null && "".equals(employeeCertificate.getPkEmployeeCertificate())){
            employeeCertificate.setPkEmployeeCertificate(GuidUtils.getGuid());
            employeeCertificate.setCreator(userName);
            employeeCertificate.setModifier(userName);
        }else {
            employeeCertificate.setModifier(userName);
        }
        employeeCertificate.setDate(DateTimeUtils.stringToDate(employeeCertificate.getDateTime()));

        return employeeCertificateClient.save(employeeCertificate);
    }


    public Employee getEmployee(EmployeeCertificate employeeCertificate){
        Employee employee = new Employee();
        employee.setPkEmployee(employeeCertificate.getPkEmployee());
        Response<Employee> byPrimaryKey = employeeClient.findByPrimaryKey(employee);
        return byPrimaryKey.getData();

    }


}
