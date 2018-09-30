package com.wuxue.view.controllers.student;

import com.wuxue.model.Student;
import com.wuxue.model.StudentTestPlansScores;
import com.wuxue.model.StudentTestPlansScores;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.student.StudentTestPlansScoresClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.EmployeeUtils;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.StudentUtils;
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
 * 项目成绩
 */
@Controller
@RequestMapping(value = "/student/studentSubjectResul")
public class StudentSubjectResultController extends BaseController
        implements IQueryController<StudentTestPlansScores,String>,ISaveController<StudentTestPlansScores,String>,
        ICreateController<StudentTestPlansScores,String>,IEditController<StudentTestPlansScores,String>,IDeleteController<StudentTestPlansScores,String> {


    @InitBinder("studentTestPlansScores")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("pls.");
    }

    @Autowired
    private StudentTestPlansScoresClient studentTestPlansScoresClient;
    @Autowired
    private StudentClient studentClient;
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
        return "/student/subjectResultList";
    }



    @Override
    public String create(HttpServletRequest request, Model model, StudentTestPlansScores studentTestPlansScores) {

        String pkStudent = request.getParameter("pkStudent");
        Student student = studentUtils.getStudent(pkStudent);
        model.addAttribute("student",student);


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
        return "/student/lookSubjectResult";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentTestPlansScores studentTestPlansScores) {
        if (studentTestPlansScores.getPkStudentTestPlansScores() == null || "".equals(studentTestPlansScores.getPkStudentTestPlansScores())){
            studentTestPlansScores.setPkStudentTestPlansScores(GuidUtils.getGuid());
            studentTestPlansScores.setCreator(SessionCache.getUserCode());
        }
        studentTestPlansScores.setModifier(SessionCache.getUserCode());

        return studentTestPlansScoresClient.save(studentTestPlansScores);
    }


}
