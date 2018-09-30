package com.wuxue.view.controllers.individual;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.*;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 个性化课表
 */
@Controller
@RequestMapping(value = "/individual/individual")
public class IndividualController extends BaseController implements IQueryController<Classinfo, String>,IDeleteController<String,String>,
        IQueryByPagingController<Schedule, Map<String, Object>> ,ISaveController<ClassinfoStudent, String>, IEditController<Classinfo, String> {


    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private ScheduleClient scheduleClient;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    EmployeeClient employeeClient;
    @Autowired
    private ClassInfoStudentClient classInfoStudentClient;


    @InitBinder("pkScheduls")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("object.");
    }

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Classinfo classInfo) {

        return "/individual/individualList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Schedule schedule, Integer sEcho, Integer start, Integer length) {

        schedule.setPageNo((start / length) + 1);
        schedule.setPageSize(length);
        Response<List<Schedule>> listResponse = scheduleClient.findElecSchedule(schedule);
        List<Schedule> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);


    }
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,ClassinfoStudent classinfoStudent) throws ParseException {

        String pkClassinfo = request.getParameter("pkClassinfo");


        return classInfoClient.saveForSche(pkClassinfo);
    }
//    @Override
//    @ResponseBody
//    public String save(HttpServletRequest request, Model model,Schedule param) throws ParseException {
//
//        String pkSchedul = request.getParameter("pkScheduls");
//        List<String> list1 = JSONArray.parseArray(pkSchedul, String.class);
//        List<Schedule> list = new ArrayList<>();
//        if (list1.size()>0){
//            for (String s : list1) {
//                Schedule schedule = JSONArray.parseObject(s, Schedule.class);
//                if (schedule != null){
//                    schedule.setPkSchedule(GuidUtils.getGuid());
//                    schedule.setPkDomain(SessionCache.getPkdomain());
//                    schedule.setCreator(SessionCache.getUserCode());
//                    list.add(schedule);
//                }
//            }
//        }
//
//
//        return classInfoClient.saveForSche(list1);
//    }

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
        //班级学生成绩信息
        List<StudentScores> scoresList = DataUtils.objectToList(map.get(Light.STUDENT_TEST_PLANS_SCORES), StudentScores.class);
        model.addAttribute("studnetScores",scoresList);

        //学期
        sysDictUtils.setModeAttribute(model,"terms", SysDicEmnuUtils.TERM);



        return "/individual/classInfoIndividul";
    }


    private void initPageClassAttribute(Model model,String pkDomain,Classinfo classinfo){

        //班主任
        employeeUtils.setModeAttribute(model,"headTeacher",classinfo.getHeadTeacher());
        //副班主任
        employeeUtils.setModeAttribute(model,"secondTeacher",classinfo.getSecondTeacher());
        //教学主管
        employeeUtils.setModeAttribute(model,"director",classinfo.getDirector());
        //项目
        sysDictUtils.setModeAttribute(model,"project",SysDicEmnuUtils.STUDENT_PROJECT);
        //年级
        sysDictUtils.setModeAttribute(model,"grade",SysDicEmnuUtils.STUDENT_GRADE);
        //学部
        sysDictUtils.setModeAttribute(model,"divisions",SysDicEmnuUtils.DIVISION);

        //教室
        model.addAttribute("classRooms",studentUtils.getClassRooms());
        //校区
        domainUtils.setModeAttribute(model,"domain",pkDomain);

    }


    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, String pkClassinfo) {


        return classInfoStudentClient.deleteByClassAndStu(pkClassinfo);
    }
}
