package com.wuxue.view.controllers.student;

import com.wuxue.model.Domain;
import com.wuxue.model.Student;
import com.wuxue.model.StudentInterviewRecord;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentInterviewRecordClient;
import com.wuxue.view.client.student.StudentInterviewRecordsClient;
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
 * 访谈记录
 */
@Controller
@RequestMapping(value = "/student/studentInterviewRecord")
public class StudentInterviewRecordController extends BaseController
        implements IQueryController<StudentInterviewRecord,String>,ISaveController<StudentInterviewRecord,String>,IQueryByPagingController<StudentInterviewRecord,Map<String,Object>>,
        ICreateController<StudentInterviewRecord,String>,IEditController<StudentInterviewRecord,String>,IDeleteController<StudentInterviewRecord,String> {


    @InitBinder("studentInterviewRecord")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("intre.");
    }

    @Autowired
    private StudentInterviewRecordClient studentInterviewRecordClient;

    @Autowired
    private StudentInterviewRecordsClient studentInterviewRecordsClients;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord)  {
        Response<List<StudentInterviewRecord>> listResponse = studentInterviewRecordClient.find(studentInterviewRecord);

        model.addAttribute("list",listResponse.getData() );
        return "/student/studentRecordList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentInterviewRecord studentInterviewRecord, Integer sEcho, Integer start, Integer length) {
        studentInterviewRecord.setPageNo((start/length)+1);
        studentInterviewRecord.setPageSize(length);

        Response<List<StudentInterviewRecord>> listResponse = studentInterviewRecordClient.find(studentInterviewRecord);
        List<StudentInterviewRecord> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }




    @Override
    public String create(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord) {

        String servletPath = request.getServletPath();
        int i = servletPath.lastIndexOf("/");
        String substring = servletPath.substring(0,i+1);
//        servletPath.s
        model.addAttribute("URLKEY",substring+"edit");
        String mentalitykey = request.getParameter("mentalitykey");
        if (mentalitykey != null && !"".equals(mentalitykey)){
            model.addAttribute("mentalitykey",mentalitykey);
        }


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
        studentInterviewRecord.setIsType(0);
        model.addAttribute("interRec",studentInterviewRecord);


        return "/student/addStudentRecord";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord) {
        return studentInterviewRecordClient.delete(studentInterviewRecord.getPkStudentInterviewRecord());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord) {
        Response<StudentInterviewRecord> byPrimaryKey = studentInterviewRecordClient.findByPrimaryKey(studentInterviewRecord);
        model.addAttribute("studentInterviewRecord",byPrimaryKey.getData());
        return "/student/lookStudentRecord";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentInterviewRecord studentInterviewRecord) {
        if(studentInterviewRecord.getPkStudentInterviewRecord() == null || "".equals(studentInterviewRecord.getPkStudentInterviewRecord())){
            studentInterviewRecord.setCreator(SessionCache.getUserCode());
            studentInterviewRecord.setPkStudentInterviewRecord(GuidUtils.getGuid());
        }else {
            studentInterviewRecord.setModifier(SessionCache.getUserCode());

        }
        if (studentInterviewRecord.getDateTime() != null && !"".equals(studentInterviewRecord.getDateTime())){
            studentInterviewRecord.setDate(DateTimeUtils.stringToDate(studentInterviewRecord.getDateTime()));
        }
        return studentInterviewRecordClient.save(studentInterviewRecord);
    }


}
