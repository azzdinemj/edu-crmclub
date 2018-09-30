package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.*;
import com.wuxue.model.Classinfo;
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
@RequestMapping(value = "/classinfo/classinfo")
public class ClassInfoController extends BaseController
        implements IQueryController<Classinfo, String>, ISaveController<Classinfo, String>,IQueryByPagingController<Classinfo,Map<String,Object>>,
        ICreateController<Classinfo, String>, IEditController<Classinfo, String>, IDeleteController<Classinfo, String> ,
        ISubmitController<Classinfo,String>,IAuditController<Classinfo,String>{

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
     * 新生报名初始化下拉框
     */
    private void initReportPageAttribute(Model model,String pkDomain){
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        //校区
        domainUtils.setModeAttribute(model, "domain", pkDomain);
        //学历
        sysDictUtils.setModeAttribute(model, "educations", SysDicEmnuUtils.EDUCATIONS);
        //学生分类
        sysDictUtils.setModeAttribute(model, "studentclass", SysDicEmnuUtils.STUDENT_CLASS);
        //经办人
        employeeUtils.setModeAttribute(model, "employees");
        //报名项目
        sysDictUtils.setModeAttribute(model,"project",SysDicEmnuUtils.STUDENT_PROJECT);
        //货币种类
        sysDictUtils.setModeAttribute(model,"currency",SysDicEmnuUtils.CURRENCY);

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
        //报名项目
        sysDictUtils.setModeAttribute(model,"project",SysDicEmnuUtils.STUDENT_PROJECT);
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

        //学生信息



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
        if(classinfo.getPkClassinfo()==null || "".equals(classinfo.getPkClassinfo())){
            classinfo.setPkClassinfo(GuidUtils.getGuid());
            classinfo.setCreator(SessionCache.getUserCode());
        }
        classinfo.setModifier(SessionCache.getUserCode());

        classinfo.setDate(DateTimeUtils.stringToDate(classinfo.getDateTime()));
        classinfo.setStartDate(DateTimeUtils.stringToDate(classinfo.getStartDateTime()));
        classinfo.setEndDate(DateTimeUtils.stringToDate(classinfo.getEndDateTime()));

        return classinfoClient.save(classinfo);

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

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Classinfo classinfo) {



        initPageClassAttribute(model,SessionCache.getPkdomain(),classinfo);
        classinfo.put(LinkEntity.CREATOR_ENTITY,EmployeeUtils.getSysUser());
        classinfo.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        classinfo.setCreationDate(new Date());
        classinfo.setLasteditDate(new Date());
        classinfo.setPkDomain(SessionCache.getPkdomain());
        //临时编码
        String autocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.CLASSES);
        classinfo.setCode(autocode);
        //临时开班日期
        classinfo.setDate(new Date());
        classinfo.setStartDate(new Date());
        classinfo.setEndDate(new Date());



        model.addAttribute("classinfo",classinfo);
//        List<Student> studentList = studentUtils.getStudentList();
        List<Student> studentList = new ArrayList<>();
        model.addAttribute("students",studentList);
        //班级老师资料
        List<Employee> employeeList = new ArrayList<>();
        model.addAttribute("employees", employeeList);


        return "/classinfo/classInfo";
    }

    /**
     * 删除
     *
     * @param classinfo
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Classinfo classinfo) {
        String response = classinfoClient.delete(classinfo.getPkClassinfo());
        return response;
    }

    /**
     * 学生报名
     * @param request
     * @param model
     * @param classinfo
     * @return
     */
    @RequestMapping(value = "/stureport",method = RequestMethod.GET)
    public String studentToClass(HttpServletRequest request,Model model,Classinfo classinfo){

        //获取请求资源路径
        String servletPath = request.getServletPath();
        model.addAttribute("URL",servletPath);
        //定位跳转路径
        model.addAttribute("reportkey",2);

        //班级信息
        Response<Classinfo> byPrimaryKey = classinfoClient.findByPrimaryKey(classinfo);
        model.addAttribute("classinfo",byPrimaryKey.getData());

        //费用项目
        ExpenseItem expenseItem = new ExpenseItem();
        Response<List<ExpenseItem>> listResponse = expenseItemClient.find(expenseItem);
        List<ExpenseItem> expenseItems = listResponse.getData();
        model.addAttribute("expenseItems", expenseItems);

        StudentSignup studentSignup = new StudentSignup();
        String pkStudents = request.getParameter("pkStudents");
        if (pkStudents != null && !"".equals(pkStudents)){
            Student student = studentUtils.getStudent(pkStudents);
//            model.addAttribute("students",student);
            studentSignup.setPkStudent(student.getPkStudent());
            studentSignup.put("caption",student.getCaption());

        }
        String pkStudentSignup = request.getParameter("pkStudentSignup");
        if (pkStudentSignup != null && !"".equals(pkStudentSignup)){

        }


        initReportPageAttribute(model,SessionCache.getPkdomain());

        //临时编号
        String sysAutocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.INTERVIEW);
        studentSignup.setCode(sysAutocode);
        studentSignup.setDate(new Date());
        studentSignup.setPkStudentSignup(GuidUtils.getGuid());
