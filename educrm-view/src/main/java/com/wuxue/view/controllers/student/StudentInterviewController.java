package com.wuxue.view.controllers.student;


import com.wuxue.model.*;

import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.InterviewClient;
import com.wuxue.view.client.student.StudentClient;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 新生面试
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 新生面试
 */
@Controller
@RequestMapping(value = "/student/studentInterview")
public class StudentInterviewController extends BaseController
        implements IQueryController<StudentInterview, String>, ISaveController<StudentInterview, String>,IQueryByPagingController<StudentInterview,Map<String,Object>>,
        ICreateController<StudentInterview, String>, IEditController<StudentInterview, String>, IDeleteController<StudentInterview, String>,
        ISubmitController<StudentInterview, String>, IAuditController<StudentInterview, String> {

    @InitBinder("studentInterview")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stu.");
    }

    @Autowired
    private InterviewClient interviewClient;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysAutcodeClient autcodeClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    /**
     * 面试学生列表
     *
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentInterview studentInterview) {
//        Response<List<StudentInterview>> listResponse = interviewClient.find(studentInterview);
//
//        model.addAttribute("student", listResponse.getData());
        return "/student/studentInterviewList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentInterview student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);
        String dateTime = student.getDateTime();

//        DateTimeUtils.StringSubToDate(dateTime);
        if (dateTime != null && !"".equals(dateTime)){

            student.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            student.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }
        Response<List<StudentInterview>> listResponse = interviewClient.find(student);
        List<StudentInterview> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改学生
     *
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, StudentInterview studentInterview) {
        Response<StudentInterview> byPrimaryKey = interviewClient.findByPrimaryKey(studentInterview);
        StudentInterview data = byPrimaryKey.getData();

        initPageAttribute(model, data.getPkDomain(),data.getPkEmployee());

        Student student = studentUtils.getStudent(data.getPkStudent());
        data.put("student", student);


        model.addAttribute("students", byPrimaryKey.getData());


        return "/student/studentInterview";
    }

    /**
     * 初始化下拉列表
     *
     * @param model
     * @param pkDomain
     */
    public void initPageAttribute(Model model, String pkDomain,String pkEmployee) {

        //校区
        domainUtils.setModeAttribute(model, "domain", pkDomain);
        //面试科目
        sysDictUtils.setModeAttribute(model, "project", SysDicEmnuUtils.STUDENT_PROJECT);
        //面试老师
        employeeUtils.setModeAttribute(model, "employee",pkEmployee);
    }

    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, StudentInterview studentInterview) {

        Student student = studentUtils.getStudent(studentInterview.getPkStudent());
        studentInterview.put("student", student);

        initPageAttribute(model, student.getPkDomain(),null);

        //临时编号
        String code = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.INTERVIEW);
        studentInterview.setCode(code);
        studentInterview.setDate(new Date());
        studentInterview.setPkStudentInterview(GuidUtils.getGuid());
        studentInterview.setCaption(student.getProgram());
        studentInterview.setCreationDate(new Date());
        studentInterview.setLasteditDate(new Date());
        studentInterview.put(LinkEntity.CREATOR_ENTITY,EmployeeUtils.getSysUser());
        studentInterview.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        model.addAttribute("students", studentInterview);
        model.addAttribute("userKey","true");


        return "/student/studentInterview";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentInterview studentInterview) {

//        if (studentInterview.getPkStudentInterview() == null && "".equals(studentInterview.getPkStudentInterview())) {
//            studentInterview.setCreator(SessionCache.getUserCode());
//            studentInterview.setPkStudentInterview(GuidUtils.getGuid());
//        }
        String userKey = request.getParameter("userKey");
        if(userKey!= null && userKey.equals("true")){
            studentInterview.setCreator(SessionCache.getUserCode());
        }
        studentInterview.setModifier(SessionCache.getUserCode());
        studentInterview.setDate(DateTimeUtils.stringToDate(studentInterview.getDateTime()));

        return interviewClient.save(studentInterview);

    }


    /**
     * 删除
     *
     * @param studentSignup
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentInterview studentSignup) {
        String response = interviewClient.delete(studentSignup.getPkStudentInterview());
        return response;
    }


    /**
     * 审核
     *
     * @param request
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    @ResponseBody
    public String audit(HttpServletRequest request, Model model, StudentInterview studentInterview) {
//        studentInterview.setIsaudit(1);
        studentInterview.setAuditor(SessionCache.getUserCode());
        studentInterview.setDate(DateTimeUtils.stringToDate(studentInterview.getDateTime()));

        return interviewClient.audit(studentInterview);
    }

    /**
     * 提交
     *
     * @param request
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    @ResponseBody
    public String submit(HttpServletRequest request, Model model, StudentInterview studentInterview) {
        studentInterview.setIssubmit(1);
        studentInterview.setSubmitor(SessionCache.getUserCode());
        studentInterview.setDate(DateTimeUtils.stringToDate(studentInterview.getDateTime()));

        return interviewClient.submit(studentInterview);
    }
}
