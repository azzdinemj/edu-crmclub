package com.wuxue.view.controllers.student;

import com.wuxue.model.*;
import com.wuxue.model.StudentTestPlansScores;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentTestPlansScoresClient;
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
import java.util.Date;
import java.util.List;

/**
 * 学生成绩
 */
@Controller
@RequestMapping(value = "/student/studentTestPlansScores")
public class StudentTestPlansScoresController extends BaseController
        implements IQueryController<StudentTestPlansScores,String>,ISaveController<StudentTestPlansScores,String>,
        ICreateController<StudentTestPlansScores,String>,IEditController<StudentTestPlansScores,String>,IDeleteController<StudentTestPlansScores,String> {


    @InitBinder("studentTestPlansScores")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("stu.");
    }
    @InitBinder("studentCredit")
    public void initBinder1(WebDataBinder binder){
        binder.setFieldDefaultPrefix("cre.");
    }

    @Autowired
    private StudentTestPlansScoresClient studentTestPlansScoresClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private StudentUtils studentUtils;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentTestPlansScores studentTestPlansScores)  {
        Response<List<StudentTestPlansScores>> listResponse = studentTestPlansScoresClient.find(studentTestPlansScores);

        model.addAttribute("list",listResponse.getData() );
        return "/student/studentResultTable";
    }



    @Override
    public String create(HttpServletRequest request, Model model, StudentTestPlansScores studentTestPlansScores) {

        String pkStudent = request.getParameter("pkStudent");
        Student student = studentUtils.getStudent(pkStudent);
        model.addAttribute("student",student);

        //考试计划
        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STU_TESTPLANS);
        model.addAttribute("sysDictValue",sysDictValue);

        //科目
        List<SysDictValues> sysDictValue1 = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STU_DISCIPLINE);
        model.addAttribute("discipline",sysDictValue1);

        studentTestPlansScores.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        studentTestPlansScores.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        studentTestPlansScores.setCreationDate(new Date());
        studentTestPlansScores.setLasteditDate(new Date());
        model.addAttribute("plSco",studentTestPlansScores);
        return "/student/addSubjectResult";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentTestPlansScores studentTestPlansScores) {
        return studentTestPlansScoresClient.delete(studentTestPlansScores.getPkStudentTestPlansScores());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, StudentTestPlansScores studentTestPlansScores) {
        Response<StudentTestPlansScores> byPrimaryKey = studentTestPlansScoresClient.findByPrimaryKey(studentTestPlansScores);
        model.addAttribute("studentTestPlansScores",byPrimaryKey.getData());
        return "/student/lookStudentResultTable";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentTestPlansScores studentTestPlansScores) {
        return studentTestPlansScoresClient.save(studentTestPlansScores);
    }
    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public String saveall(HttpServletRequest request, Model model, StudentTestPlansScores studentTestPlansScores, StudentCredit studentCredit) {
        if (studentTestPlansScores.getPkStudentTestPlansScores()== null || "".equals(studentTestPlansScores.getPkStudentTestPlansScores())){
            studentTestPlansScores.setPkStudentTestPlansScores(GuidUtils.getGuid());
            studentTestPlansScores.setCreator(SessionCache.getUserCode());
        }
        studentTestPlansScores.setModifier(SessionCache.getUserCode());
        return studentTestPlansScoresClient.save(studentTestPlansScores);
    }


}
