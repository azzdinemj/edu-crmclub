package com.wuxue.view.utils;

import com.wuxue.base.KeyValue;
import com.wuxue.model.Employee;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeUtils {

    @Autowired
    private EmployeeClient employeeClient;

    /**
     * 获取员工信息
     * @param pkEmployee
     * @return
     */
    public Employee getEmployee(String pkEmployee){
        Employee employee = new Employee();
        if(pkEmployee != null && !"".equals(pkEmployee)){
            employee.setPkEmployee(pkEmployee);
            employee = employeeClient.findByPrimaryKey(employee).getData();
        }

        return employee;
    }
    //获取所有员工
    public void setModeAttribute(Model model, String name){
        if(model == null || name ==null || name.equals("")){
            return;
        }
        model.addAttribute(name,getEmployees());
    }
    //获取员工信息
    public void setModeAttribute(Model model, String name,String pkEmployee){
        if(model == null || name ==null || name.equals("")){
            return;
        }
        model.addAttribute(name,getEmployee(pkEmployee));
    }
    public List<Employee> getEmployees(){
        Employee employee = new Employee();
        Response<List<Employee>> listResponse = employeeClient.find(employee);
        return listResponse.getData();
    }

    private static KeyValue setSysUser(){
        KeyValue keyValue = new KeyValue();
        keyValue.setCaption(SessionCache.getUserName());
        return keyValue;
    }

    public static KeyValue getSysUser(){
        return setSysUser();
    }


}
