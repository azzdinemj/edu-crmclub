package com.wuxue.view.controllers.student;


import com.wuxue.model.StudentInterview;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.InterviewClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 新生试听
 */


@Controller
@RequestMapping(value = "/student/studentListen")
public class StudentListenController extends BaseController
        implements IQueryController<StudentInterview,String>,ISaveController<StudentInterview, String>,
        ICreateController<StudentInterview, String>,IEditController<StudentInterview, String>,IDeleteController<StudentInterview, String> {

    @InitBinder("studentInterview")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("stu.");
    }

    @Autowired
    private InterviewClient interviewClient;

    /**
     * 学生列表
     * @param model
     * @param studentInterview
     * @return
     */
   @Override
    public String query(HttpServletRequest request, Model model, StudentInterview studentInterview){
        Response<List<StudentInterview>> listResponse = interviewClient.find(studentInterview);
        model.addAttribute("student",listResponse.getData());
        return "/student/listenList";
    }

    /**
     *修改学生
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model,StudentInterview studentInterview){
       /* Response<StudentInterview> byPrimaryKey = interviewClient.findByPrimaryKey(studentInterview);

        model.addAttribute("student",byPrimaryKey.getData());*/

        return "/student/editStudentListen";
    }

    /**
     * 添加页面
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model,StudentInterview studentInterview){
        return "/student/addlisten";
    }


    /**
     * 保存
     * @param request
     * @param model
     * @param studentInterview
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentInterview studentInterview) {

        return interviewClient.save(studentInterview);

    }

    /**
     * 审核
     * @param studentSignup
     * @param model
     * @return
     */
    @RequestMapping(value = "/auditing")
    public String interview(StudentInterview studentSignup,Model model){

        Response<StudentInterview> byPrimaryKey = interviewClient.findByPrimaryKey(studentSignup);
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
    public String delete(HttpServletRequest request, Model model,StudentInterview studentSignup){
        String response = interviewClient.delete(studentSignup.getPkStudentInterview());
        return response;
    }


}
