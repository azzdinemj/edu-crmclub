package com.wuxue.view.controllers.student;

import com.wuxue.model.Domain;
import com.wuxue.model.Student;
import com.wuxue.model.StudentPlans;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentPlansClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 学生计划
 */
@Controller
@RequestMapping(value = "/student/studentPlans")
public class StudentplansController extends BaseController implements IQueryController<StudentPlans,String>,ISaveController<StudentPlans, String>,
        ICreateController<StudentPlans, String>,IEditController<StudentPlans, String>,IDeleteController<StudentPlans, String> {

    @InitBinder("studentPlans")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("plan.");
    }

    @Autowired
    private StudentPlansClient studentPlansClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;

    /**
     * 列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Override

    public String query(HttpServletRequest request, Model model, StudentPlans studentPlans)  {
        Response<List<StudentPlans>> listResponse = studentPlansClient.find(studentPlans);

        model.addAttribute("list", listResponse.getData());
        return "/student/studentPlansList";
    }

    /**
     * 修改页面
     *
     * @param studentPlans
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model,StudentPlans studentPlans) {

        Response<StudentPlans> byPrimaryKey = studentPlansClient.findByPrimaryKey(studentPlans);
        model.addAttribute("studentPlanss", byPrimaryKey.getData());

        return "/student/editStudentPlans";
    }

    /**
     * 保存
     *
     * @param studentPlans
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,StudentPlans studentPlans) {

        if (studentPlans.getPkStudentPlans()==null || "".equals(studentPlans.getPkStudentPlans())){
            studentPlans.setCreator(SessionCache.getUserCode());
            studentPlans.setPkStudentPlans(GuidUtils.getGuid());
        }
        studentPlans.setModifier(SessionCache.getUserCode());

        return studentPlansClient.save(studentPlans);
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
   @Override
    public String create(HttpServletRequest request, Model model,StudentPlans studentPlans) {

       Student student = studentUtils.getStudent(studentPlans.getPkStudent());
       model.addAttribute("student",student);
       Domain domain = domainUtils.getDomain(student.getPkDomain());
       model.addAttribute("domain",domain);
       studentPlans.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
       studentPlans.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
       studentPlans.setCreationDate(new Date());
       studentPlans.setLasteditDate(new Date());
       model.addAttribute("plans",studentPlans);

        return "/student/addStudentPlans";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model,StudentPlans studentPlans) {
        String response = studentPlansClient.delete(studentPlans.getPkStudentPlans());
        return response;
    }

}
