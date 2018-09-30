package com.wuxue.view.controllers.student;

import com.wuxue.model.Domain;
import com.wuxue.model.Student;
import com.wuxue.model.StudentEduExperience;
import com.wuxue.model.StudentEduExperience;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentEduExperienceClient;
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
@RequestMapping(value = "/student/studentEduExperience")
public class StudentEduExperienceController extends BaseController
        implements IQueryController<StudentEduExperience,String>,ISaveController<StudentEduExperience,String>,
        ICreateController<StudentEduExperience,String>,IEditController<StudentEduExperience,String>,IDeleteController<StudentEduExperience,String> {


    @InitBinder("studentEduExperience")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("stu.");
    }

    @Autowired
    private StudentEduExperienceClient studentEduExperienceClient;
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
    public String query(HttpServletRequest request, Model model, StudentEduExperience studentEduExperience)  {
        Response<List<StudentEduExperience>> listResponse = studentEduExperienceClient.find(studentEduExperience);

        model.addAttribute("list",listResponse.getData() );
        return "/student/studentEduExperienceList";
    }



    @Override
    public String create(HttpServletRequest request, Model model, StudentEduExperience studentEduExperience) {

        Student student = studentUtils.getStudent(studentEduExperience.getPkStudent());
        model.addAttribute("student",student);
        Domain domain = domainUtils.getDomain(student.getPkDomain());
        model.addAttribute("domain",domain);

        studentEduExperience.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        studentEduExperience.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        studentEduExperience.setCreationDate(new Date());
        studentEduExperience.setLasteditDate(new Date());
        model.addAttribute("experience",studentEduExperience);

        return "/student/addEduExperience";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentEduExperience studentEduExperience) {
        return studentEduExperienceClient.delete(studentEduExperience.getPkStudentEduExperience());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, StudentEduExperience studentEduExperience) {
        Response<StudentEduExperience> byPrimaryKey = studentEduExperienceClient.findByPrimaryKey(studentEduExperience);
        model.addAttribute("studentEduExperience",byPrimaryKey.getData());
        return "/student/editStudentEduExperience";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentEduExperience studentEduExperience) {
        if(studentEduExperience.getPkStudentEduExperience() == null || "".equals(studentEduExperience.getPkStudentEduExperience())){
            studentEduExperience.setCreator(SessionCache.getUserCode());
            studentEduExperience.setPkStudentEduExperience(GuidUtils.getGuid());

        }
        studentEduExperience.setModifier(SessionCache.getUserCode());
        return studentEduExperienceClient.save(studentEduExperience);
    }


}
