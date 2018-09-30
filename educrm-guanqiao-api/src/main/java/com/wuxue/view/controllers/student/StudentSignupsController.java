package com.wuxue.view.controllers.student;


import com.alibaba.fastjson.JSON;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.student.StudentSignupClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.ClasStuListUtils;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.StudentGetDataUtils;
import com.wuxue.view.utils.StudentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 未进班学生
 */
@Controller
@RequestMapping(value = "/student/studentSignups")
public class StudentSignupsController extends BaseController
        implements IQueryController<StudentSignup, String>, ISaveController<StudentSignup, String>,IQueryByPagingController<StudentSignup,Map<String,Object>>,
        ICreateController<StudentSignup, String>, IEditController<StudentSignup, String>, IDeleteController<StudentSignup, String> {

    @InitBinder("studentSignup")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stu.");
    }

    @InitBinder("clasStuListUtils")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("clastu.");
    }

    @Autowired
    private StudentSignupClient studentSignupClient;
    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private StudentGetDataUtils studentGetDataUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private StudentClient studentClient;

    /**
     * 学生列表
     *
     * @param model
     * @param studentSignup
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentSignup studentSignup) {

//        Response<List<StudentSignup>> listResponse = studentSignupClient.getNotPlacement(studentSignup);
//        model.addAttribute("student", listResponse.getData());
        return "/classinfo/studentSignupsList";
    }

    @RequestMapping(value = "/getClassinfoStatus", method = RequestMethod.POST)
    @ResponseBody
    public String getClassinfoStatus(HttpServletRequest request, Model model, StudentSignup studentSignup) {
        Response<StudentSignup> byPrimaryKey = studentSignupClient.findByPrimaryKey(studentSignup);
        if(byPrimaryKey.getData().getPkClassinfo() != null){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * 修改学生
     *
     * @param model
     * @param studentSignup
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, StudentSignup studentSignup) {

        Student student = studentUtils.getStudent(studentSignup.getPkStudent());
        model.addAttribute("student", student);
        //家长资料
        List<Linkman> linkMans = studentGetDataUtils.getLinkMans(studentSignup.getPkStudent());
        model.addAttribute("linkMans", linkMans);

        //学生特长
        List<StudentSpecialty> specialty = studentGetDataUtils.getSpecialty(studentSignup.getPkStudent());
        model.addAttribute("specialty", specialty);

        return "/student/studentReport";
    }

    /**
     * 学生进班
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, StudentSignup studentSignup) {

        Classinfo classinfo = new Classinfo();
        Response<List<Classinfo>> listResponse = classInfoClient.find(classinfo);
        model.addAttribute("classinfo", listResponse.getData());

        studentSignup.setPkStudent("001");
        model.addAttribute("stu", studentSignup);


        return "/classinfo/studentClassInfo";
    }

    @RequestMapping(value = "/createAll", method = RequestMethod.GET)
    public String createAll(HttpServletRequest request, Model model, StudentSignup studentSignup, ClasStuListUtils clasStuListUtils) {

        Classinfo classinfo = new Classinfo();
        Response<List<Classinfo>> listResponse = classInfoClient.find(classinfo);
        model.addAttribute("classinfo", listResponse.getData());

//        List<String> pkStudents = clasStuListUtils.getPkStudents();
//        String jsonString = JSON.toJSONString(studentSignup.getPkStudent());

        model.addAttribute("clastu", studentSignup.getPkStudent());
        model.addAttribute("pkStudentSignup", studentSignup.getPkStudentSignup());


        return "/classinfo/studentClassInfo";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param studentSignup
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentSignup studentSignup) {


        return studentSignupClient.save(studentSignup);

    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public String updateStatus(HttpServletRequest request, Model model, StudentSignup studentSignup) {
        studentSignup.setModifier(SessionCache.getUserCode());
        return studentSignupClient.updateStatus(studentSignup);
    }

    /**
     * 保存学生信息
     *
     * @param request
     * @param model
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStudent(HttpServletRequest request, Model model, Student student) {
        String byKey = request.getParameter("byKey");
        if (byKey != null && byKey.length() > 0) {
            int i = Integer.parseInt(byKey);
            if (i == 1) {
                student.setCreator(SessionCache.getUserCode());
            }
            student.setModifier(SessionCache.getUserCode());
        }
        return studentClient.save(student);
    }

    /**
     * 删除
     *
     * @param studentSignup
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentSignup studentSignup) {
        String response = studentSignupClient.delete(studentSignup.getPkStudentSignup());
        return response;
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentSignup studentSignup, Integer sEcho, Integer start, Integer length) {
        studentSignup.setPageNo((start/length)+1);
        studentSignup.setPageSize(length);
        Response<List<StudentSignup>> listResponse = studentSignupClient.getNotPlacement(studentSignup);
        List<StudentSignup> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

}
