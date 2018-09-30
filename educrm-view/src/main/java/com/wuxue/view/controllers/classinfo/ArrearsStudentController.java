package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级升班
 */

@Controller
@RequestMapping(value = "/classinfo/arrearsStudent")
public class ArrearsStudentController extends BaseController
        implements IQueryController<Student, String>,IQueryByPagingController<Student,Map<String,Object>> {

    @Autowired
    private StudentClient studentClient;


    /**
     * 学生列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Student student) {

        return "/classinfo/arrearsStudentList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Student student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);

        Response<List<Student>> listResponse = studentClient.findArrearsStudent(student);
        List<Student> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }



}
