package com.wuxue.view.controllers.zhyou.student;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Course;
import com.wuxue.model.Linkman;
import com.wuxue.model.Student;
import com.wuxue.model.StudentSignup;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.CourseClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.student.StudentSignupClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 学生管理
 */
@Controller
@RequestMapping(value = "/student/studentlist")
public class StudentListController extends BaseController implements
        IQueryController<Student,String>, IEditController<Student, String>,ISaveController<Student,String>,
        IAuditController<Student,Response>{

    @InitBinder("student")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("stu.");
    }

    @Autowired
    private StudentClient studentClient;


    @Override
    public String edit(HttpServletRequest request, Model model, Student student) {
        Response<Student> byPrimary = studentClient.findByPrimarypk(student.getPkStudent());
        Student stu = byPrimary.getData();
        model.addAttribute("student", stu);
        return "/zhyou/student/student";
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Student student) {
        return studentClient.save(student);
    }


    @Override
    public String query(HttpServletRequest request, Model model, Student student) throws IOException {
        //页码，每页数量
        String str=request.getParameter("pageNo");
        if(str!=null&&str!=""){
            int pageNo=Integer.parseInt(str);
            student.setPageNo(pageNo);
        }else{
            student.setPageNo(Contsants.PAGE_NO);
        }
        student.setPageSize(Contsants.PAGE_SIZE);

        Response<PageInfo<Student>> response=studentClient.findStudent(student);
        PageInfo<Student> pageInfo=response.getData();

        model.addAttribute("Students", pageInfo);
        return "/zhyou/student/studentlist";
    }

    @Override
    @ResponseBody
    public Response audit(HttpServletRequest request, Model model, Student student) {
        return studentClient.audit(student);
    }

}
