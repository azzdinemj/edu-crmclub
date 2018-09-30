package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.SchoolBusStudent;
import com.wuxue.model.Student;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SchoolBusStudentClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SysDicEmnuUtils;
import com.wuxue.view.utils.SysDictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
 * 校车学生信息
 */

@Controller
@RequestMapping(value = "/system/schoolBusStudent")
public class SchoolBusStudentController extends BaseController
        implements IQueryController<SchoolBusStudent, String>, ISaveController<SchoolBusStudent, String>,IQueryByPagingController<SchoolBusStudent,Map<String,Object>>,
        ICreateController<SchoolBusStudent, String>, IEditController<SchoolBusStudent, String>, IDeleteController<SchoolBusStudent, String> ,
        ISubmitController<SchoolBusStudent,String>,IAuditController<SchoolBusStudent,String>{

    @Autowired
    private SchoolBusStudentClient schoolBusStudentClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    EmployeeClient employeeClient;

    @InitBinder("schoolBusStudent")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("dorm.");
    }




    /**
     *
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, SchoolBusStudent schoolBusStudent) {
        //报名项目
        sysDictUtils.setModeAttribute(model,"project",SysDicEmnuUtils.STUDENT_PROJECT);
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        return "/schoolBusStudent/classInfoList";
    }

    /**
     * 修改页面
     *
     * @param schoolBusStudent
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, SchoolBusStudent schoolBusStudent) {


        return "/schoolBusStudent/classInfo";
    }


    /**
     * 保存
     *
     * @param schoolBusStudent
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SchoolBusStudent schoolBusStudent) {

        String pkStudents = request.getParameter("pkStudents");
        List<String> list = JSON.parseArray(pkStudents).toJavaList(String.class);
        List<Student> studentList = new ArrayList<>();

        if(list.size()>0){
            for (String s : list) {
                Student student = new Student();
                student.setPkStudent(s);
                studentList.add(student);
            }
        }
        schoolBusStudent.setCreator(SessionCache.getUserCode());
        schoolBusStudent.setModifier(SessionCache.getUserCode());
        schoolBusStudent.setPkDomain(SessionCache.getPkdomain());
        schoolBusStudent.put(Light.STUDENT_LIST,studentList);

        return schoolBusStudentClient.save(schoolBusStudent);

    }


    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, SchoolBusStudent schoolBusStudent) {



        return "/schoolBusStudent/classInfo";
    }

    /**
     * 删除
     *
     * @param schoolBusStudent
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, SchoolBusStudent schoolBusStudent) {
        schoolBusStudent.setModifier(SessionCache.getUserCode());
        String response = schoolBusStudentClient.delete(schoolBusStudent);
        return response;
    }


    /**
     * 提交
     * @param request
     * @param model
     * @param schoolBusStudent
     * @return
     */
    @Override
    public String submit(HttpServletRequest request, Model model, SchoolBusStudent schoolBusStudent) {



        return schoolBusStudentClient.submit(schoolBusStudent);
    }

    /**
     * 审核
     * @param request
     * @param model
     * @param schoolBusStudent
     * @return
     */
    @Override
    public String audit(HttpServletRequest request, Model model, SchoolBusStudent schoolBusStudent) {

        return schoolBusStudentClient.audit(schoolBusStudent);
    }




    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SchoolBusStudent schoolBusStudent, Integer sEcho, Integer start, Integer length) {
        schoolBusStudent.setPageNo((start/length)+1);
        schoolBusStudent.setPageSize(length);
        String dateTime = schoolBusStudent.getDateTime();
        if(dateTime!=null && !"".equals(dateTime)){
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date endTime=simpleDateFormat.parse(substring1);
                Date strTime=simpleDateFormat.parse(substring);
                schoolBusStudent.setFromDate(strTime);
                schoolBusStudent.setToDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Response<List<SchoolBusStudent>> listResponse = schoolBusStudentClient.find(schoolBusStudent);
        List<SchoolBusStudent> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @ResponseBody
    @RequestMapping(value = "/queryStudent")
    public Map<String, Object> queryStudent(HttpServletRequest request, HttpServletResponse response, SchoolBusStudent schoolBusStudent, Integer sEcho, Integer start, Integer length) {
        schoolBusStudent.setPageNo((start/length)+1);
        schoolBusStudent.setPageSize(length);

        Response<List<SchoolBusStudent>> listResponse = schoolBusStudentClient.find(schoolBusStudent);
        List<SchoolBusStudent> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }




}
