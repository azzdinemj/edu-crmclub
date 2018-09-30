package com.wuxue.view.controllers.financial;

import com.wuxue.model.Payables;
import com.wuxue.model.Student;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.finance.PayablesClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.DateTimeUtils;
import com.wuxue.view.utils.EmployeeUtils;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学生退费
 */
@Controller
@RequestMapping(value = "/finance/payables")
public class PayablesController extends BaseController implements IQueryController<Payables,String>,ISaveController<Payables,String>,
        IQueryByPagingController<Payables,Map<String,Object>>,IEditController<Payables,String>,ISubmitController<Payables,String>,IAuditController<Payables,String> {


    @Autowired
    PayablesClient payablesClient;
    @Autowired
    StudentClient studentClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private EmployeeUtils employeeUtils;

    /**
     * 退费列表
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/geStutrefundList", method = RequestMethod.GET)
    public String queryUser(Model model, Payables payables) throws Exception {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/financial/studentrefund";
    }

    @RequestMapping(value = "/returnpay", method = RequestMethod.GET)
    public String returnpay(HttpServletRequest request, Model model, Payables payables) throws Exception {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/financial/payables";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Payables payables, Integer sEcho, Integer start, Integer length) {
        payables.setPageNo((start/length)+1);
        payables.setPageSize(length);

        String dateTime = payables.getDateTime();
        if(dateTime != null && !"".equals(dateTime)){
            payables.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            payables.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }

        Response<List<Payables>> listResponse = payablesClient.find(payables);
        List<Payables> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @Override
    public String query(HttpServletRequest request, Model model, Payables payables)  {
        return "/financial/payablesList";
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Payables payables) {
        return payablesClient.save(payables);
    }

    @ResponseBody
    @RequestMapping(value = "/receiptSavePayables", method = RequestMethod.POST)
    public String receiptSavePayables(HttpServletRequest request, Model model, Payables payables){
        payables.setPkPayables(GuidUtils.getGuid());
        payables.setCode(payables.getPkPayables());
        payables.setPkDomain(SessionCache.getPkdomain());

        return payablesClient.receiptSavePayables(payables);
    }

    @Override
    public String edit(HttpServletRequest request, Model model, Payables payables) {
        Response<Payables> byPrimaryKey = payablesClient.findByPrimaryKey(payables);

        model.addAttribute("payables",byPrimaryKey.getData());

        if (byPrimaryKey.getData() != null) {
            Student student = new Student();
            student.setPkStudent(byPrimaryKey.getData().getPkStudent());
            Response<Student> studentResponse = Response.newResponse();
            if (student.getPkStudent() != null && !"".equals(student.getPkStudent())) {
                studentResponse = studentClient.findByPrimaryKey(student);
            }
            model.addAttribute("student",studentResponse.getData());
            employeeUtils.setModeAttribute(model,"employee",byPrimaryKey.getData().getPkSysUser());

            SysUser sysUser = new SysUser();
            Response<List<SysUser>> listResponse = sysUserClient.find(sysUser);
            model.addAttribute("userList",listResponse.getData());

            sysUser.setPkSysUser(byPrimaryKey.getData().getPkSysUser());
            Response<SysUser> userResponse = Response.newResponse();
            if (sysUser.getPkSysUser() != null && !"".equals(sysUser.getPkSysUser())) {
                userResponse = sysUserClient.findByPrimaryKey(sysUser);
            }
            model.addAttribute("user",userResponse.getData());
        }


        return "/financial/payables";
    }

    @RequestMapping(value = "/stuRefund",method = RequestMethod.GET)
    public String studentRefund(HttpServletRequest request, Model model, Payables payables) {
        Response<Payables> byPrimaryKey = payablesClient.findByPrimaryKey(payables);
        model.addAttribute("payables",byPrimaryKey.getData());
        return "/financial/stuRefund";
    }
    @Override
    @ResponseBody
    public String audit(HttpServletRequest request, Model model, Payables payables) {
        return payablesClient.audit(payables);
    }

    @Override
    @ResponseBody
    public String submit(HttpServletRequest request, Model model, Payables payables) {
        return payablesClient.submit(payables);
    }

    @ResponseBody
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public String refund(HttpServletRequest request, Model model, Payables payables){
        return payablesClient.refund(payables);
    }
}
