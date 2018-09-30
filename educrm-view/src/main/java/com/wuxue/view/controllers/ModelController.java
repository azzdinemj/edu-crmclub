package com.wuxue.view.controllers;

import com.wuxue.model.Classinfo;
import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.view.utils.EmployeeUtils;
import com.wuxue.view.utils.StudentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;
import java.util.List;

/**
 *  查找跳转controller
 */
@Controller
@RequestMapping(value = "/model")
public class ModelController {

    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private EmployeeUtils employeeUtils;

    @RequestMapping(value = "/findstu",method = RequestMethod.GET)
    public String findstu(HttpServletRequest request, Model model, Classinfo classinfo){
        List<Student> studentList = studentUtils.getStudentList();
        model.addAttribute("students",studentList);
        model.addAttribute("classinfo",classinfo);
        return "/teaching/studentlistModel";
    }

    @RequestMapping(value = "/findemp",method = RequestMethod.GET)
    public String findEmp(HttpServletRequest request, Model model, Classinfo classinfo){
        List<Employee> employees = employeeUtils.getEmployees();
        model.addAttribute("employees",employees);
        return "/model/employeeslistModel";
    }

    /**
     * 跳转课程列表model
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getCourses")
    public String getCourses(HttpServletRequest request, Model model){
        String pkEmployee = request.getParameter("pkEmployee");
        model.addAttribute("pkEmployee",pkEmployee);
        return "/model/courselistModel";

    }


}
