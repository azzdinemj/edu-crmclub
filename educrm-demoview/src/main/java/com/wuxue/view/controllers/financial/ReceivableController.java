package com.wuxue.view.controllers.financial;

import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.finance.ExpenseItemClient;
import com.wuxue.view.client.finance.ReceivableClient;
import com.wuxue.view.client.student.ReceiptClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.student.StudentSignupClient;
import com.wuxue.view.client.student.StudentSignupDetailsClient;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 应收款
 */
@Controller
@RequestMapping(value = "/finance/receivable")
public class ReceivableController extends BaseController
        implements IQueryController<Receivable,String>,ISaveController<Receivable,String>,IQueryByPagingController<Receivable,Map<String,Object>>,
        ICreateController<Receivable,String>,IEditController<Receivable,String>,IDeleteController<Receivable,String>
        ,ISubmitController<Receivable,String>,IAuditController<Receivable,String> {

    @InitBinder("receivable")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("rec.");
    }
    @InitBinder("receipt")
    public void initBinder1(WebDataBinder binder){
        binder.setFieldDefaultPrefix("recp.");
    }

    /**
     * 应收款列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    
    public String query(HttpServletRequest request, Model model, Receivable receivable) {
//        Response<List<Receivable>> listResponse = receivableClient.find(receivable);
//
//        model.addAttribute("list",listResponse.getData() );
        return "/financial/receivableList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Receivable receivable, Integer sEcho, Integer start, Integer length) {
        receivable.setPageNo((start/length)+1);
        receivable.setPageSize(length);

        String dateTime = receivable.getDateTime();
        if(dateTime != null && !"".equals(dateTime)){
            receivable.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            receivable.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }

        Response<List<Receivable>> listResponse = receivableClient.find(receivable);
        List<Receivable> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    @Autowired
    private ReceivableClient receivableClient;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private StudentSignupClient studentSignupClient;
    @Autowired
    private ReceiptClient receiptClient;
    @Autowired
    private ExpenseItemClient expenseItemClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private StudentSignupDetailsClient studentSignupDetailsClient;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private EmployeeUtils employeeUtils;

    @Override
    public String create(HttpServletRequest request, Model model, Receivable receivable) {
        receivable.setCreationDate(new Date());
        receivable.setLasteditDate(new Date());
        receivable.setCode(sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.RECEIVABLE_TCODE));
        KeyValue keyValue = new KeyValue();
        keyValue.setCaption(SessionCache.getUserName());
        receivable.put("creatorEntity",keyValue);
        receivable.put("modifierEntity",keyValue);
        receivable.setPkReceivable(GuidUtils.getGuid());
        receivable.setPkDomain(SessionCache.getPkdomain());
        model.addAttribute("receivable",receivable);

        Response<List<ExpenseItem>> listResponse = expenseItemClient.find(new ExpenseItem());
        model.addAttribute("expenseItem",listResponse.getData());
        return "/financial/receivable";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Receivable receivable) {
        return receivableClient.delete(receivable.getPkReceivable());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, Receivable receivable) {
        Response<Receivable> byPrimaryKey = receivableClient.findByPrimaryKey(receivable);

        model.addAttribute("receivable",byPrimaryKey.getData());

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


        return "/financial/receivable";
    }

   


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Receivable receivable) {
        if(receivable.getDateTime() != null) {
            receivable.setDate(DateTimeUtils.stringToDate(receivable.getDateTime()));
        }
        receivable.setDate(new Date());
        return receivableClient.save(receivable);
    }


    @Override
    @ResponseBody
    public String submit(HttpServletRequest request, Model model, Receivable receivable) {
        receivable.setIssubmit(1);
        receivable.setModifier(SessionCache.getUserCode());
        receivable.setSubmitor(SessionCache.getUserCode());
        receivable.setSubmitDate(new Date());
        receivable.setDate(DateTimeUtils.stringToDate(receivable.getDateTime()));
        return receivableClient.submit(receivable);
    }

    @Override
    @ResponseBody
    public String audit(HttpServletRequest request, Model model, Receivable receivable) {
        receivable.setAuditor(SessionCache.getUserCode());
        receivable.setModifier(SessionCache.getUserCode());
        receivable.setAuditDate(new Date());
        receivable.setIsaudit(1);
        receivable.setDate(DateTimeUtils.stringToDate(receivable.getDateTime()));
        return receivableClient.audit(receivable);
    }

    /**
     * 收款
     * @param request
     * @param model
     * @param receivable
     * @return
     */
    @RequestMapping(value = "/setCollection",method = RequestMethod.GET)
    public String setCollection(HttpServletRequest request, Model model, Receivable receivable) {
//        Receivable data = byPrimaryKey.getData();
//        data.setCode(sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.RECEIP_TCODE));
//        data.setDate(new Date());

//        if (receivable.getPkReceivable() == null || "".equals(receivable.getPkReceivable())) {
//            String pkReceipt = request.getParameter("pkReceipt");
//            Receipt receipt = new Receipt();
//            receipt.setPkReceipt(pkReceipt);
//            Response<Receipt> receiptResponse = receiptClient.findByPrimaryKey(receipt);
//            receivable.setPkReceivable(receiptResponse.getData().getPkParent());
//        }
        Response<Receivable> byPrimaryKey = receivableClient.findByPrimaryKey(receivable);
        Receivable data = byPrimaryKey.getData();
        data.setCode(sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.RECEIP_TCODE));
        data.setDate(new Date());
        data.setIsaudit(0);
        data.setAuditor(null);
        data.setIssubmit(0);
        data.setSubmitor(null);
        model.addAttribute("receivable",data);

        if (byPrimaryKey.getData() != null) {
            Student student = new Student();
            student.setPkStudent(byPrimaryKey.getData().getPkStudent());
            Response<Student> studentResponse = Response.newResponse();
            if (student.getPkStudent() != null && !"".equals(student.getPkStudent())) {
                studentResponse = studentClient.findByPrimaryKey(student);
            }
            model.addAttribute("student", studentResponse.getData());

            employeeUtils.setModeAttribute(model,"employee",data.getPkSysUser());

            StudentSignup studentSignup = new StudentSignup();
            if(byPrimaryKey.getData().getPkParent() != null) {
                studentSignup.setPkStudentSignup(byPrimaryKey.getData().getPkParent());
                Response<StudentSignup> studentSignupResponse = studentSignupClient.findByPrimaryKey(studentSignup);
                model.addAttribute("studentSign", studentSignupResponse.getData());

                studentUtils.setDetailsModeAttribute(model, "receiptList", data.getPkParent());
            }

        }

        //支付方式
        List<SysDictValues> paymethod = sysDictUtils.getSysDictValue(SysDicEmnuUtils.PAY_METHOD);
        model.addAttribute("paymethod",paymethod);

        return "/financial/collection";
    }


    /**
     * 退费
     * @param request
     * @param model
     * @param receivable
     * @return
     */
    @RequestMapping(value = "/getReceiptList",method = RequestMethod.GET)
    public String getReceiptList(HttpServletRequest request, Model model, Receivable receivable) {

        model.addAttribute("pkReceivable",receivable.getPkReceivable());
        model.addAttribute("pkStudent",receivable.getPkStudent());
        return "/model/receiptListModel";
    }

}
