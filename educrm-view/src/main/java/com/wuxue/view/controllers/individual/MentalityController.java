package com.wuxue.view.controllers.individual;

import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.LinkManClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 心里健康
 */
@Controller
@RequestMapping(value = "/individual/mentality")
public class MentalityController extends BaseController implements IQueryController<StudentInterviewRecord, String>, ISaveController<StudentInterviewRecord, String>,
        ICreateController<StudentInterviewRecord, String>, IEditController<Student, String>, IDeleteController<StudentInterviewRecord, String> {


    @InitBinder("studentInterviewRecord")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("intre.");
    }

    @Autowired
    private LinkManClient linkmanClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private EmployeeUtils employeeUtils;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord) {
        model.addAttribute("isType", 1);
        model.addAttribute("mentality", 1);
        return "/student/studentList";
    }

    @Override
    public String edit(HttpServletRequest request, Model model, Student student) {



        Response<Student> byPrimaryKey = studentClient.findByPrimaryKey(student);
        Student stu = byPrimaryKey.getData();
        model.addAttribute("student", stu);

        //初始化下拉列表
        initPageAttribute(model,stu.getPkDomain());

        //招生老师
        employeeUtils.setModeAttribute(model,"employee",stu.getPkSysUser());
        Map<String, Object> map = stu.getMap();
        //访谈记录
        List<StudentInterviewRecord> studentInterviewRecords = DataUtils.objectToList(map.get(Light.STUDENT_INTERVIEW_RECORD), StudentInterviewRecord.class);
        model.addAttribute("records",studentInterviewRecords);
        model.addAttribute("mentalitykey",1);

        return "/student/student";
    }


    @Override
    public String create(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord) {

        Student student = studentUtils.getStudent(studentInterviewRecord.getPkStudent());
        model.addAttribute("student",student);
        Domain domain = domainUtils.getDomain(student.getPkDomain());
        model.addAttribute("domain",domain);

        //年级
        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STUDENT_GRADE);
        model.addAttribute("grade",sysDictValue);

        studentInterviewRecord.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        studentInterviewRecord.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());

        studentInterviewRecord.setCreationDate(new Date());
        studentInterviewRecord.setLasteditDate(new Date());
        studentInterviewRecord.setIsType(1);
        model.addAttribute("interRec",studentInterviewRecord);
        return "/student/addStudentRecord";
    }

    @Override
    public String delete(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord) {
        return null;
    }

    @Override
    public String save(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord) {
        return null;
    }

    private void initPageAttribute(Model model, String pkDomain) {
        //证件类型
        sysDictUtils.setModeAttribute(model, "idkind", SysDicEmnuUtils.ID_KIND);
        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);
        //校区
        domainUtils.setModeAttribute(model, "domain", pkDomain);
        //学历
        sysDictUtils.setModeAttribute(model, "educations", SysDicEmnuUtils.EDUCATIONS);
        //学生分类
        sysDictUtils.setModeAttribute(model, "studentclass", SysDicEmnuUtils.STUDENT_CLASS);
        //身体健康
        sysDictUtils.setModeAttribute(model, "health", SysDicEmnuUtils.HEALTH);
        //心里健康
        sysDictUtils.setModeAttribute(model, "mentalhealth", SysDicEmnuUtils.MENTAL_HEALTH);
        //线索来源
        sysDictUtils.setModeAttribute(model, "channel", SysDicEmnuUtils.CHANNEL);
        //国籍
        sysDictUtils.setModeAttribute(model, "country", SysDicEmnuUtils.COUNTRY);
        //母语
        sysDictUtils.setModeAttribute(model, "motherlan", SysDicEmnuUtils.MOTHER_LAN);
        //民族
        sysDictUtils.setModeAttribute(model, "nation", SysDicEmnuUtils.NATION);
    }


}
