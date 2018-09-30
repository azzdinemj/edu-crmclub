package com.wuxue.view.controllers.jiedian.student;


import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.InterviewClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.student.StudentSpecialtyClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 意向学生
 */
@Controller("jiedianmystudent")
@RequestMapping(value = "/jiedian/student/mystudent")
public class MyStudentController extends BaseController
implements IQueryController<Student, String>{

    @InitBinder("student")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stu.");
    }

 
    private SysDictUtils sysDictUtils;


    
    /**
     * 学生列表
     *
     * @param model
     * @param student
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Student student) {
      
        model.addAttribute("isType",3);
      
        //年级
        //sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);

        return "/jiedian/student/query";
    }
    
   
}
