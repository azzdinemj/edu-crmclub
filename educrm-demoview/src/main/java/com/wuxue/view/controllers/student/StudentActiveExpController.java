package com.wuxue.view.controllers.student;

import com.wuxue.model.Domain;
import com.wuxue.model.Student;
import com.wuxue.model.StudentActivityExp;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentActivityExpClient;
import com.wuxue.view.client.student.StudentClient;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 活动成绩管理
 */
@Controller
@RequestMapping(value = "/student/studentActivityExp")
public class StudentActiveExpController extends BaseController
        implements IQueryController<StudentActivityExp,String>,ISaveController<StudentActivityExp,String>,
        ICreateController<StudentActivityExp,String>,IEditController<StudentActivityExp,String>,IDeleteController<StudentActivityExp,String> {


    @InitBinder("studentActivityExp")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("exp.");
    }

    @Autowired
    private StudentActivityExpClient studentActivityExpClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysDictUtils sysDictUtils;


    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentActivityExp studentActivityExp)  {
        Response<List<StudentActivityExp>> listResponse = studentActivityExpClient.find(studentActivityExp);

        model.addAttribute("list",listResponse.getData() );
        return "/student/StudentActivityExpList";
    }



    @Override
    public String create(HttpServletRequest request, Model model, StudentActivityExp studentActivityExp) {

        Student student = studentUtils.getStudent(studentActivityExp.getPkStudent());
        model.addAttribute("student",student);
        Domain domain = domainUtils.getDomain(student.getPkDomain());
        model.addAttribute("domain",domain);

        //社会实践活动
        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STU_ACTIVITY_EXP);
        model.addAttribute("sysDictValue",sysDictValue);
        //活动类型
        List<SysDictValues> sysDictValueType = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STU_ACTIVITYEXPTYPE);
        model.addAttribute("type",sysDictValueType);

        studentActivityExp.setCreator(SessionCache.getUserCode());
        studentActivityExp.setCreationDate(new Date());
        studentActivityExp.setModifier(SessionCache.getUserCode());
        studentActivityExp.setLasteditDate(new Date());
        model.addAttribute("activity",studentActivityExp);
        return "/student/addActivityExp";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentActivityExp studentActivityExp) {
        return studentActivityExpClient.delete(studentActivityExp.getPkStudentActivityExp());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, StudentActivityExp studentActivityExp) {
        Response<StudentActivityExp> byPrimaryKey = studentActivityExpClient.findByPrimaryKey(studentActivityExp);
        model.addAttribute("studentActivityExp",byPrimaryKey.getData());
        return "/student/editActivityExp";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentActivityExp studentActivityExp) {
        if (studentActivityExp.getPkStudentActivityExp()==null || "".equals(studentActivityExp.getPkStudentActivityExp())){
            studentActivityExp.setPkStudentActivityExp(GuidUtils.getGuid());
            studentActivityExp.setCreator(SessionCache.getUserCode());
        }
        studentActivityExp.setModifier(SessionCache.getUserCode());


        return studentActivityExpClient.save(studentActivityExp);
    }


}
