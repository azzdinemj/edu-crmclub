package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.DormRoomEmployee;
import com.wuxue.model.DormRoomEmployee;
import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.DormRoomEmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SysDicEmnuUtils;
import com.wuxue.view.utils.SysDictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 员工宿舍信息
 */

@Controller
@RequestMapping(value = "/system/dormRoomEmployee")
public class DormRoomEmployeeController extends BaseController
        implements IQueryController<DormRoomEmployee, String>, ISaveController<DormRoomEmployee, String>,IQueryByPagingController<DormRoomEmployee,Map<String,Object>>,
        ICreateController<DormRoomEmployee, String>, IEditController<DormRoomEmployee, String>, IDeleteController<DormRoomEmployee, String> ,
        ISubmitController<DormRoomEmployee,String>,IAuditController<DormRoomEmployee,String>{

    @Autowired
    private DormRoomEmployeeClient dormRoomEmployeeClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    EmployeeClient employeeClient;

    @InitBinder("dormRoomEmployee")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("dorm.");
    }




    /**
     * 员工宿舍列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, DormRoomEmployee dormRoomEmployee) {
        //报名项目
        sysDictUtils.setModeAttribute(model,"project",SysDicEmnuUtils.STUDENT_PROJECT);
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        return "/dormRoomEmployee/classInfoList";
    }

    /**
     * 修改页面
     *
     * @param dormRoomEmployee
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, DormRoomEmployee dormRoomEmployee) {


        return "/dormRoomEmployee/classInfo";
    }


    /**
     * 保存
     *
     * @param dormRoomEmployee
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, DormRoomEmployee dormRoomEmployee) {

        String pkEmployees = request.getParameter("pkEmployees");
        List<String> list = JSON.parseArray(pkEmployees).toJavaList(String.class);
        List<Employee> employeeList = new ArrayList<>();

        if(list.size()>0){
            for (String s : list) {
                Employee employee = new Employee();
                employee.setPkEmployee(s);
                employeeList.add(employee);
            }
        }
        dormRoomEmployee.setCreator(SessionCache.getUserCode());
        dormRoomEmployee.setModifier(SessionCache.getUserCode());
        dormRoomEmployee.setPkDomain(SessionCache.getPkdomain());
        dormRoomEmployee.put(Light.EMPLOYEE,employeeList);

        return dormRoomEmployeeClient.save(dormRoomEmployee);

    }


    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, DormRoomEmployee dormRoomEmployee) {



        return "/dormRoomEmployee/classInfo";
    }

    /**
     * 删除
     *
     * @param dormRoomEmployee
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, DormRoomEmployee dormRoomEmployee) {
        dormRoomEmployee.setModifier(SessionCache.getUserCode());
        String response = dormRoomEmployeeClient.delete(dormRoomEmployee);
        return response;
    }


    /**
     * 提交
     * @param request
     * @param model
     * @param dormRoomEmployee
     * @return
     */
    @Override
    public String submit(HttpServletRequest request, Model model, DormRoomEmployee dormRoomEmployee) {



        return dormRoomEmployeeClient.submit(dormRoomEmployee);
    }

    /**
     * 审核
     * @param request
     * @param model
     * @param dormRoomEmployee
     * @return
     */
    @Override
    public String audit(HttpServletRequest request, Model model, DormRoomEmployee dormRoomEmployee) {

        return dormRoomEmployeeClient.audit(dormRoomEmployee);
    }




    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, DormRoomEmployee dormRoomEmployee, Integer sEcho, Integer start, Integer length) {
        dormRoomEmployee.setPageNo((start/length)+1);
        dormRoomEmployee.setPageSize(length);
        String dateTime = dormRoomEmployee.getDateTime();
        if(dateTime!=null && !"".equals(dateTime)){
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date endTime=simpleDateFormat.parse(substring1);
                Date strTime=simpleDateFormat.parse(substring);
                dormRoomEmployee.setFromDate(strTime);
                dormRoomEmployee.setToDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Response<List<DormRoomEmployee>> listResponse = dormRoomEmployeeClient.find(dormRoomEmployee);
        List<DormRoomEmployee> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }




}
