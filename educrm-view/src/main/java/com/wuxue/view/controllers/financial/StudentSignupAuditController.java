package com.wuxue.view.controllers.financial;

import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.finance.ExpenseItemClient;
import com.wuxue.view.client.student.StudentSignupClient;
import com.wuxue.view.client.student.StudentSignupDetailsClient;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 费用项目审核
 */
@Controller
@RequestMapping(value = "/finance/studentSignupAudit")
public class StudentSignupAuditController extends BaseController
        implements  IQueryController<StudentSignup, String>,IQueryByPagingController<StudentSignup,Map<String,Object>>,
        IEditController<StudentSignup, String>{

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
    private SysDictUtils sysDictUtils;
    @Autowired
    private ClassInfoClient classInfoClient;


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
        model.addAttribute("urlKey","Audit");
        return "/student/studentReportsList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentSignup student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);
        String dateTime = student.getDateTime();
        student.setIssubmit(1);
        student.setReviewStatus(1);
        if(dateTime!=null && !"".equals(dateTime)){
            student.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            student.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }
        student.setHasClassInfo(null);
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

        Classinfo classinfo = new Classinfo();
        if (signup.getPkClassinfo() != null && !"".equals(signup.getPkClassinfo())) {
            classinfo.setPkClassinfo(signup.getPkClassinfo());
            classinfo = classInfoClient.findByPrimaryKey(classinfo).getData();
        }
        model.addAttribute("classinfo", classinfo);



        return "/financial/studentReportAudit";
    }

    @ResponseBody
    @RequestMapping(value = "/reject",method = RequestMethod.POST)
    public String reject(HttpServletRequest request,StudentSignup studentSignup){
        return studentSignupClient.cancel(studentSignup);
    }


}
