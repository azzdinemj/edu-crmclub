package com.wuxue.view.controllers.schedule;

import com.wuxue.model.Domain;
import com.wuxue.model.Employee;
import com.wuxue.model.ScheduleConstraint;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.ScheduleConstraintClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.DateTimeUtils;
import com.wuxue.view.utils.DomainUtils;
import com.wuxue.view.utils.EmployeeUtils;
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
import java.util.List;
import java.util.Map;

/**
 * 排课约束
 */

@Controller
@RequestMapping(value = "/schedule/scheduleConstraint")
public class ScheduleConstraintController extends BaseController
        implements IQueryController<ScheduleConstraint, String>, ISaveController<ScheduleConstraint, String>,IQueryByPagingController<ScheduleConstraint,Map<String,Object>>,
        ICreateController<ScheduleConstraint, String>, IEditController<ScheduleConstraint, String>, IDeleteController<ScheduleConstraint, String> {

    @Autowired
    private ScheduleConstraintClient scheduleConstraintClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private EmployeeUtils employeeUtils;

    @InitBinder("scheduleConstraint")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("con.");
    }

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, ScheduleConstraint scheduleConstraint) {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/schedule/scheduleConstraintList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ScheduleConstraint scheduleConstraint, Integer sEcho, Integer start, Integer length) {
        scheduleConstraint.setPageNo((start/length)+1);
        scheduleConstraint.setPageSize(length);

        Response<List<ScheduleConstraint>> listResponse = scheduleConstraintClient.find(scheduleConstraint);
        List<ScheduleConstraint> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     *
     * @param scheduleConstraint
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, ScheduleConstraint scheduleConstraint) {
        Response<ScheduleConstraint> byPrimaryKey = scheduleConstraintClient.findByPrimaryKey(scheduleConstraint);
        model.addAttribute("scheduleConstraint", byPrimaryKey.getData());
        return "/schedule/scheduleConstraintEdit";
    }


    /**
     * 保存
     *
     * @param scheduleConstraint
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ScheduleConstraint scheduleConstraint) {

        scheduleConstraint.setStartDate(DateTimeUtils.stringToDateHM(scheduleConstraint.getStartDateTime()));
        scheduleConstraint.setEndDate(DateTimeUtils.stringToDateHM(scheduleConstraint.getEndDateTime()));

        if (scheduleConstraint.getConstraintId() == null){
            scheduleConstraint.setCreator(SessionCache.getUserCode());
        }
        scheduleConstraint.setModifier(SessionCache.getUserCode());
        String response = scheduleConstraintClient.save(scheduleConstraint);
        return response;

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, ScheduleConstraint scheduleConstraint) {

        model.addAttribute("scheduleConstraint",scheduleConstraint);


        return "/schedule/scheduleConstraint";
    }

    /**
     * 删除
     *
     * @param scheduleConstraint
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ScheduleConstraint scheduleConstraint) {
        String response = scheduleConstraintClient.delete(scheduleConstraint.getConstraintId());
        return response;
    }
    @ResponseBody
    @RequestMapping(value = "/findDomain",method = RequestMethod.GET)
    public Response findDomain(HttpServletRequest request){

        List<Domain> allDomains = domainUtils.getAllDomains();
        if (allDomains == null){
            allDomains = new ArrayList<>();
        }
        Response response = Response.newResponse();
        response.setData(allDomains);
        return response;
    }
    @ResponseBody
    @RequestMapping(value = "/getEmployees",method = RequestMethod.GET)
    public Response getEmployee(HttpServletRequest request,String jobPost){

        List<Employee> employeeByJobPost = employeeUtils.getEmployeeByJobPost(jobPost);
        if (employeeByJobPost == null){
            employeeByJobPost = new ArrayList<>();
        }
        Response response = Response.newResponse();
        response.setData(employeeByJobPost);
        return response;
    }

}
