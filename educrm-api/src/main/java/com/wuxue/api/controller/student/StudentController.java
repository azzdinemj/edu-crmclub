package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.*;
import com.wuxue.api.service.StudentService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.Linkman;
import com.wuxue.model.LinkmanList;
import com.wuxue.model.Student;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/student/student")
public class StudentController implements IFindController<Student>,
        ISaveController<Student>,IDeleteController<String>,IFindAllController<Student>,IAuditController<Student> {
    @Autowired
    private StudentService studentService;

    @Override
    public Response Find(@RequestBody Request<Student> student) {
        return studentService.find(student);
    }

    @Override
    public Response Save(@RequestBody Request<Student> student) {
        return studentService.save(student);
    }
    @Override

    public Response Delete(@RequestBody Request<String> student) {
        return studentService.delete(student);
    }

    /**
     * 查询学生列表 中航油
     * */
    @RequestMapping(value = "/findzhyou",method = RequestMethod.POST)
    public Response findzhyou(@RequestBody Request<Student> student) {
        return studentService.findzhyou(student);
    }

    /**
     * 查询学生详情（不包含任何关联数据）
     * */
    @RequestMapping(value = "/getstudent",method = RequestMethod.POST)
    public Response getstudent(@RequestBody Request<String> student) {
        return studentService.getStudent(student);
    }

    @RequestMapping(value = "/studentlogin",method = RequestMethod.POST)
    public Response Login(@RequestBody Request<Student> student) {
        return studentService.login(student);
    }

    /**
     * 中航激活用户
     * @param student
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Response register(@RequestBody Request<Student> student) {
        return studentService.register(student);
    }

    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public Response updatepassword(@RequestBody Request<Student> student) {
        return studentService.updatePassword(student);
    }

    @Override
    public Response FindAll(@RequestBody Request<Student> student) {
        return studentService.findAll(student);
    }

    @Override
    public Response Audit(@RequestBody Request<Student> student) {
        return studentService.audit(student);
    }

    @RequestMapping(value = "/findemployeestudent",method = RequestMethod.POST)
    public Response findEmployeeStudent(@RequestBody Request<Student> student) {
        return studentService.findEmployeeStudent(student);
    }

    @RequestMapping(value = "/findbyphone",method = RequestMethod.POST)
    public Response findbyPhone(@RequestBody Request<String> phone) {
        return studentService.findStudentByPhone(phone);
    }

    @RequestMapping(value = "/savezhyou",method = RequestMethod.POST)
    public Response savestudentzhy(@RequestBody Request<Student> student) {
        return studentService.saveStudentzhy(student);
    }

    @RequestMapping(value = "/findjiedian",method = RequestMethod.POST)
    public Response findjiedian(@RequestBody Request<Student> student) {
        return studentService.findjiedian(student);
    }


    @RequestMapping(value = "/findbyclassinfo",method = RequestMethod.POST)
    public Response findByClassinfo(@RequestBody Request<Student> student) {
        return studentService.findByClassinfo(student);
    }


    /**
     * 欠费学生
     */
    @RequestMapping(value = "/findarrearsstudent",method = RequestMethod.POST)
    public Response findArrearsStudent(@RequestBody Request<Student> student) {
        return studentService.arrearsStudent(student);
    }

    @InitBinder("student")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("stu.");
    }
    @InitBinder("man")
    public void initBinder1(WebDataBinder binder){
        binder.setFieldDefaultPrefix("man.");
    }

    /**
     * 信息采集
     * @param request
     * @param student
     * @param man
     * @return
     */
    @RequestMapping(value = "/saveStuForSmall",method = RequestMethod.POST)
    public Response saveStuFor(HttpServletRequest request,Student student,LinkmanList man){
         List<Linkman> linkmanList = man.getMan();
        List<Linkman> list = new ArrayList<>();
        if(linkmanList !=null && linkmanList.size()>0){
            for (Linkman linkman : linkmanList) {
                if (linkman.getCaption() == null || "".equals(linkman.getCaption())){
                    continue;
                }
                if(linkman.getPkLinkman()==null || "".equals(linkman.getPkLinkman())){
                    linkman.setPkLinkman(GuidUtils.getGuid());
                    linkman.setCreator("admin");
                }
                linkman.setModifier("admin");
                linkman.setPkDomain("1");
                linkman.setPkStudent(student.getPkStudent());
                list.add(linkman);
            }
        }
        student.setBirthday(DateTimeUtils.stringToDate(student.getBirthdayTime()));
        student.setPassportDate(DateTimeUtils.stringToDate(student.getPassportDateTime()));

        student.put(Light.LINKMAN,list);
        if(student.getPkStudent() == null || "".equals(student.getPkStudent())){
            student.setPkStudent(GuidUtils.getGuid());
            student.setCreator("admin");
            student.setCreationDate(new Date());
        }
        student.setModifier("admin");
        student.setPkDomain("1");

        Request<Student> studentRequest = DataUtils.objToRequest(student);


        return studentService.saveForCollect(studentRequest);
    }
    @RequestMapping(value = "/selectStudentListPage",method = RequestMethod.POST)
    public Response selectStudentListPage(@RequestBody ResultEntity entity){

        return studentService.selectStudentListByPkClassInfo(entity);
    }
    @RequestMapping(value = "/getstudentidcode",method = RequestMethod.POST)
    public Response getStudentIdCode(@RequestBody  Request<String> pkClassinfo){

        return studentService.getStudentIdCode(pkClassinfo);
    }
}
