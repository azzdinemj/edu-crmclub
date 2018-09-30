package com.wuxue.view.controllers.student;


import com.wuxue.model.Payables;
import com.wuxue.model.Receivable;
import com.wuxue.model.StudentShift;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.finance.PayablesClient;
import com.wuxue.view.client.finance.ReceivableClient;
import com.wuxue.view.client.student.StudentShiftClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 新生面试
 */
@Controller
@RequestMapping(value = "/student/studentShift")
public class StudentShiftController extends BaseController
        implements IQueryController<StudentShift, String>, ISaveController<StudentShift, String>,IQueryByPagingController<StudentShift,Map<String,Object>>,
        ICreateController<StudentShift, String>, IEditController<StudentShift, String>, IDeleteController<StudentShift, String>{

    @Autowired
    private StudentShiftClient studentShiftClient;
    @Autowired
    private ReceivableClient receivableClient;
    @Autowired
    private PayablesClient payablesClient;

    /**
     * 面试学生列表
     *
     * @param model
     * @param studentShift
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentShift studentShift) {
//        Response<List<StudentShift>> listResponse = interviewClient.find(studentShift);
//
//        model.addAttribute("student", listResponse.getData());
        return "/financial/studentShiftList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentShift student, Integer sEcho, Integer start, Integer length) {
        student.setPageNo((start/length)+1);
        student.setPageSize(length);
//        String dateTime = student.getDateTime();

//        DateTimeUtils.StringSubToDate(dateTime);
//        if (dateTime != null && !"".equals(dateTime)){
//
//            student.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
//            student.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
//        }
        Response<List<StudentShift>> listResponse = studentShiftClient.find(student);
        List<StudentShift> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    @Override
    public String create(HttpServletRequest request, Model model, StudentShift studentShift) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, Model model, StudentShift studentShift) {
        return null;
    }

    @Override
    public String edit(HttpServletRequest request, Model model, StudentShift studentShift) {
        return null;
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, StudentShift studentShift) {
        studentShift.setPkStudentShift(GuidUtils.getGuid());
        return studentShiftClient.save(studentShift);
    }

    @RequestMapping(value = "/setFinancial",method = RequestMethod.GET)
    public String setFinancial(HttpServletRequest request, Model model, StudentShift studentShift) {
        model.addAttribute("pkStudentShift",studentShift.getPkStudentShift());
        return "/financial/studentShiftModel";
    }

    @RequestMapping(value = "/saveFinancial",method = RequestMethod.POST)
    @ResponseBody
    public String saveFinancial(HttpServletRequest request, Model model, StudentShift studentShift) {

        int oldMoney = Integer.valueOf(request.getParameter("oldMoney"));
        int money = Integer.valueOf(request.getParameter("money"));
        Response<StudentShift> byPrimaryKey = studentShiftClient.findByPrimaryKey(studentShift);
        StudentShift shift = byPrimaryKey.getData();

        String save = "";
        if(oldMoney < money){
//收
            Receivable receivable = new Receivable();
            receivable.setPkReceivable(GuidUtils.getGuid());
            receivable.setPkStudent(shift.getPkStudent());
            receivable.setPkDomain(SessionCache.getPkdomain());
            receivable.setDate(new Date());
            receivable.setCreator(SessionCache.getUserCode());
            receivable.setModifier(SessionCache.getUserCode());
            receivable.setCost(BigDecimal.valueOf(money-oldMoney));
            receivable.setCurrency("CNY");
            save = receivableClient.save(receivable);
        }else{
//    付
            Payables payables = new Payables();
            payables.setPkPayables(GuidUtils.getGuid());
            payables.setPkDomain(SessionCache.getPkdomain());
            payables.setPkStudent(shift.getPkStudent());
            payables.setCode(payables.getPkPayables());
            payables.setCreator(SessionCache.getUserCode());
            payables.setModifier(SessionCache.getUserCode());
            payables.setCost(BigDecimal.valueOf(oldMoney-money));
            payables.setDate(new Date());
            save = payablesClient.save(payables);
        }

        shift.setIsvalid(2);
        studentShiftClient.save(shift);
        return save;
    }
}
