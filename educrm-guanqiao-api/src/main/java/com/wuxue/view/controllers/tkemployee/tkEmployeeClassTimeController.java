package com.wuxue.view.controllers.tkemployee;

import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.ScheduleClient;
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

/**
 *
 * description: 员工课时统计
 * @auther: wh
 * @date: 2018/7/13 9:32
 */
@Controller
@RequestMapping(value = "/guanqiao/employeeClassTime")
public class tkEmployeeClassTimeController extends BaseController {
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private ScheduleClient scheduleClient;


    /**
     * 员工列表  统计课时
     *
     * @param model
     * @param employee
     * @return
     */
    @RequestMapping("queryClassTime")
    public String queryClassTime(HttpServletRequest request, Model model, Employee employee) {
        return "systemhtml/employeeListClassTime";
    }

    /**
     * 查询老师已经上过的课程的 课时 以及总费用
     *  查询条件， 老师姓名，时间范围
     * @param request
     * @param response
     * @param employee
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @RequestMapping("queryByPagingClassTime")
    @ResponseBody
    public Map<String, Object> queryByPagingClassTime(HttpServletRequest request, HttpServletResponse response, Employee employee, Integer sEcho, Integer start, Integer length) {
        employee.setPageNo((start/length)+1);
        employee.setPageSize(length);
        Response<List<Employee>> listResponse = employeeClient.find(employee);
        List<Employee> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        else{
            for (Employee e:data) {
                Schedule schedule=new Schedule();
                schedule.setPkEmployee(e.getPkEmployee());

                String dateTime = employee.getDateTime();
                if(dateTime != null && !"".equals(dateTime)){
                    schedule.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
                    schedule.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
                }

                schedule.setStatus(1); //已经上的课程
                Response<List<Schedule>> response1=scheduleClient.findguanqiao(schedule);
                int i=0;
                if(response1.getData().size()>0){
                    for (Schedule schedule1:response1.getData()) {
                        if(schedule1.getProductUseclasshours()!=null&&!"".equals(schedule1.getProductUseclasshours())){
                            i=i+schedule1.getProductUseclasshours();
                        }
                    }
                }else{
                    i=0;
                }
                e.put("classTime",i);
                e.put("amount",i*e.getPrice());
            }

        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
