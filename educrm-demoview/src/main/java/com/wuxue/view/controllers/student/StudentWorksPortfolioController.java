package com.wuxue.view.controllers.student;

import com.wuxue.model.*;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentWorksPortfolioClient;
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
 * 学生作品
 */
@Controller
@RequestMapping(value = "/student/studentWorksPortfolio")
public class StudentWorksPortfolioController extends BaseController
        implements IQueryController<StudentWorksPortfolio, String>, ISaveController<StudentWorksPortfolio, String>,
        ICreateController<StudentWorksPortfolio, String>, IEditController<StudentWorksPortfolio, String>, IDeleteController<StudentWorksPortfolio, String> {


    @InitBinder("studentWorksPortfolio")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("wpor.");
    }


    @Autowired
    private StudentWorksPortfolioClient studentWorksPortfolioClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysDictUtils sysDictUtils;


    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */

    public String query(HttpServletRequest request, Model model, StudentWorksPortfolio studentWorksPortfolio) {
        Response<List<StudentWorksPortfolio>> listResponse = studentWorksPortfolioClient.find(studentWorksPortfolio);

        model.addAttribute("list",listResponse.getData() );
        return "/student/studentWorksPortfolioArchivesList";
    }


    /**
     * 修改学生作品
     *
     * @param model
     * @param studentWorksPortfolio
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, StudentWorksPortfolio studentWorksPortfolio) {

        Response<StudentWorksPortfolio> byPrimaryKey = studentWorksPortfolioClient.findByPrimaryKey(studentWorksPortfolio);
        model.addAttribute("studentActivityExp",byPrimaryKey.getData());

        return "/student/studentWorksPortfolioArchives";
    }

    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, StudentWorksPortfolio studentWorksPortfolio) {

        Student student = studentUtils.getStudent(studentWorksPortfolio.getPkStudent());
        model.addAttribute("student",student);
        Domain domain = domainUtils.getDomain(student.getPkDomain());
        model.addAttribute("domain",domain);

        //作品类型
        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STU_PORTFOLIOTYPE);
        model.addAttribute("sysDictValue",sysDictValue);
        //年级
        List<SysDictValues> sysDictValue1 = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STUDENT_GRADE);
        model.addAttribute("grade",sysDictValue1);

        studentWorksPortfolio.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        studentWorksPortfolio.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        studentWorksPortfolio.setCreationDate(new Date());
        studentWorksPortfolio.setLasteditDate(new Date());
        model.addAttribute("portfolio",studentWorksPortfolio);


        return "/student/addWorksPortfolio";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param studentWorksPortfolio
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentWorksPortfolio studentWorksPortfolio) {

        if (studentWorksPortfolio.getPkStudentWorksPortfolio() == null || "".equals(studentWorksPortfolio.getPkStudentWorksPortfolio())){
            studentWorksPortfolio.setPkStudentWorksPortfolio(GuidUtils.getGuid());
            studentWorksPortfolio.setCreator(SessionCache.getUserCode());

        }
        studentWorksPortfolio.setModifier(SessionCache.getUserCode());

        return studentWorksPortfolioClient.save(studentWorksPortfolio);

    }


    /**
     * 删除
     *
     * @param studentWorksPortfolio
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentWorksPortfolio studentWorksPortfolio) {
        String response = studentWorksPortfolioClient.delete(studentWorksPortfolio.getPkStudentWorksPortfolio());
        return response;
    }

}
