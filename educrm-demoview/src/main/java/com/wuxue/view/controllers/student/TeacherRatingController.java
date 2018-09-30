package com.wuxue.view.controllers.student;

import com.wuxue.model.Domain;
import com.wuxue.model.Student;
import com.wuxue.model.StudentCredit;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentCreditClient;
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
 * 老师评分
 */
@Controller
@RequestMapping(value = "/student/teacherRating")
public class TeacherRatingController extends BaseController
        implements IQueryController<StudentCredit,String>,ISaveController<StudentCredit,String>,
        ICreateController<StudentCredit,String>,IEditController<StudentCredit,String>,IDeleteController<StudentCredit,String> {


    @InitBinder("studentCredit")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("cre.");
    }

    @Autowired
    private StudentCreditClient studentCreditClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;

    /**
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentCredit studentCredit)  {
        Response<List<StudentCredit>> listResponse = studentCreditClient.find(studentCredit);

        model.addAttribute("list",listResponse.getData() );
        return "/student/studentCreditList";
    }



    @Override
    public String create(HttpServletRequest request, Model model, StudentCredit studentCredit) {

        Student student = studentUtils.getStudent(studentCredit.getPkStudent());
        model.addAttribute("student",student);
        Domain domain = domainUtils.getDomain(student.getPkDomain());
        model.addAttribute("domain",domain);

        studentCredit.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        studentCredit.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        studentCredit.setCreationDate(new Date());
        studentCredit.setLasteditDate(new Date());
        model.addAttribute("credit",studentCredit);

        return "/student/addTeacherRating";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentCredit studentCredit) {
        return studentCreditClient.delete(studentCredit.getPkStudentCredit());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, StudentCredit studentCredit) {
        Response<StudentCredit> byPrimaryKey = studentCreditClient.findByPrimaryKey(studentCredit);
        model.addAttribute("studentCredit",byPrimaryKey.getData());
        return "/student/editStudentCredit";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentCredit studentCredit) {
        if (studentCredit.getPkStudentCredit() == null || "".equals(studentCredit.getPkStudentCredit())){
            studentCredit.setPkStudentCredit(GuidUtils.getGuid());
            studentCredit.setCreator(SessionCache.getUserCode());
        }
        studentCredit.setModifier(SessionCache.getUserCode());
        return studentCreditClient.save(studentCredit);
    }


}
