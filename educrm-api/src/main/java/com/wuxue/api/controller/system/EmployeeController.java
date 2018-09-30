package com.wuxue.api.controller.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.Employee;
import com.wuxue.api.service.EmployeeService;
import com.wuxue.model.WxOpenid;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/system/employee")
public class EmployeeController implements IFindController<Employee>,
        ISaveController<Employee>,IDeleteController<Employee> {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Response Find(@RequestBody Request<Employee> employee) {
        return employeeService.find(employee);
    }

    @Override
    public Response Save(@RequestBody Request<Employee> employee) {
        return employeeService.save(employee);
    }

    @Override
    public Response Delete(@RequestBody Request<Employee> employee) {
        return employeeService.delete(employee);

    }

    @RequestMapping(value = "/quit",method = RequestMethod.POST)
    public Response Quit(@RequestBody Request<Employee> employee) {
        return employeeService.quit(employee);

    }

    @RequestMapping(value = "/selectby",method = RequestMethod.POST)
    public Response selectBy(@RequestBody Request<Employee> employee) {
        return employeeService.selectBy(employee);
    }

    /**
     * 中航油查找 list  pageingfo
     * @param employee
     * @return
     */
    @RequestMapping("/findzhyou")
    public Response Findzhyou(@RequestBody Request<Employee> employee) {
        return employeeService.findzhyou(employee);
    }

    /**
     * 中航油save update
     * @param employee
     * @return
     */
    @RequestMapping("/savezhyou")
    public Response Savezhyou(@RequestBody Request<Employee> employee) {
        return employeeService.savezhyou(employee);
    }

    @RequestMapping(value = "/findbyphone",method = RequestMethod.POST)
    public Response findbyPhone(@RequestBody Request<String> phone) {
        return employeeService.findEmployeeByPhone(phone);
    }

    /**
     * jiedian  返回老师详情页
     * @param employee
     * @return
     */
    @RequestMapping("/findjiedian")
    public Response findjiedian(@RequestBody Request<Employee> employee) {
        return employeeService.findjiedian(employee);
    }


    @RequestMapping("/selectOpenid")
    public Response selectOpenid(@RequestBody Request<WxOpenid> wxOpenid){
        return employeeService.selectOpenid(wxOpenid);
    }

    @RequestMapping("/getPhoneCode")
    public Response getPhoneCode(@RequestBody Request<Employee> employee){
        return employeeService.getPhoneCode(employee);
    }

    @RequestMapping("/bindWx")
    public Response bindWx(@RequestBody Request<WxOpenid> wxOpenid){
        return employeeService.bindWx(wxOpenid);
    }

    @RequestMapping("/getOpenid")
    public Response getOpenid(@RequestBody Request<WxOpenid> wxOpenid){
        return employeeService.getOpenid(wxOpenid);
    }

    @RequestMapping(value = "/getcode",method = RequestMethod.POST)
    public Response getCode(){
        return employeeService.getCode();
    }

    @RequestMapping("/findbymap")
    public Response findForMap(@RequestBody Request<Employee> employee) {
        return employeeService.findForMap(employee);
    }

}
