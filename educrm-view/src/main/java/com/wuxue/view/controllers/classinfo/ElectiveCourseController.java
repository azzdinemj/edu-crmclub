package com.wuxue.view.controllers.classinfo;

import com.alibaba.fastjson.JSONArray;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.system.DivisionGradeClient;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 个性化课表
 */
@Controller
@RequestMapping(value = "/classinfo/electiveCourse")
public class ElectiveCourseController extends BaseController implements IQueryController<Classinfo, String>,ICreateController<Classinfo, String>,
        IQueryByPagingController<Classinfo, Map<String, Object>> ,ISaveController<Schedule, String>, IEditController<Classinfo, String> {


    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private ScheduleClient scheduleClient;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    EmployeeClient employeeClient;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;
    @Autowired
    private ClassroomUtils classroomUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private DivisionGradeClient divisionGradeClient;


    @InitBinder("pkScheduls")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("object.");
    }

    @InitBinder("classinfo")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("clas.");
    }


    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Classinfo classInfo) {

        return "/individual/electiveCourseList";
    }


    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Classinfo classinfo) {



        initPageClassAttribute(model,SessionCache.getPkdomain(),classinfo);

        classinfo.setPkDomain(SessionCache.getPkdomain());
        //临时编码
        String autocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.CLASSES);
        classinfo.setCode(autocode);
        //临时开班日期
        classinfo.setDate(new Date());
        classinfo.setStartDate(new Date());
        classinfo.setEndDate(new Date());

        model.addAttribute("classinfo",classinfo);
        List<Student> studentList = new ArrayList<>();
        model.addAttribute("students",studentList);
        employeeUtils.setEmployeeByJobPostForMap(model,"teacherList",JobPostEnum.TEACHER_EMPLOYEE);


        return "/individual/electiveCourse";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Classinfo classinfo, Integer sEcho, Integer start, Integer length) {

        classinfo.setPageNo((start / length) + 1);
        classinfo.setPageSize(length);
        Response<List<Classinfo>> listResponse = classInfoClient.findClassForCourse(classinfo);
        List<Classinfo> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);


    }
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,Schedule param) throws ParseException {

        String pkSchedul = request.getParameter("pkScheduls");
        List<String> list1 = JSONArray.parseArray(pkSchedul, String.class);
        List<Schedule> list = new ArrayList<>();
        if (list1.size()>0){
            for (String s : list1) {
                Schedule schedule = JSONArray.parseObject(s, Schedule.class);
                if (schedule != null){
                    schedule.setPkSchedule(GuidUtils.getGuid());
                    schedule.setPkDomain(SessionCache.getPkdomain());
                    schedule.setCreator(SessionCache.getUserCode());
                    list.add(schedule);
                }
            }
        }

        return scheduleClient.saveAll(list);
    }

    @ResponseBody
    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public String verification(HttpServletRequest request, Model model, Schedule schedule) throws ParseException {


        return classInfoClient.selectiveCheck(schedule);

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
        Response<Classinfo> byPrimaryKey = classInfoClient.findByPrimaryKey(classinfo);
        Classinfo clas = byPrimaryKey.getData();
        model.addAttribute("classinfo", clas);

        //初始化下拉列表
        initPageClassAttribute(model,byPrimaryKey.getData().getPkDomain(),byPrimaryKey.getData());

        Map<String, Object> map = byPrimaryKey.getData().getMap();
        //班级学生资料
        List<Student> list = DataUtils.objectToList(map.get(Light.STUDENT_LIST), Student.class);
        model.addAttribute("students", list);
        //班级老师资料
        List<Employee> employeeList = DataUtils.objectToList(map.get(Light.EMPLOYEE), Employee.class);
        model.addAttribute("employees", employeeList);

        //年级信息
        DivisionGrade divisionGrade = new DivisionGrade();
        divisionGrade.setDivisionId(clas.getDivision());
        Response<List<DivisionGrade>> listResponse = divisionGradeClient.find(divisionGrade);
        model.addAttribute("grade",listResponse.getData());

        sysDictUtils.setModeAttribute(model,"divisions",SysDicEmnuUtils.DIVISION);





        return "/individual/classInfoIndividul";
    }


    private void initPageClassAttribute(Model model,String pkDomain,Classinfo classinfo){

        //教室
        model.addAttribute("classRooms",classroomUtils.getClassRooms());
        //校区
        domainUtils.setModeAttribute(model,"domain",pkDomain);
        //学部
        sysDictUtils.setModeAttribute(model,"divisions",SysDicEmnuUtils.DIVISION);

    }

//    public String saveElective();
    @ResponseBody
    @RequestMapping(value = "/saveElective",method = RequestMethod.POST)
    public String saveElective(HttpServletRequest request, Model model, Classinfo classinfo) {
        if(classinfo.getPkClassinfo()==null || "".equals(classinfo.getPkClassinfo())){
            classinfo.setCreator(SessionCache.getUserCode());
        }
        classinfo.setModifier(SessionCache.getUserCode());

        String doubleGrade = classinfo.getDoubleGrade();
        if (doubleGrade != null && !"".equals(doubleGrade)){
            doubleGrade=","+doubleGrade+",";
            classinfo.setDoubleGrade(doubleGrade);
        }

        classinfo.setDate(DateTimeUtils.getStringToDate(classinfo.getDateTime(),"yyyy-MM-dd hh:mm:ss"));

        classinfo.setStartDate(DateTimeUtils.getStringToDate(classinfo.getStartDateTime(),"yyyy-MM-dd hh:mm:ss"));
        classinfo.setEndDate(DateTimeUtils.getStringToDate(classinfo.getEndDateTime(),"yyyy-MM-dd hh:mm:ss"));

//        return null;
        return classInfoClient.saveElective(classinfo);

    }


}
