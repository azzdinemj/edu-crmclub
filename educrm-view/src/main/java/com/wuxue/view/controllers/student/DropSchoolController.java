package com.wuxue.view.controllers.student;

import com.wuxue.model.DropExpenseRecord;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.DropExpenseRecordClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.StudentUtils;
import com.wuxue.view.utils.SysDicEmnuUtils;
import com.wuxue.view.utils.SysDictUtils;
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
import java.util.List;
import java.util.Map;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/student/dropSchool")
public class DropSchoolController extends BaseController
        implements IQueryController<DropExpenseRecord, String>, ISaveController<DropExpenseRecord, String>, IQueryByPagingController<DropExpenseRecord, Map<String, Object>>,
        ICreateController<DropExpenseRecord, String>, IEditController<DropExpenseRecord, String>, IDeleteController<DropExpenseRecord, String> {


    @InitBinder("dropExpenseRecord")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("dro.");
    }

    @Autowired
    private DropExpenseRecordClient dropExpenseRecordClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private StudentClient studentClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, DropExpenseRecord dropExpenseRecord) {


        return "/student/dropSchoolList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, DropExpenseRecord dropExpenseRecord, Integer sEcho, Integer start, Integer length) {

        dropExpenseRecord.setPageNo((start / length) + 1);
        dropExpenseRecord.setPageSize(length);
        Response<List<DropExpenseRecord>> listResponse = dropExpenseRecordClient.find(dropExpenseRecord);
        List<DropExpenseRecord> data = listResponse.getData();

        if (data == null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);
    }


    @Override
    public String create(HttpServletRequest request, Model model, DropExpenseRecord dropExpenseRecord) {

        sysDictUtils.setModeAttribute(model, "schoolYearList", SysDicEmnuUtils.SCHOOLYEAR);
        Student student = studentUtils.getStudent(dropExpenseRecord.getPkStudent());
        model.addAttribute("student", student);

        return "/student/addDropExpenseRecord";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, DropExpenseRecord dropExpenseRecord) {
        return dropExpenseRecordClient.delete(dropExpenseRecord.getPkDropExpenseRecord());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, DropExpenseRecord dropExpenseRecord) {
        Response<DropExpenseRecord> byPrimaryKey = dropExpenseRecordClient.findByPrimaryKey(dropExpenseRecord);
        model.addAttribute("dropExpenseRecord", byPrimaryKey.getData());
        return "/student/editDropExpenseRecord";
    }


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, DropExpenseRecord dropExpenseRecord) {

        String type = request.getParameter("type");
        if ("1".equals(type)) {
            Student student = new Student();
            student.setPkStudent(dropExpenseRecord.getPkStudent());
            student.setIstype(3);
            return studentClient.save(student);
        } else {

            String userName = SessionCache.getUserName();
            if (dropExpenseRecord.getPkDropExpenseRecord() != null) {
                dropExpenseRecord.setModifier(userName);
            } else {
                dropExpenseRecord.setCreator(userName);
                dropExpenseRecord.setModifier(userName);
            }

            return dropExpenseRecordClient.save(dropExpenseRecord);
        }
    }

}
