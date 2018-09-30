package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.finance.ExpenseItemClient;
import com.wuxue.view.client.student.StudentClient;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 班级信息
 */

@Controller
@RequestMapping(value = "/classinfo/students")
public class ClassStudentController extends BaseController
        implements IQueryController<Student, String>, ISaveController<Student, String>,
        ICreateController<Student, String>, IEditController<Student, String>, IDeleteController<Student, String> {

    @Autowired
    private StudentClient studentClient;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private ExpenseItemClient expenseItemClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    @InitBinder("student")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stu.");
    }

    /**
     * 班级学生列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, Student student) {


        List<Student> studentList = studentUtils.getStudentList();


        model.addAttribute("list",studentList);




        return "/classinfo/classInfoList";
    }

    /**
     * 修改页面
     *
     * @param student
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Student student) {
        Response<Student> byPrimaryKey = studentClient.findByPrimaryKey(student);
        model.addAttribute("student", byPrimaryKey.getData());

        List<Student> studentList = studentUtils.getStudentList();
        model.addAttribute("students",studentList);



        return "/classinfo/classInfo";
    }


    /**
     * 保存
     *
     * @param student
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Student student) {
        String response = studentClient.save(student);
        return response;

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Student student) {

        List<Employee> employees = employeeUtils.getEmployees();
        model.addAttribute("employees",employees);

//        List<ExpenseItem> expenseItems = studentUtils.getExpenseItems();
        //费用项目
//        model.addAttribute("expenseItems",expenseItems);
        studentUtils.setExpenseItemModeAttribute(model,"expenseItems");

        List<ClassRoom> classRooms = studentUtils.getClassRooms();
        model.addAttribute("classRooms",classRooms);

        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(Light.SYS_DIC_GRADE);
        model.addAttribute("grade",sysDictValue);

        student.setCreator(SessionCache.getUserCode());
        student.setModifier(SessionCache.getUserCode());
        student.setCreationDate(new Date());
        student.setLasteditDate(new Date());
        student.setPkDomain(SessionCache.getPkdomain());

        String autocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.CLASSES);
        student.setCode(autocode);



        model.addAttribute("student",student);
//        List<Student> studentList = studentUtils.getStudentList();
        List<Student> studentList = new ArrayList<>();
        model.addAttribute("students",studentList);


        return "/classinfo/classInfo";
    }

    /**
     * 删除
     *
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Student student) {
        String response = studentClient.delete(student.getPkStudent());
        return response;
    }


    @RequestMapping(value = "/stuToClass",method = RequestMethod.GET)
    public String studentToClass(HttpServletRequest request,Model model,Student student){

        if(student.getPkStudent() == null || "".equals(student.getPkStudent())){
            model.addAttribute("classInfo",student);
        }else {

            Response<Student> byPrimaryKey = studentClient.findByPrimaryKey(student);
            model.addAttribute("classInfo",byPrimaryKey.getData());

        }
        List<Student> studentList = studentUtils.getStudentList();
        model.addAttribute("student",studentList);

        return "/classinfo/studentToClass";
    }

}
