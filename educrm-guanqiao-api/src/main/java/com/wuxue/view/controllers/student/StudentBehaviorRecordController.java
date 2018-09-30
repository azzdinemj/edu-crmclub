package com.wuxue.view.controllers.student;

import com.wuxue.model.Domain;
import com.wuxue.model.Student;
import com.wuxue.model.StudentBehaviorRecord;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentBehaviorRecordClient;
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
 * 学生违纪
 */

@Controller
@RequestMapping(value = "/student/studentBehaviorRecord")
public class StudentBehaviorRecordController extends BaseController
        implements IQueryController<StudentBehaviorRecord,String>,ISaveController<StudentBehaviorRecord,String>,IQueryByPagingController<StudentBehaviorRecord,Map<String,Object>>,
        ICreateController<StudentBehaviorRecord,String>,IEditController<StudentBehaviorRecord,String>,IDeleteController<StudentBehaviorRecord,String> {

    @InitBinder("studentBehaviorRecord")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("bere.");
    }

    @Autowired
    private StudentBehaviorRecordClient studentBehaviorRecordClient;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysDictUtils sysDictUtils;

    @Override
    public String create(HttpServletRequest request, Model model, StudentBehaviorRecord studentBehaviorRecord) {

        Student student = studentUtils.getStudent(studentBehaviorRecord.getPkStudent());
        model.addAttribute("student",student);
        Domain domain = domainUtils.getDomain(student.getPkDomain());
        model.addAttribute("domain",domain);

        studentBehaviorRecord.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        studentBehaviorRecord.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        studentBehaviorRecord.setCreationDate(new Date());
        studentBehaviorRecord.setLasteditDate(new Date());
        model.addAttribute("behare",studentBehaviorRecord);

        //年级
        sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);


        return "/student/addStuBehaviorRecord";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentBehaviorRecord studentBehaviorRecord) {
        return studentBehaviorRecordClient.delete(studentBehaviorRecord.getPkStudentBehaviorRecord());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, StudentBehaviorRecord studentBehaviorRecord) {
        Response<StudentBehaviorRecord> byPrimaryKey = studentBehaviorRecordClient.findByPrimaryKey(studentBehaviorRecord);
        model.addAttribute("studentBehaviorRecord",byPrimaryKey.getData());
        return "/student/editStuBehaviorRecord";
    }

    @Override
    public String query(HttpServletRequest request, Model model, StudentBehaviorRecord studentBehaviorRecord) {
//       Response<List<StudentBehaviorRecord>> listResponse = studentBehaviorRecordClient.find(studentBehaviorRecord);
//        model.addAttribute("list",listResponse.getData() );
        return "/student/StudentBehaviorRecordList";

    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentBehaviorRecord studentBehaviorRecord, Integer sEcho, Integer start, Integer length) {
        studentBehaviorRecord.setPageNo((start/length)+1);
        studentBehaviorRecord.setPageSize(length);

        Response<List<StudentBehaviorRecord>> listResponse = studentBehaviorRecordClient.find(studentBehaviorRecord);
        List<StudentBehaviorRecord> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentBehaviorRecord studentBehaviorRecord) {
        if (studentBehaviorRecord.getPkStudentBehaviorRecord() == null || "".equals(studentBehaviorRecord.getPkStudentBehaviorRecord())){
            studentBehaviorRecord.setPkStudentBehaviorRecord(GuidUtils.getGuid());
            studentBehaviorRecord.setCreator(SessionCache.getUserCode());
        }
        studentBehaviorRecord.setModifier(SessionCache.getUserCode());
        if (studentBehaviorRecord.getDateTime() != null && !"".equals(studentBehaviorRecord.getDateTime())){
            studentBehaviorRecord.setDate(DateTimeUtils.stringToDate(studentBehaviorRecord.getDateTime()));
        }
        return studentBehaviorRecordClient.save(studentBehaviorRecord);
    }


}
