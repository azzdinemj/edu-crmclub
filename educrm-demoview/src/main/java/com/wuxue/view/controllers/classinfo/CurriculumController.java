package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassTime;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassTimeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 课程表
 */
@Controller
@RequestMapping(value = "/classinfo/curriulum")
public class CurriculumController extends BaseController
        implements IQueryController<ClassTime,String>,ISaveController<ClassTime,String>,
        ICreateController<ClassTime,String>,IEditController<ClassTime,String>,IDeleteController<ClassTime,String> {

    @Autowired
    private ClassTimeClient classTimeClient;

    /**
     * 课程表
     * @param model
     * @return
     * @throws java.text.ParseException
     */

    public String query(HttpServletRequest request,Model model, ClassTime classTime) {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/student/curriculumList";
    }

    @Override
    public String create(HttpServletRequest request, Model model, ClassTime classTime) {
        return "/student/addCurriculum";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ClassTime classTime) {
        return classTimeClient.delete(classTime.getPkClassTime());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, ClassTime classTime) {
       /* Response<ClassTime> byPrimaryKey = classTimeClient.findByPrimaryKey(classTime);
        model.addAttribute("classTime",byPrimaryKey.getData());*/
        return "/student/editCurriculum";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ClassTime classTime) {

        return classTimeClient.save(classTime);
    }

}
