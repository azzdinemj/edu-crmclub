package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.Student;
import com.wuxue.model.StudentScores;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.StudentScoresClient;
import com.wuxue.view.client.finance.ExpenseItemClient;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生成绩信息
 */

@Controller
@RequestMapping(value = "/classinfo/studentScoress")
public class StudentScoresController extends BaseController
        implements IQueryController<Student, String>, ISaveController<StudentScores, String>,IQueryByPagingController<StudentScores,Map<String,Object>>,
        ICreateController<StudentScores, String>, IEditController<StudentScores, String>, IDeleteController<StudentScores, String> {

    @Autowired
    private StudentScoresClient studentScoresClient;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private ExpenseItemClient expenseItemClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    @InitBinder("studentScores")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("sc.");
    }
    @InitBinder("scores")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("scores.");
    }

    /**
     *
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, Student student) {

        Response<List<StudentScores>> schoolReport = studentScoresClient.findSchoolReport(student);

        return "/classinfo/classInfoList";
    }

    /**
     * 修改页面
     *
     * @param studentScores
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, StudentScores studentScores) {

        Response<StudentScores> byPrimaryKey = studentScoresClient.findByPrimaryKey(studentScores);
        model.addAttribute("studentScore",byPrimaryKey.getData());

        sysDictUtils.setModeAttribute(model,"terms", SysDicEmnuUtils.TERM);
        sysDictUtils.setModeAttribute(model,"testPlansType", SysDicEmnuUtils.STU_TESTPLANS);

        return "/classinfo/studentScores";
    }


    /**
     * 保存
     *
     * @param studentScores
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentScores studentScores) {
        if(studentScores.getPkStudentTestPlansScores() == null || "".equals(studentScores.getPkStudentTestPlansScores())){
            studentScores.setPkStudentTestPlansScores(GuidUtils.getGuid());
            studentScores.setCreator(SessionCache.getUserCode());
        }
        studentScores.setModifier(SessionCache.getUserCode());
        String employee = SessionCache.getEmployee();
        studentScores.setPkEmployee(employee);
        studentScores.setYear(DateTimeUtils.stringToDateYYYY(studentScores.getYearTime()));
        String response = studentScoresClient.save(studentScores);
        return response;

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, StudentScores studentScores) {

        return "/classinfo/classInfo";
    }

    /**
     * 删除
     *
     * @param studentScores
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentScores studentScores) {
//        String response = studentScoresClient.delete(studentScores.getPkStudentScores());
        return null;
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentScores studentScores, Integer sEcho, Integer start, Integer length) {
        studentScores.setPageNo((start/length)+1);
        studentScores.setPageSize(length);
        Response<List<StudentScores>> listResponse = studentScoresClient.find(studentScores);
        List<StudentScores> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @ResponseBody
    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, LinkmanList scores ) {
        List<StudentScores> list = scores.getScores();
        List<StudentScores> scoresList = new ArrayList<>();
        if (list != null && list.size()>0){
            for (StudentScores studentScores : list) {
                if(studentScores.getScores()!= null && !"".equals(studentScores.getScores())){
                    if(studentScores.getPkStudentTestPlansScores() == null || "".equals(studentScores.getPkStudentTestPlansScores())){
                        studentScores.setPkStudentTestPlansScores(GuidUtils.getGuid());
                        studentScores.setCode(sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.SCORES_TCODE));
                        studentScores.setPkDomain(SessionCache.getPkdomain());
                        studentScores.setCreator(SessionCache.getUserCode());
                    }

                    String employee = SessionCache.getEmployee();
                    studentScores.setPkEmployee(employee);
                    studentScores.setModifier(SessionCache.getUserCode());
                    studentScores.setYear(DateTimeUtils.stringToDateYYYY(studentScores.getYearTime()));
                    scoresList.add(studentScores);
                }

            }
        }
        return studentScoresClient.saveAll(scoresList);

    }


}
