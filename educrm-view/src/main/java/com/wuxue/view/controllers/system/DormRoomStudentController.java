package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ActivityStudentClient;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.classInfo.ClassInfoStudentClient;
import com.wuxue.view.client.classInfo.StudentScoresClient;
import com.wuxue.view.client.finance.ExpenseItemClient;
import com.wuxue.view.client.system.DormRoomStudentClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级信息
 */

@Controller
@RequestMapping(value = "/system/dormRoomStudent")
public class DormRoomStudentController extends BaseController
        implements IQueryController<DormRoomStudent, String>, ISaveController<DormRoomStudent, String>,IQueryByPagingController<DormRoomStudent,Map<String,Object>>,
        ICreateController<DormRoomStudent, String>, IEditController<DormRoomStudent, String>, IDeleteController<DormRoomStudent, String> ,
        ISubmitController<DormRoomStudent,String>,IAuditController<DormRoomStudent,String>{

    @Autowired
    private DormRoomStudentClient dormRoomStudentClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    EmployeeClient employeeClient;

    @InitBinder("dormRoomStudent")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("dorm.");
    }




    /**
     * 班级列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, DormRoomStudent dormRoomStudent) {
//        //报名项目
//        sysDictUtils.setModeAttribute(model,"project",SysDicEmnuUtils.STUDENT_PROJECT);
//        //年级
//        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        return "/dormRoomStudent/classInfoList";
    }

    /**
     * 修改页面
     *
     * @param dormRoomStudent
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, DormRoomStudent dormRoomStudent) {


        return "/dormRoomStudent/classInfo";
    }


    /**
     * 保存
     *
     * @param dormRoomStudent
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, DormRoomStudent dormRoomStudent) {

        String pkStudents = request.getParameter("pkStudents");
        List<String> list = JSON.parseArray(pkStudents).toJavaList(String.class);
        List<Student> students = new ArrayList<>();

        if(list.size()>0){
            for (String s : list) {
                Student student = new Student();
                student.setPkStudent(s);
                students.add(student);
            }
        }
        dormRoomStudent.setCreator(SessionCache.getUserCode());
        dormRoomStudent.setModifier(SessionCache.getUserCode());
        dormRoomStudent.setPkDomain(SessionCache.getPkdomain());
        dormRoomStudent.put(Light.STUDENT_LIST,students);

        return dormRoomStudentClient.save(dormRoomStudent);

    }


    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, DormRoomStudent dormRoomStudent) {



        return "/dormRoomStudent/classInfo";
    }

    /**
     * 删除
     *
     * @param dormRoomStudent
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, DormRoomStudent dormRoomStudent) {
        dormRoomStudent.setIsvalid(1);
        dormRoomStudent.setModifier(SessionCache.getUserCode());
        String response = dormRoomStudentClient.delete(dormRoomStudent);
        return response;
    }


    /**
     * 提交
     * @param request
     * @param model
     * @param dormRoomStudent
     * @return
     */
    @Override
    public String submit(HttpServletRequest request, Model model, DormRoomStudent dormRoomStudent) {



        return dormRoomStudentClient.submit(dormRoomStudent);
    }

    /**
     * 审核
     * @param request
     * @param model
     * @param dormRoomStudent
     * @return
     */
    @Override
    public String audit(HttpServletRequest request, Model model, DormRoomStudent dormRoomStudent) {

        return dormRoomStudentClient.audit(dormRoomStudent);
    }




    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, DormRoomStudent dormRoomStudent, Integer sEcho, Integer start, Integer length) {
        dormRoomStudent.setPageNo((start/length)+1);
        dormRoomStudent.setPageSize(length);
        String dateTime = dormRoomStudent.getDateTime();
        if(dateTime!=null && !"".equals(dateTime)){
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date strTime=simpleDateFormat.parse(substring);
                Date endTime=simpleDateFormat.parse(substring1);
                dormRoomStudent.setFromDate(strTime);
                dormRoomStudent.setToDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Response<List<DormRoomStudent>> listResponse = dormRoomStudentClient.find(dormRoomStudent);
        List<DormRoomStudent> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }




}
