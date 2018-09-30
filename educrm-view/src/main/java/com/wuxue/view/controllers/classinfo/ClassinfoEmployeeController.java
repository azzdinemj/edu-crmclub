package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassinfoEmployee;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoEmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
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
 * 班级信息
 */

@Controller
@RequestMapping(value = "/classinfo/classinfoEmployee")
public class ClassinfoEmployeeController extends BaseController
        implements IQueryController<ClassinfoEmployee, String>, ISaveController<ClassinfoEmployee, String>,IQueryByPagingController<ClassinfoEmployee,Map<String,Object>>,
        ICreateController<ClassinfoEmployee, String>, IEditController<ClassinfoEmployee, String>, IDeleteController<ClassinfoEmployee, String> {

    @Autowired
    private ClassInfoEmployeeClient classinfoEmployeeClient;

    /**
     * 班级学生列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, ClassinfoEmployee student) {

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
        return classinfoEmployeeClient.save(classinfoEmployee);

    }

    /**
     * 批量保存
     *
     * @param classinfoEmployee
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, ClassinfoEmployee classinfoEmployee) {
        classinfoEmployee.setCreator(SessionCache.getUserCode());
        classinfoEmployee.setModifier(SessionCache.getUserCode());
        return classinfoEmployeeClient.saveAll(classinfoEmployee);

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, ClassinfoEmployee student) {

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
    public String delete(HttpServletRequest request, Model model, ClassinfoEmployee student) {
        return null;
    }


    @RequestMapping(value = "/studentTeacherAdd",method = RequestMethod.GET)
    public String studentTeacherAdd(HttpServletRequest request,Model model,ClassinfoEmployee classinfoEmployee){

        model.addAttribute("pkClassinfo",classinfoEmployee.getPkClassinfo());

        return "/model/classinfoEmployeeModel2";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ClassinfoEmployee classinfoEmployee, Integer sEcho, Integer start, Integer length) {
        classinfoEmployee.setPageNo((start/length)+1);
        classinfoEmployee.setPageSize(length);
        Response<List<ClassinfoEmployee>> listResponse = classinfoEmployeeClient.find(classinfoEmployee);
        List<ClassinfoEmployee> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

}
