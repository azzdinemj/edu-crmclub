package com.wuxue.view.controllers.jiedian.schedule;


import com.alibaba.fastjson.JSON;
import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;

import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.classInfo.ClassRoomClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.product.ProductClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.SysLogClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 意向学生
 */
@Controller("jiedianmyschedule")
@RequestMapping(value = "/jiedian/schedule")
public class ScheduleController extends BaseController
implements IQueryController<Schedule, String>,IQueryByPagingController<Schedule,Map<String,Object>>,ICreateController<Schedule, String>, IEditController<Schedule, String>,ISaveController<Schedule, String>,IDeleteController<Schedule, String> {
	@InitBinder
    public void initBinder3(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));
    }
	@InitBinder
    public void initBinder2(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }
    
    
    

    @Autowired
    private SysDictUtils sysDictUtils;

    @Autowired
    private ScheduleClient scheduleClient;
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private SysLogClient sysLogClient;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private ClassRoomClient classRoomClient;
    private void initPageAttribute(Model model, String pkDomain) {
        //科目类型
        sysDictUtils.setModeAttribute(model, "subject", SysDicEmnuUtils.STU_DISCIPLINE);
        Product product=new Product();
//        Response<List<Product>> listResponse = productClient.find(product);
        Response<List<Product>> listResponse = productClient.selectBy(product);
        model.addAttribute("product", listResponse.getData());
        Employee employee=new Employee();
//        Response<List<Employee>> listResponse2 = employeeClient.find(employee);
        Response<List<Employee>> listResponse2 = employeeClient.selectBy(employee);
         model.addAttribute("employee", listResponse2.getData());
         ClassRoom classRoom=new ClassRoom();
//        Response<List<ClassRoom>> listResponse3 = classRoomClient.find(classRoom);
        Response<List<ClassRoom>> listResponse3 = classRoomClient.selectBy(classRoom);
          model.addAttribute("classRoom", listResponse3.getData());
    
       
    }
    
    /**
     * 排课列表
     *
     * @param model
     * @param
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Schedule schedule) {
      
        model.addAttribute("isType",3);
        Employee employee=new Employee();
        String pkEmployee=request.getParameter("pkEmployee");
        String pkStudent=request.getParameter("pkStudent");
        String studentName=request.getParameter("studentName");
        String defaultView = request.getParameter("defaultView");
        employee.setPkEmployee(pkEmployee);
        Response<List<Employee>> listResponse = employeeClient.find(employee);
        model.addAttribute("pkEmployee", pkEmployee);
        model.addAttribute("studentName", studentName);
         model.addAttribute("pkStudent", pkStudent);
         model.addAttribute("employee", listResponse.getData());
         if(defaultView != null) {
             model.addAttribute("defaultView", defaultView);
         }else{
             model.addAttribute("defaultView", "month");
         }
        //年级
        //sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);

        return "/jiedian/schedule/querybak3";
    }
    /**
     * 老师排课列表
     *
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String teacher(HttpServletRequest request, Model model, Schedule schedule) {
      
        model.addAttribute("isType",3);
        Employee employee=new Employee();
        String pkEmployee=request.getParameter("pkEmployee");
        String pkStudent=request.getParameter("pkStudent");
        employee.setPkEmployee(pkEmployee);
        Response<List<Employee>> listResponse = employeeClient.selectBy(employee);
         model.addAttribute("pkEmployee", pkEmployee);
         model.addAttribute("pkStudent", pkStudent);
         model.addAttribute("employee", listResponse.getData());

        //年级
        //sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);

        return "/jiedian/schedule/querybak2";
    }
    //获取排课数据
    @ResponseBody
    @RequestMapping(value = "/getschedule", method = RequestMethod.POST)
    public String getschedule(HttpServletRequest request, Model model, Schedule schedule) {
    
        Response<List<Schedule>> listResponse = scheduleClient.find(schedule);
        return  JSON.toJSONString(listResponse.getData());

       
    }

    //获取单条排课数据
    @ResponseBody
    @RequestMapping(value = "/getsch", method = RequestMethod.POST)
    public String getsch(HttpServletRequest request, Model model, Schedule schedule) {

        Response<Schedule> byPrimaryKey = scheduleClient.findByPrimaryKey(schedule);
        return  JSON.toJSONString(byPrimaryKey.getData());


    }

    /**
     * 删除
     * @param schedule
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Schedule schedule) {
        String response = scheduleClient.delete(schedule.getPkSchedule());
        return response;
    }

    /**
     * 排课去重
     *
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkschedule", method = RequestMethod.POST)
    public String checkschedule(HttpServletRequest request, Model model, Schedule schedule) throws ParseException {
    	Schedule schedule2=new Schedule();
        String listkey = request.getParameter("listkey");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

//    	schedule2.setPkClassRoom(schedule.getPkClassRoom());
//    	schedule2.setPkStudent(schedule.getPkStudent());


        schedule2.setStartTime(sdf.parse(schedule.getStartTimes()));
        schedule2.setEndTime(sdf.parse(schedule.getEndTimes()));
        if (listkey != null && "listkey".equals(listkey)){
            String cycleTime = request.getParameter("datas");
            if (cycleTime != null && !"".equals(cycleTime)){
                List<Date> dates = DateTimeUtils.StringSubToDate(cycleTime);
                schedule2.setStartTime(dates.get(0));
                schedule2.setEndTime(dates.get(1));
            }
            schedule2.put(Light.COURSE,listkey);
            schedule2.setCycle(schedule.getCycle());
        }
//        else {
//            schedule2.setStartTime(sdf.parse(schedule.getStartTimes()));
//            schedule2.setEndTime(sdf.parse(schedule.getEndTimes()));
//        }
    	Response<List<Schedule>> listResponse = scheduleClient.find(schedule2);

//    	时间段相同和交叉
//        List<Schedule> data1 = new ArrayList<>();
//        if(listResponse.getData().size() <= 0) {
//            Schedule schedule1 = new Schedule();
//            schedule1.setStartTime(schedule.getStartTime());
//            schedule1.setEndTime(schedule.getEndTime());
//            Response<List<Schedule>> listResponse1 = scheduleClient.find(schedule1);
//            data1 = listResponse1.getData();
//        }
        //String pkSchedule=request.getParameter("pksch");
        List<Schedule> data = listResponse.getData();
     
        if(data.size()>0) {
        	for (Schedule sch : data) {
        		if((sch.getPkStudent().equals(schedule.getPkStudent())&&(schedule.getPkSchedule()==null||schedule.getPkSchedule().equals("")))||(!sch.getPkSchedule().equals(schedule.getPkSchedule())&&sch.getPkStudent().equals(schedule.getPkStudent()))) {
        			return "{\"code\":2,\"message\":\"学员该时间段已经有排课了 \"}";
        		}
        		if((sch.getPkClassRoom().equals(schedule.getPkClassRoom())&&!sch.getPkSchedule().equals(schedule.getPkSchedule()))&&!sch.getPkEmployee().equals(schedule.getPkEmployee())) {
        			return "{\"code\":2,\"message\":\"教室已经被占用 \"}";
        		}
        		if((!sch.getPkClassRoom().equals(schedule.getPkClassRoom())&&!sch.getPkSchedule().equals(schedule.getPkSchedule()))&&sch.getPkEmployee().equals(schedule.getPkEmployee())) {
        			return "{\"code\":2,\"message\":\"老师已经在其他教室上课了 \"}";
        		}
        		if((!sch.getPkStudent().equals(schedule.getPkStudent())&&!sch.getPkSchedule().equals(schedule.getPkSchedule()))&&sch.getPkEmployee().equals(schedule.getPkEmployee())  && schedule.getStartTime().equals(sch.getStartTime()) && schedule.getEndTime().equals(sch.getEndTime())) {
        			return "{\"code\":1,\"message\":\"老师安排其他学员上课，确定要加入吗？ \"}";
        		}
                if((!sch.getPkStudent().equals(schedule.getPkStudent())&&!sch.getPkSchedule().equals(schedule.getPkSchedule()))&&sch.getPkEmployee().equals(schedule.getPkEmployee())  && !schedule.getStartTime().equals(sch.getStartTime()) && !schedule.getEndTime().equals(sch.getEndTime())) {
                    return "{\"code\":2,\"message\":\"老师该时间段已经有排课了 \"}";
                }
                if (schedule.getPkStudent().equals(sch.getPkStudent()) && !schedule.getPkClassRoom().equals(sch.getPkClassRoom()) && !schedule.getPkEmployee().equals(sch.getPkEmployee())) {
                    return "{\"code\":2,\"message\":\"学员该时间段已经有排课了 \"}";
                }

                if (schedule.getPkEmployee().equals(sch.getPkEmployee()) && !schedule.getPkClassRoom().equals(sch.getPkClassRoom()) && !schedule.getPkStudent().equals(sch.getPkStudent())) {
                    return "{\"code\":2,\"message\":\"老师该时间段已经有排课了 \"}";
                }

//                if (!schedule.getPkEmployee().equals(sch.getPkEmployee()) && schedule.getPkClassRoom().equals(sch.getPkClassRoom()) && !schedule.getPkStudent().equals(sch.getPkStudent())) {
//                    return "{\"code\":2,\"message\":\"教室该时间段已经有排课了 \"}";
//                }
        	}
        	
        	
        }
        return "{\"code\":0,\"message\":\"ok \"}";
       
       
    }
    
    /**
     * 排课modal
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/modalschedule", method = RequestMethod.GET)
    public String modalschedule(HttpServletRequest request, Model model, Schedule schedule) {
    	
    	 model.addAttribute("schedule", schedule);
         //年级
         //sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);

         return "/jiedian/schedule/modalschedule";
       
       
    }
    
    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Schedule schedule) {

        String defaultView = request.getParameter("defaultView");
        String viewType = request.getParameter("viewType");

        if (schedule.getPkStudent() != null) {
            Student student = new Student();
            student.setPkStudent(schedule.getPkStudent());
            Response<Student> byPrimaryKey = studentClient.findByPrimaryKey(student);
            schedule.put("studentobj",byPrimaryKey.getData().getCaption());
        }

        model.addAttribute("schedule", schedule);
        model.addAttribute("delete",0);
        model.addAttribute("defaultView",defaultView);
        model.addAttribute("viewType",viewType);
        initPageAttribute(model, schedule.getPkDomain());
        String key = request.getParameter("key");
        if ("1".equals(key)){
            model.addAttribute("bykey",key);
        }
        return "/jiedian/schedule/schedule";
    }
    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Schedule schedule) {
    	Response<Schedule> byPrimaryKey = scheduleClient.findByPrimaryKey(schedule);
//    	String primaryKey=schedule.getPkSchedule();
//    	SysLog sysLog=new SysLog();
//    	sysLog.setpkId(primaryKey);
//    	sysLog.setTableName("schedule");
//    	Response<List<SysLog>> byPrimaryKey2 = sysLogClient.find(sysLog);
//
//    	model.addAttribute("syslog", byPrimaryKey2.getData());
        String defaultView = request.getParameter("defaultView");
        String viewType = request.getParameter("viewType");

        model.addAttribute("schedule", byPrimaryKey.getData());
        model.addAttribute("delete",1);
        model.addAttribute("defaultView",defaultView);
        model.addAttribute("viewType",viewType);

    	initPageAttribute(model, schedule.getPkDomain());
    	return "/jiedian/schedule/schedule";
    }
    /**
     * 保存
     *
     * @param request
     * @param model
     * @param schedule
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Schedule schedule) throws ParseException {

        Enumeration<String> parameterNames = request.getParameterNames();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        if(schedule.getPkSchedule() == null || "".equals(schedule.getPkSchedule())){
        	schedule.setCreator(SessionCache.getUserCode());
        	schedule.setModifier(SessionCache.getUserCode());
        }else {
        	schedule.setModifier(SessionCache.getUserCode());

        }
        schedule.setStartTime(sdf.parse(schedule.getStartTimes()));
        schedule.setEndTime(sdf.parse(schedule.getEndTimes()));

        return scheduleClient.save(schedule);

    }
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Schedule schedule, Integer sEcho, Integer start, Integer length) {
    	schedule.setPageNo((start/length)+1);
    	schedule.setPageSize(length);
       
        Response<List<Schedule>> listResponse = scheduleClient.find(schedule);
        List<Schedule> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 循环排课
     * @param request
     * @param model
     * @param schedule
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, Schedule schedule) throws ParseException {

//        Enumeration<String> parameterNames = request.getParameterNames();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date startTime = sdf.parse(schedule.getStartTimes());
        Date endTime = sdf.parse(schedule.getEndTimes());

        //判断开始时间是周几
        int dayForWeek = DateUtils.dayForWeek(startTime);
        schedule.setCreator(SessionCache.getUserCode());
        schedule.setModifier(SessionCache.getUserCode());
        schedule.setPkDomain(SessionCache.getPkdomain());
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);

        String cycleTime = request.getParameter("datas");
        if (cycleTime != null && !"".equals(cycleTime)){
            List<Date> dates = DateTimeUtils.StringSubToDate(cycleTime);
            schedule.setFromTime(dates.get(0));
            schedule.setToTime(dates.get(1));
        }


        return scheduleClient.saveLoop(schedule);
    }
    
   
}
