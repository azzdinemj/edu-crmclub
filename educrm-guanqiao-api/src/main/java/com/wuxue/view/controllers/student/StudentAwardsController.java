package com.wuxue.view.controllers.student;

import com.wuxue.model.Domain;
import com.wuxue.model.Student;
import com.wuxue.model.StudentAwards;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentAwardsClient;
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
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生奖励
 */
@Controller
@RequestMapping(value = "/student/studentAwards")
public class StudentAwardsController extends BaseController
        implements IQueryController<StudentAwards,String>,ISaveController<StudentAwards,String>,IQueryByPagingController<StudentAwards,Map<String,Object>>,
        ICreateController<StudentAwards,String>,IEditController<StudentAwards,String>,IDeleteController<StudentAwards,String> {


    @InitBinder("studentAwards")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("awa.");
    }

    @Autowired
    private StudentAwardsClient studentAwardsClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysDictUtils sysDictUtils;

    /**
     * 宿舍列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentAwards studentAwards)  {
        Response<List<StudentAwards>> listResponse = studentAwardsClient.find(studentAwards);

        model.addAttribute("list",listResponse.getData() );
        return "/student/studentRewardList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentAwards studentAwards, Integer sEcho, Integer start, Integer length) {
        studentAwards.setPageNo((start/length)+1);
        studentAwards.setPageSize(length);

        Response<List<StudentAwards>> listResponse = studentAwardsClient.find(studentAwards);
        List<StudentAwards> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }



    @Override
    public String create(HttpServletRequest request, Model model, StudentAwards studentAwards) {

        Student student = studentUtils.getStudent(studentAwards.getPkStudent());
        model.addAttribute("student",student);
        Domain domain = domainUtils.getDomain(student.getPkDomain());
        model.addAttribute("domain",domain);

        //年级
        List<SysDictValues> sysDictValue1 = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STUDENT_GRADE);
        model.addAttribute("grade",sysDictValue1);

        //奖励级别
        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STU_AWARDS);
        model.addAttribute("awardsgrade",sysDictValue);

        studentAwards.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        studentAwards.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        studentAwards.setCreationDate(new Date());
        studentAwards.setLasteditDate(new Date());
        model.addAttribute("awards",studentAwards);


        return "/student/addStudentAward";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentAwards studentAwards) {
        return studentAwardsClient.delete(studentAwards.getPkStudentAwards());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, StudentAwards studentAwards) {
        Response<StudentAwards> byPrimaryKey = studentAwardsClient.findByPrimaryKey(studentAwards);
        model.addAttribute("studentAwards",byPrimaryKey.getData());
        return "/student/lookStudentReward";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentAwards studentAwards) {
        if(studentAwards.getPkStudentAwards() ==null || "".equals(studentAwards.getPkStudentAwards())){
            studentAwards.setCreator(SessionCache.getUserCode());
            studentAwards.setPkStudentAwards(GuidUtils.getGuid());
        }
        studentAwards.setModifier(SessionCache.getUserCode());

        if (studentAwards.getDateTime() != null && !"".equals(studentAwards.getDateTime())){
            studentAwards.setDate(DateTimeUtils.stringToDate(studentAwards.getDateTime()));
        }
        return studentAwardsClient.save(studentAwards);
    }


}
