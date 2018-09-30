package com.wuxue.view.controllers.jiedian.student;


import com.wuxue.model.Student;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.SysDictUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
