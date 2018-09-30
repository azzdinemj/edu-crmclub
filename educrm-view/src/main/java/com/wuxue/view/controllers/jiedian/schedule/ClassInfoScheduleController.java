package com.wuxue.view.controllers.jiedian.schedule;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.*;
import com.wuxue.view.client.finance.ExpenseItemClient;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级信息
 */

@Controller
@RequestMapping(value = "/schedule/classinfoSchedule")
public class ClassInfoScheduleController extends BaseController
        implements IQueryController<Classinfo, String>, ISaveController<Classinfo, String>, IEditController<Classinfo, String> {

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
    private ClassTimeClient classTimeClient;
    @Autowired
    private ClassInfoEmployeeClient classInfoEmployeeClient;
    @Autowired
    private ClassInfoRoomClient classInfoRoomClient;

    @InitBinder("classinfo")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("clas.");
    }


    /**
     * 班级列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, Classinfo classinfo) {
        /*Response<List<Classinfo>> listResponse = classinfoClient.find(classinfo);

        model.addAttribute("list",listResponse.getData() );
*/
        model.addAttribute("urlkey",1);
        //报名项目
        sysDictUtils.setModeAttribute(model, "project", SysDicEmnuUtils.STUDENT_PROJECT);
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        return "/classinfo/classInfoList";
    }

    /**
     * 修改页面
     *
     * @param classinfo
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Classinfo classinfo) {
        Response<Classinfo> byPrimaryKey = classinfoClient.findByPrimaryKey(classinfo);
        Classinfo clas = byPrimaryKey.getData();
        model.addAttribute("classinfo", clas);

        //初始化下拉列表
        initPageClassAttribute(model, byPrimaryKey.getData().getPkDomain(), byPrimaryKey.getData());

        Map<String, Object> map = byPrimaryKey.getData().getMap();
        //班级学生资料
        List<Student> list = DataUtils.objectToList(map.get(Light.STUDENT_LIST), Student.class);
        model.addAttribute("students", list);
        //班级老师资料
        List<Employee> employeeList = DataUtils.objectToList(map.get(Light.EMPLOYEE), Employee.class);
        model.addAttribute("employees", employeeList);
        //班级学生成绩信息
        List<StudentScores> scoresList = DataUtils.objectToList(map.get(Light.STUDENT_TEST_PLANS_SCORES), StudentScores.class);
        model.addAttribute("studnetScores", scoresList);

        //学期
        sysDictUtils.setModeAttribute(model, "terms", SysDicEmnuUtils.TERM);


        return "/classinfo/classInfo";
    }


    /**
     * 保存
     *
     * @param classinfo
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Classinfo classinfo) {
        if (classinfo.getPkClassinfo() == null || "".equals(classinfo.getPkClassinfo())) {
            classinfo.setPkClassinfo(GuidUtils.getGuid());
            classinfo.setCreator(SessionCache.getUserCode());
        }
        classinfo.setModifier(SessionCache.getUserCode());

        classinfo.setDate(DateTimeUtils.stringToDate(classinfo.getDateTime()));
        classinfo.setStartDate(DateTimeUtils.stringToDate(classinfo.getStartDateTime()));
        classinfo.setEndDate(DateTimeUtils.stringToDate(classinfo.getEndDateTime()));

        return classinfoClient.save(classinfo);

    }

    private void initPageClassAttribute(Model model, String pkDomain, Classinfo classinfo) {

        //班主任
        employeeUtils.setModeAttribute(model, "headTeacher", classinfo.getHeadTeacher());
        //副班主任
        employeeUtils.setModeAttribute(model, "secondTeacher", classinfo.getSecondTeacher());
        //教学主管
        employeeUtils.setModeAttribute(model, "director", classinfo.getDirector());
        //项目
        sysDictUtils.setModeAttribute(model, "project", SysDicEmnuUtils.STUDENT_PROJECT);
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        //学部
        sysDictUtils.setModeAttribute(model, "divisions", SysDicEmnuUtils.DIVISION);

        //教室
        model.addAttribute("classRooms", studentUtils.getClassRooms());
        //校区
        domainUtils.setModeAttribute(model, "domain", pkDomain);

    }


}