//        studentSignup.setCreator(SessionCache.getUserCode());
        studentSignup.setCreationDate(new Date());
//        studentSignup.setModifier(SessionCache.getUserCode());
        studentSignup.put(LinkEntity.CREATOR_ENTITY,EmployeeUtils.getSysUser());
        studentSignup.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        studentSignup.setLasteditDate(new Date());
        model.addAttribute("student", studentSignup);
        model.addAttribute("userKey","true");

        return "/student/studentReport";
    }

    /**
     * 提交
     * @param request
     * @param model
     * @param classinfo
     * @return
     */
    @Override
    public String submit(HttpServletRequest request, Model model, Classinfo classinfo) {

        classinfo.setSubmitor(SessionCache.getUserCode());
        classinfo.setIssubmit(1);
        classinfo.setDate(DateTimeUtils.stringToDate(classinfo.getDateTime()));
        classinfo.setStartDate(DateTimeUtils.stringToDate(classinfo.getStartDateTime()));
        classinfo.setEndDate(DateTimeUtils.stringToDate(classinfo.getEndDateTime()));

        return classinfoClient.submit(classinfo);
    }

    /**
     * 审核
     * @param request
     * @param model
     * @param classinfo
     * @return
     */
    @Override
    public String audit(HttpServletRequest request, Model model, Classinfo classinfo) {
        classinfo.setAuditor(SessionCache.getUserCode());
        classinfo.setIsaudit(1);
        classinfo.setDate(DateTimeUtils.stringToDate(classinfo.getDateTime()));
        classinfo.setStartDate(DateTimeUtils.stringToDate(classinfo.getStartDateTime()));
        classinfo.setEndDate(DateTimeUtils.stringToDate(classinfo.getEndDateTime()));
        return classinfoClient.audit(classinfo);
    }

    /**
     * 班级升班
     * @param request
     * @param model
     * @param classinfo
     * @return
     */
    @RequestMapping(value = "/classon",method = RequestMethod.GET)
    public String classOn(HttpServletRequest request, Model model, Classinfo classinfo){



        Response<Classinfo> byPrimaryKey = classinfoClient.findByPrimaryKey(classinfo);
        Classinfo data = byPrimaryKey.getData();

        //初始化下拉列表
        initPageClassAttribute(model,data.getPkDomain(),data);

        data.setPkParent(data.getPkClassinfo());
        data.setPkClassinfo(null);
        data.setIssubmit(0);
        data.setIsaudit(0);
        data.setStartDate(new Date());
        data.setEndDate(new Date());
        data.setCode(sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.CLASSES));
        Map<String, Object> map = data.getMap();
        List<Student> studentList = DataUtils.objectToList(map.get(Light.STUDENT_LIST), Student.class);
        model.addAttribute("students",studentList);
        data.setDate(new Date());
        model.addAttribute("classinfo",data);

        return "/classinfo/classInfo";
    }

    @RequestMapping(value = "/findstu",method = RequestMethod.GET)
    public String findstu(HttpServletRequest request, Model model, Classinfo classinfo){
        /*List<Student> studentList = studentUtils.getStudentList();
        model.addAttribute("students",studentList);
        model.addAttribute("classinfo",classinfo);*/
        String capid = request.getParameter("id1");
        String pkid = request.getParameter("id2");
        model.addAttribute("capid",capid);
        model.addAttribute("pkid",pkid);
        return "/model/studentlistModel";
    }

    @RequestMapping(value = "/findstu2",method = RequestMethod.GET)
    public String findstu2(HttpServletRequest request, Model model, Classinfo classinfo){
        /*List<Student> studentList = studentUtils.getStudentList();
        model.addAttribute("students",studentList);
        model.addAttribute("classinfo",classinfo);*/
        String capid = request.getParameter("id1");
        String pkid = request.getParameter("id2");
        model.addAttribute("capid",capid);
        model.addAttribute("pkid",pkid);
        return "/model/studentlistModel2";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Classinfo classinfo, Integer sEcho, Integer start, Integer length) {
        classinfo.setPageNo((start/length)+1);
        classinfo.setPageSize(length);
        String dateTime = classinfo.getDateTime();
        if(dateTime!=null && !"".equals(dateTime)){
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date strTime=simpleDateFormat.parse(substring);
                Date endTime=simpleDateFormat.parse(substring1);
                classinfo.setFromDate(strTime);
                classinfo.setToDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Response<List<Classinfo>> listResponse = classinfoClient.find(classinfo);
        List<Classinfo> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @ResponseBody
    @RequestMapping(value = "/insertActivityStudent",method = RequestMethod.POST)
    public String insertActivityStudent(HttpServletRequest request, Model model,ActivityStudent activityStudent){
        activityStudent.setPkActivityStudent(GuidUtils.getGuid());
        activityStudent.setCreator(SessionCache.getUserCode());
        activityStudent.setModifier(SessionCache.getUserCode());
        return activityStudentClient.save(activityStudent);
    }

    @RequestMapping(value = "/studentScore",method = RequestMethod.GET)
    public String studentScore(HttpServletRequest request, Model model,ActivityStudent activityStudent){
        model.addAttribute("activityStudent",activityStudent);
        return "/classinfo/studentScore";
    }

    @RequestMapping(value = "/studentEvaluate",method = RequestMethod.GET)
    public String studentEvaluate(HttpServletRequest request, Model model,ActivityStudent activityStudent){
        model.addAttribute("activityStudent",activityStudent);
        return "/classinfo/studentEvaluate";
    }

    /**
     * 学生转班
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/studentreturn", method = RequestMethod.GET)
    public String studentreturn(HttpServletRequest request, Model model) {

        String pkStudent = request.getParameter("pkStudent");
        String oldPkClassinfo = request.getParameter("oldPkClassinfo");
        model.addAttribute("pkStudent",pkStudent);
        model.addAttribute("oldPkClassinfo",oldPkClassinfo);

//        //报名项目
//        sysDictUtils.setModeAttribute(model,"project",SysDicEmnuUtils.STUDENT_PROJECT);
//        //年级
//        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);


        return "/model/classinfolistModel";
    }

    @RequestMapping(value = "/getEmployeeList",method = RequestMethod.GET)
    public String getEmployeeList(HttpServletRequest request, Model model,ActivityStudent activityStudent){
//        model.addAttribute("activityStudent",activityStudent);
        return "/model/classinfoEmployeeModel";
    }

    @RequestMapping(value = "/addSchedule",method = RequestMethod.GET)
    public String addSchedule(HttpServletRequest request, Model model,Classinfo classinfo){

        Response<Classinfo> byPrimaryKey = classinfoClient.findByPrimaryKey(classinfo);
        model.addAttribute("classinfo",byPrimaryKey.getData());

        ClassTime classTime = new ClassTime();
        if (byPrimaryKey.getData().getDivision() != null) {
            classTime.setType(byPrimaryKey.getData().getDivision());
        }else{
            classTime.setType("201824828559806804");
        }
        Response<List<ClassTime>> listResponse = classTimeClient.find(classTime);
        model.addAttribute("classTimeList",listResponse.getData());

        ClassinfoRoom classinfoRoom = new ClassinfoRoom();
        classinfoRoom.setPkClassinfo(classinfo.getPkClassinfo());
        Response<List<ClassinfoRoom>> room = classInfoRoomClient.findClassinfoRoom(classinfoRoom);
        model.addAttribute("classinfoRoomList",room.getData());

        ClassinfoEmployee employee = new ClassinfoEmployee();
        employee.setPkClassinfo(classinfo.getPkClassinfo());
        Response<List<ClassinfoEmployee>> clientTeacher = classInfoEmployeeClient.findClassinfoTeacher(employee);
        model.addAttribute("classTeacherList",clientTeacher.getData());

        return "/model/scheduleModel";
    }

    @RequestMapping(value = "/entryscores",method = RequestMethod.GET)
    public String entryScores(HttpServletRequest request, Model model,ClassinfoStudent classinfoStudent){
//        model.addAttribute("activityStudent",activityStudent);
//        model.addAttribute("classinfo",classinfo);
        Response<List<ClassinfoStudent>> listResponse = classinfoClient.findClasssStudentScores(classinfoStudent);
        sysDictUtils.setModeAttribute(model,"discipline",SysDicEmnuUtils.STU_DISCIPLINE);//课程
        List<ClassinfoStudent> data = listResponse.getData();

        //考试类型
        sysDictUtils.setModeAttribute(model,"testplans",SysDicEmnuUtils.TEST_PLAS_TYPE);
//       学期
        sysDictUtils.setModeAttribute(model,"term",SysDicEmnuUtils.TERM);

        model.addAttribute("list",data);
        return "/classinfo/studentScoresList";
    }




}
