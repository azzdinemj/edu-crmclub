package com.wuxue.view.controllers.student;


import com.wuxue.model.Student;
import com.wuxue.model.StudentSpecialty;
import com.wuxue.model.StudentSpecialty;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.InterviewClient;
import com.wuxue.view.client.student.StudentSpecialtyClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.EmployeeUtils;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.StudentUtils;
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
 * 特长爱好
 */


@Controller
@RequestMapping(value = "/student/studentSpecialty")
public class StudentSpecialtyController extends BaseController
        implements IQueryController<StudentSpecialty,String>,ISaveController<StudentSpecialty, String>,
        ICreateController<StudentSpecialty, String>,IEditController<StudentSpecialty, String>,IDeleteController<StudentSpecialty, String> {

    @InitBinder("studentSpecialty")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("stu.");
    }

    @Autowired
    private StudentSpecialtyClient studentSpecialtyClient;
    @Autowired
    private StudentUtils studentUtils;

    /**
     * 学生列表
     * @param model
     * @param studentSpecialty
     * @return
     */
   @Override
    public String query(HttpServletRequest request, Model model, StudentSpecialty studentSpecialty){
        Response<List<StudentSpecialty>> listResponse = studentSpecialtyClient.find(studentSpecialty);
        model.addAttribute("student",listResponse.getData());
        return "/student/listenList";
    }

    /**
     *修改学生
     * @param model
     * @param studentSpecialty
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model,StudentSpecialty studentSpecialty){
       /* Response<StudentSpecialty> byPrimaryKey = interviewClient.findByPrimaryKey(studentSpecialty);

        model.addAttribute("student",byPrimaryKey.getData());*/

        return "/student/editStudentListen";
    }

    /**
     * 添加页面
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model,StudentSpecialty studentSpecialty){

        Student student = studentUtils.getStudent(studentSpecialty.getPkStudent());
        model.addAttribute("student",student);

        studentSpecialty.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        studentSpecialty.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        studentSpecialty.setCreationDate(new Date());
        studentSpecialty.setLasteditDate(new Date());
        model.addAttribute("specialty",studentSpecialty);
        return "/student/addStuSpecialty";
    }


    /**
     * 保存
     * @param request
     * @param model
     * @param studentSpecialty
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentSpecialty studentSpecialty) {

        if(studentSpecialty.getPkStudentSpecialty()==null||"".equals(studentSpecialty.getPkStudentSpecialty())){
            studentSpecialty.setModifier(SessionCache.getUserCode());
            studentSpecialty.setPkStudentSpecialty(GuidUtils.getGuid());
        }
        studentSpecialty.setModifier(SessionCache.getUserCode());

        return studentSpecialtyClient.save(studentSpecialty);

    }

    /**
     * 审核
     * @param studentSignup
     * @param model
     * @return
     */
    @RequestMapping(value = "/auditing")
    public String interview(StudentSpecialty studentSignup,Model model){

        Response<StudentSpecialty> byPrimaryKey = studentSpecialtyClient.findByPrimaryKey(studentSignup);
        model.addAttribute("stu",byPrimaryKey);

        return "/student/addInterview";
    }

    /**
     * 删除
     * @param studentSignup
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model,StudentSpecialty studentSignup){
        String response = studentSpecialtyClient.delete(studentSignup.getPkStudentSpecialty());
        return response;
    }


}
