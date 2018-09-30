package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassTime;
import com.wuxue.model.Classinfo;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ActivityStudentClient;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.classInfo.ClassInfoStudentClient;
import com.wuxue.view.client.classInfo.StudentScoresClient;
import com.wuxue.view.client.finance.ExpenseItemClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
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
 * 班级信息
 */

@Controller
@RequestMapping(value = "/classinfo/classinfoschedule")
public class ClassinfoScheduleController extends BaseController
        implements IQueryController<Classinfo, String>, ISaveController<Schedule, String>,IQueryByPagingController<Schedule,Map<String,Object>>,
        ICreateController<Schedule, String>, IEditController<ClassTime, String>, IDeleteController<Schedule, String> ,
        ISubmitController<Schedule,String>,IAuditController<Schedule,String>{

    @Autowired
    private ClassInfoClient classinfoClient;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;
    @Autowired
    private ClassInfoStudentClient classInfoStudentClient;
    @Autowired
    private ClassroomUtils classroomUtils;
    @Autowired
    private ExpenseItemClient expenseItemClient;
    @Autowired
    private ActivityStudentClient activityStudentClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private StudentScoresClient studentScoresClient;
    @Autowired
    EmployeeClient employeeClient;

    @Autowired
    private ScheduleClient scheduleClient;

    @InitBinder("classinfo")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("clas.");
    }




    /**
     * 页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, Classinfo classinfo) {

        //报名项目
        sysDictUtils.setModeAttribute(model,"project",SysDicEmnuUtils.STUDENT_PROJECT);
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);

        return "/classinfo/classInfoScheduleList";
    }

    /**
     * 修改页面
     *
     * @param classTime
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, ClassTime classTime) {

        String pkClassinfo = request.getParameter("pkClassinfo");
//        if (pkClassinfo == null || "".equals(pkClassinfo)){
//            classTime.put(Light.CLASSINFO,"201817456867753551");
//        }else {
//            classTime.put(Light.CLASSINFO,pkClassinfo);
//        }

        classTime.put(Light.CLASSINFO,pkClassinfo);
        Response<List<ClassTime>> listResponse = scheduleClient.findClassSchodule(classTime);
        List<ClassTime> list = listResponse.getData();

        model.addAttribute("list",list);

        return "/classinfo/classinfoSchedule";
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


    @Override
    public String audit(HttpServletRequest request, Model model, Schedule schedule) {
        return null;
    }

    @Override
    public String create(HttpServletRequest request, Model model, Schedule schedule) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, Model model, Schedule schedule) {
        return null;
    }

    @Override
    public String save(HttpServletRequest request, Model model, Schedule schedule) {
        return null;
    }

    @Override
    public String submit(HttpServletRequest request, Model model, Schedule schedule) {
        return null;
    }

}
