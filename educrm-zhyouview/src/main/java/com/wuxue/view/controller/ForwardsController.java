package com.wuxue.view.controller;

import com.wuxue.model.Student;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
* @Description: 页面跳转控制
* @author wanghao
* @date  9:27 2018/3/12
* @version V1.0
*/
@Controller
@RequestMapping(value="/static")
public class ForwardsController {

    @Autowired
    private StudentClient studentClient;


//    @RequestMapping(value="question_",method = RequestMethod.GET)
//    public String question_() { return "/question_"; }

//    @RequestMapping(value="history",method = RequestMethod.GET)
//    public String history() { return "/history"; }

//    @RequestMapping(value="historylist",method = RequestMethod.GET)
//    public String historylist() { return "/historylist"; }

//    @RequestMapping(value="question_bank",method = RequestMethod.GET)
//    public String question_bank() { return "/question_bank"; }

   /* @RequestMapping(value="home",method = RequestMethod.GET)
    public String home() { return "/home"; }*/

   /* @RequestMapping(value="classDetails",method = RequestMethod.GET)
    public  String  classDetails()
    {
        return  "/class_details";
    }*/

  /*  @RequestMapping(value="haveClass",method = RequestMethod.GET)
    public  String  haveClass(){
        return  "/have_class";
    }*/


    /*@RequestMapping(value = "question",method = RequestMethod.GET)
    public  String  question(){return "/question";};*/


    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public  String  about(){return "/about";};

//    @RequestMapping(value = "teacherlist",method = RequestMethod.GET)
//    public  String  teacherlist(){
//        return "/teacherlist";};

//    @RequestMapping(value = "teacher",method = RequestMethod.GET)
//    public  String  teacher(){
//        return "/teacher";};

   /* @RequestMapping(value = "set",method = RequestMethod.GET)
    public  String  set(HttpSession session, Model model){

        String str=(String)session.getAttribute("studentpk");
        Response<Student> response = studentClient.findByPrimaryKey(str);
        Student student=response.getData();
        model.addAttribute("studentUser",student);
        return "/set"
    ;};*/


//    @RequestMapping(value = "personalcenter",method = RequestMethod.GET)
//    public  String  personalcenter(){return "/personalcenter";};


}
