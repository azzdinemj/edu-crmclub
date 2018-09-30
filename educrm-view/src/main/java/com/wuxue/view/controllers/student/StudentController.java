package com.wuxue.view.controllers.student;

import com.github.pagehelper.PageInfo;
import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.model.LinkmanList;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentClient;
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
import java.util.*;

/**
 * 学生档案
 */
@Controller
@RequestMapping(value = "/student/student")
public class StudentController extends BaseController
        implements IQueryController<Student, String>, ISaveController<Student, String>,
        ICreateController<Student, String>, IEditController<Student, String>, IDeleteController<Student, String> {


    @InitBinder("student")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("stu.");
    }
    @InitBinder("man")
    public void initBinder1(WebDataBinder binder){
        binder.setFieldDefaultPrefix("man.");
    }
    @InitBinder("studentInterview")
    public void initBinder2(WebDataBinder binder){
        binder.setFieldDefaultPrefix("inte.");
    }

    @Autowired
    private StudentClient studentClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;
    @Autowired
    private EmployeeUtils employeeUtils;
    @Autowired
    private ClassinfoUtils classinfoUtils;

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

    /**
     * 学生档案列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */

    public String query(HttpServletRequest request, Model model, Student student) {

        model.addAttribute("isType",-1);

        return "/student/studentList";
    }


    /**
     * 修改学生
     *
     * @param model
     * @param student
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Student student) {

        //获取URL定位跳转路径
        model.addAttribute("URL",request.getServletPath());
        model.addAttribute("empkey",3);

        Response<Student> byPrimaryKey = studentClient.findByPrimaryKey(student);
        Student stu = byPrimaryKey.getData();
        model.addAttribute("student", stu);

        //初始化下拉列表
        initPageAttribute(model,stu.getPkDomain());

        //招生老师
        employeeUtils.setModeAttribute(model,"employee",stu.getPkSysUser());

        Map<String, Object> map = stu.getMap();

        //监护人资料
        List<Linkman> linkmanList = DataUtils.objectToList(map.get(Light.LINKMAN), Linkman.class);
        model.addAttribute("linkMans",linkmanList);

        List<KeyValue> classinfoByType = classinfoUtils.getClassinfoByType(0);
        model.addAttribute("classinfoList",classinfoByType);

        return "/student/student";
    }

    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Student student) {
        //年级
        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STUDENT_GRADE);
        model.addAttribute("grade",sysDictValue);
        //证件类型
        List<SysDictValues> sysDictValue1 = sysDictUtils.getSysDictValue(SysDicEmnuUtils.ID_KIND);
        model.addAttribute("idkind",sysDictValue1);
        //校区
        Domain domain = domainUtils.getDomain(SessionCache.getPkdomain());
        model.addAttribute("domain",domain);

        //编号
        String sysAutocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.ADD_STUDENT);
        student.setCode(sysAutocode);
        model.addAttribute("student",student);


        //学历
        List<SysDictValues> sysDictValue2 = sysDictUtils.getSysDictValue(SysDicEmnuUtils.ID_KIND);
        model.addAttribute("idkind",sysDictValue2);
        //学生分类
        List<SysDictValues> sysDictValue3 = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STUDENT_CLASS);
        model.addAttribute("studentclass",sysDictValue3);


        return "/student/student";
    }

    @RequestMapping(value="/refund",method = RequestMethod.GET)
    public String refund(HttpServletRequest request, Model model, Student student) {
        model.addAttribute("pkStudent",student.getPkStudent());
        return "/student/studentRefund";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Student student) {

        Enumeration<String> parameterNames = request.getParameterNames();



        if(student.getPkStudent() == null && "".equals(student.getPkStudent())){
            student.setCreator(SessionCache.getUserCode());
            student.setModifier(SessionCache.getUserCode());
        }else {
            student.setModifier(SessionCache.getUserCode());

        }

        return studentClient.save(student);

    }

    @RequestMapping(value="/saveall",method = RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Student student, LinkmanList man, StudentInterview studentInterview) {
        List<Linkman> linkmanList = man.getMan();
        List<Linkman> list = new ArrayList<>();
        if(linkmanList !=null && linkmanList.size()>0){
            for (Linkman linkman : linkmanList) {
                if (linkman.getCaption() == null || "".equals(linkman.getCaption())){
                    continue;
                }
                if(linkman.getPkLinkman()==null || "".equals(linkman.getPkLinkman())){
                    linkman.setPkLinkman(GuidUtils.getGuid());
                    linkman.setCreator(SessionCache.getUserCode());
                }
                linkman.setModifier(SessionCache.getUserCode());
                linkman.setPkDomain(student.getPkDomain());
                linkman.setPkStudent(student.getPkStudent());
                list.add(linkman);
            }
        }


        student.setBirthday(DateTimeUtils.stringToDate(student.getBirthdayTime()));
        student.setPassportDate(DateTimeUtils.stringToDate(student.getPassportDateTime()));

        student.put(Light.LINKMAN,list);
        if(student.getPkStudent() == null || "".equals(student.getPkStudent())){
            student.setPkStudent(GuidUtils.getGuid());
            student.setCreator(SessionCache.getUserCode());
            student.setCreationDate(new Date());
        }
        student.setModifier(SessionCache.getUserCode());
        return studentClient.save(student);

    }




    /**
     * 删除
     *
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Student student) {
        String response = studentClient.delete(student.getPkStudent());
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/findStudent",method = RequestMethod.GET)
    public Map<String, Object> findStudent(HttpServletRequest request,Student student,Model model){

        Response<List<Student>> response = studentClient.findByClassinfo(student);

        List<Student> data = response.getData();
        if (data == null){
            data = new ArrayList<>();
        }

        return convertToPaging(response.getTotal(),response.getTotal(),data);
    }

}
