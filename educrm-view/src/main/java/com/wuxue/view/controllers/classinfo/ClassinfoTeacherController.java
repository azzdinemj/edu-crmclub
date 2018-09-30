package com.wuxue.view.controllers.classinfo;

import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.classInfo.ClassInfoEmployeeClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.ClassinfoUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级老师
 */

@Controller
@RequestMapping(value = "/classinfo/classinfoTeacher")
public class ClassinfoTeacherController extends BaseController
        implements IQueryController<ClassinfoEmployee, String>, ISaveController<ClassinfoEmployee, String>,IQueryByPagingController<ClassinfoEmployee,Map<String,Object>>,
        ICreateController<ClassinfoEmployee, String>, IEditController<ClassinfoEmployee, String>, IDeleteController<ClassinfoEmployee, String> {

    @Autowired
    private ClassInfoEmployeeClient classinfoEmployeeClient;
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private ClassinfoUtils classinfoUtils;

    /**
     * 班级老师列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, ClassinfoEmployee student) {



        return "/classinfo/classinfoTeacherList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ClassinfoEmployee courseTeacher, Integer sEcho, Integer start, Integer length) {
        courseTeacher.setPageNo((start/length)+1);
        courseTeacher.setPageSize(length);

        Response<List<ClassinfoEmployee>> listResponse = classinfoEmployeeClient.findTeacher(courseTeacher);
        List<ClassinfoEmployee> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     *
     * @param student
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, ClassinfoEmployee student) {

        return "/classinfo/classInfo";
    }


    /**
     * 保存
     *
     * @param classinfoEmployee
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ClassinfoEmployee classinfoEmployee) {
        classinfoEmployee.setCreator(SessionCache.getUserCode());
        classinfoEmployee.setModifier(SessionCache.getUserCode());
        classinfoEmployee.setIsvalid(1);
        return classinfoEmployeeClient.save(classinfoEmployee);

    }

    /**
     * 保存
     *
     * @param classinfoEmployee
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, ClassinfoEmployee classinfoEmployee) {
        classinfoEmployee.setIsvalid(1);
        return classinfoEmployeeClient.saveAll(classinfoEmployee);
    }



    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, ClassinfoEmployee student) {

//        Classinfo classinfo = new Classinfo();
//        classinfo.setPageSize(10000);
//        Response<List<Classinfo>> listResponse = classInfoClient.find(classinfo);
//        model.addAttribute("classinfo",listResponse.getData());
        List<KeyValue> classinfoByType = classinfoUtils.getClassinfoByType(null);
        model.addAttribute("classinfo",classinfoByType);

        return "/classinfo/classinfoTeacher";
    }

    /**
     * 删除
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ClassinfoEmployee classinfoEmployee) {
        classinfoEmployee.setModifier(SessionCache.getUserCode());
        return classinfoEmployeeClient.delete(classinfoEmployee);
    }




}
