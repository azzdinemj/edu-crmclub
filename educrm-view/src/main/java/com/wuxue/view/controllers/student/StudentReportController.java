package com.wuxue.view.controllers.student;


import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.finance.ExpenseItemClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.student.StudentSignupClient;
import com.wuxue.view.client.student.StudentSignupDetailsClient;
import com.wuxue.view.client.system.SysAutcodeClient;
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
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 新生报名
 */


@Controller
@RequestMapping(value = "/student/studentReport")
public class StudentReportController extends BaseController
        implements IQueryController<StudentSignup, String>, ISaveController<StudentSignup, String>,IQueryByPagingController<StudentSignup,Map<String,Object>>,
        IEditController<StudentSignup, String>, IDeleteController<StudentSignup, String>, ICreateController<StudentSignup, String> {

    @InitBinder("studentSignup")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stu.");
    }

    @InitBinder("studentSignupDetails")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("details.");
    }

    @Autowired
    private StudentSignupClient studentSignupClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private ExpenseItemClient expenseItemClient;

    @Autowired
    private StudentSignupDetailsClient studentSignupDetailsClient;


    /**
     * 初始化下拉列表
     * @param model
     * @param pkDomain
     */
    private void initPageAttribute(Model model,String pkDomain,String pkEmployee){

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
        //招生老师
        employeeUtils.setModeAttribute(model,"employee",pkEmployee);
        //费用项目
        studentUtils.setExpenseItemModeAttribute(model,"expenseItems");
        //支付方式
        sysDictUtils.setModeAttribute(model,"paymethod",SysDicEmnuUtils.PAY_METHOD);

    }

    /**
     * 学生列表
     *
     * @param model
     * @param studentSignup
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentSignup studentSignup) {
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        model.addAttribute("hasClassInfo","false");
        return "/student/studentReportsList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentSignup student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);
        String dateTime = student.getDateTime();
        if(dateTime!=null && !"".equals(dateTime)){
           student.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
           student.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }
        student.setReviewStatus(999);
        Response<List<StudentSignup>> listResponse = studentSignupClient.find(student);
        List<StudentSignup> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改学生
     *
     * @param model
     * @param studentSignup
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, StudentSignup studentSignup) {

        //获取请求资源路径
        String servletPath = request.getServletPath();
        model.addAttribute("URL",servletPath);
        //定位跳转路径
        model.addAttribute("reportkey",1);

        //学年
        sysDictUtils.setModeAttribute(model, "schoolYear", SysDicEmnuUtils.SCHOOL_YEAR);
        Response<StudentSignup> byPrimaryKey = studentSignupClient.findByPrimaryKey(studentSignup);
        StudentSignup signup = byPrimaryKey.getData();
        Student student = studentUtils.getStudent(signup.getPkStudent());
        signup.put("caption",student.getCaption());
        signup.setModifier(SessionCache.getUserCode());
        signup.setLasteditDate(new Date());
        model.addAttribute("student", signup);

        initPageAttribute(model,SessionCache.getPkdomain(),signup.getPkEmployee());

        studentUtils.setDetailsModeAttribute(model,"detailsList",signup.getPkStudentSignup());

        //经办人
        Employee employee = employeeUtils.getEmployee(signup.getResponsiblePerson());
        model.addAttribute("responsiblePerson",employee);



        return "/student/studentReport";
    }

    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, StudentSignup studentSignup) {

        Student student = studentUtils.getStudent(studentSignup.getPkStudent());
        //学年
        sysDictUtils.setModeAttribute(model, "schoolYear", SysDicEmnuUtils.SCHOOL_YEAR);

        //获取请求资源路径
        String servletPath = request.getServletPath();
        int i = servletPath.lastIndexOf("/");
        String substring = servletPath.substring(0,i+1);
        model.addAttribute("URL",substring);
        //定位跳转路径
        model.addAttribute("reportkey",1);

        //初始化下拉框
        initPageAttribute(model,SessionCache.getPkdomain(),student.getPkSysUser());



        //临时编号
        String sysAutocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.SIGNUP);
        studentSignup.setCode(sysAutocode);

        student.put("date",new Date());
        student.setCreator(SessionCache.getUserName());
        student.setCreationDate(new Date());
        student.setModifier(SessionCache.getUserName());
        student.setLasteditDate(new Date());

        studentSignup.setPkStudentSignup(GuidUtils.getGuid());
        studentSignup.setPkStudent(student.getPkStudent());
        studentSignup.put("caption",student.getCaption());
        studentSignup.setDate(new Date());
        studentSignup.setCreationDate(new Date());
        studentSignup.setLasteditDate(new Date());
        studentSignup.setOldSchool(student.getOldSclool());
        studentSignup.put(LinkEntity.CREATOR_ENTITY,EmployeeUtils.getSysUser());
        studentSignup.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        model.addAttribute("student", studentSignup);
        model.addAttribute("userKey","true");
        employeeUtils.setModeAttribute(model,"employee",student.getPkSysUser());


        return "/student/studentReport";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param studentSignup
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentSignup studentSignup) {

        if (studentSignup.getPkStudentSignup() == null) {

        }
//        String userKey = request.getParameter("userKey");
//        if(userKey!= null && userKey.equals("true")){
//            studentSignup.setCreator(SessionCache.getUserCode());
//        }
        studentSignup.setModifier(SessionCache.getUserCode());

        return studentSignupClient.save(studentSignup);

    }

    @RequestMapping(value = "/saveall", method = RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentSignup studentSignup, SignupDetailsList details) {
        String userKey = request.getParameter("userKey");
        if(userKey!= null && userKey.equals("true")){
//            studentSignup.setCreator(SessionCache.getUserCode());
        }
        studentSignup.setModifier(SessionCache.getUserCode());
        studentSignup.put(Light.STUDENT_SIGNUP_DETAILS, details.getDetails());
        studentSignup.setDate(DateTimeUtils.stringToDate(studentSignup.getDateTime()));
        return studentSignupClient.save(studentSignup);

    }


    /**
     * 删除
     *
     * @param studentSignup
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentSignup studentSignup) {
        String response = studentSignupClient.delete(studentSignup.getPkStudentSignup());
        return response;
    }

    /**
     * 提交
     *
     * @param request
     * @param model
     * @param studentSignup
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, Model model, StudentSignup studentSignup, SignupDetailsList details) {
        studentSignup.setSubmitor(SessionCache.getUserCode());
        studentSignup.setIssubmit(1);
        studentSignup.put(Light.STUDENT_SIGNUP_DETAILS, details);
        studentSignup.setDate(DateTimeUtils.stringToDate(studentSignup.getDateTime()));

        return studentSignupClient.submit(studentSignup);
    }

    /**
     * 审核
     *
     * @param request
     * @param model
     * @param studentSignup
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public String audit(HttpServletRequest request, Model model, StudentSignup studentSignup, SignupDetailsList details) {
        studentSignup.setAuditor(SessionCache.getUserCode());
        studentSignup.setIsaudit(1);
        studentSignup.setDate(DateTimeUtils.stringToDate(studentSignup.getDateTime()));

        return studentSignupClient.audit(studentSignup);
    }

    /**
     * getCost
     */
    @ResponseBody
    @RequestMapping(value = "/getCost", method = RequestMethod.POST)
    public Response getCost(HttpServletRequest request, Model model, StudentSignup studentSignup, SignupDetailsList details) {


        return studentSignupClient.getCost(studentSignup);
    }

}
