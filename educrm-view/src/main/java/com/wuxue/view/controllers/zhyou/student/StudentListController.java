package com.wuxue.view.controllers.zhyou.student;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学生管理
 */
@Controller
@RequestMapping(value = "/student/studentlist")
public class StudentListController extends BaseController implements
        IQueryController<Student,String>, IEditController<Student, String>,ISaveController<Student,String>,IDeleteController<Student,String>,
        IAuditController<Student,Response>,IQueryByPagingController<Student,Map<String,Object>>{

    @InitBinder("student")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("stu.");
    }

    @Autowired
    private StudentClient studentClient;


    @Override
    public String edit(HttpServletRequest request, Model model, Student student) {
        if(student.getPkStudent()!=null&&student.getPkStudent()!=""){
            Response<Student> byPrimary = studentClient.findByPrimarypk(student.getPkStudent());
            Student stu = byPrimary.getData();
            model.addAttribute("student", stu);
        }
        return "/zhyou/student/student";
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Student student) {
        if(student.getPkStudent()==null||student.getPkStudent()==""){
            student.setPkDomain("2");
            student.setCreator(SessionCache.getUserName());
            student.setModifier(SessionCache.getUserName());
        }else{
            student.setModifier(SessionCache.getUserName());
        }
        return studentClient.saveStudentzhy(student);

    }


    @Override
    public String query(HttpServletRequest request, Model model, Student student) {
//        //页码，每页数量
//        String str=request.getParameter("pageNo");
//        if(str!=null&&str!=""){
//            int pageNo=Integer.parseInt(str);
//            student.setPageNo(pageNo);
//        }else{
//            student.setPageNo(Contsants.PAGE_NO);
//        }
//        student.setPageSize(Contsants.PAGE_SIZE);
//
//        student.setPkDomain(Contsants.PK_DOMAIN_ZHY);//数据分离
//        Response<PageInfo<Student>> response=studentClient.findStudent(student);
//        PageInfo<Student> pageInfo=response.getData();
//
//        model.addAttribute("Students", pageInfo);
        return "/zhyou/student/studentlist";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Student student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);
        student.setPkDomain(Contsants.PK_DOMAIN_ZHY);//数据分离
        student.setIsvalid(1);
        Response<PageInfo<Student>> resp=studentClient.findStudent(student);
        List<Student> data = resp.getData().getList();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(resp.getData().getTotal(),resp.getData().getTotal(),data);
    }

    @Override
    @ResponseBody
    public Response audit(HttpServletRequest request, Model model, Student student) {
        return studentClient.audit(student);
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Student student) {
        return studentClient.delete(student.getPkStudent());
    }
}
