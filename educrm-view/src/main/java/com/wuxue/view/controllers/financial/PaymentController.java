package com.wuxue.view.controllers.financial;

import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.finance.ExpenseItemClient;
import com.wuxue.view.client.finance.PaymentClient;
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
 * 应付款
 */
@Controller
@RequestMapping(value = "/finance/payment")
public class PaymentController extends BaseController
        implements IQueryController<Payment,String>,ISaveController<Payment,String>,IQueryByPagingController<Payment,Map<String,Object>>,
        ICreateController<Payment,String>,IEditController<Payment,String>,IDeleteController<Payment,String>
        ,ISubmitController<Payment,String>,IAuditController<Payment,String> {

    /**
     * 应付款列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    
    public String query(HttpServletRequest request, Model model, Payment payment) {
//        Response<List<Payment>> listResponse = paymentClient.find(payment);
//
//        model.addAttribute("list",listResponse.getData() );
        return "/financial/paymentList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Payment payment, Integer sEcho, Integer start, Integer length) {
        payment.setPageNo((start/length)+1);
        payment.setPageSize(length);

        String dateTime = payment.getDateTime();
        if(dateTime != null && !"".equals(dateTime)){
            payment.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            payment.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }

        Response<List<Payment>> listResponse = paymentClient.find(payment);
        List<Payment> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    @Autowired
    private PaymentClient paymentClient;

    @Override
    public String create(HttpServletRequest request, Model model, Payment payment) {
        return "/financial/addReceived";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Payment payment) {
        return paymentClient.delete(payment.getPkPayment());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, Payment payment) {
        Response<Payment> byPrimaryKey = paymentClient.findByPrimaryKey(payment);

        return "/financial/payment";
    }

   


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Payment payment) {
        if(payment.getPkPayment() == null || "".equals(payment.getPkPayment())){
            payment.setCreator(SessionCache.getUserCode());
            payment.setModifier(SessionCache.getUserCode());
        }else {
            payment.setModifier(SessionCache.getUserCode());
        }
        payment.setPkPayment(GuidUtils.getGuid());
        payment.setCode(payment.getPkPayment());
        payment.setPkDomain(SessionCache.getPkdomain());
        payment.setPkSysUser(SessionCache.getUserCode());
        return paymentClient.save(payment);
    }


    @Override
    @ResponseBody
    public String submit(HttpServletRequest request, Model model, Payment payment) {
        payment.setIssubmit(1);
        payment.setModifier(SessionCache.getUserCode());
        payment.setSubmitor(SessionCache.getUserCode());
        payment.setSubmitDate(new Date());
        return paymentClient.submit(payment);
    }

    @Override
    @ResponseBody
    public String audit(HttpServletRequest request, Model model, Payment payment) {
        payment.setAuditor(SessionCache.getUserCode());
        payment.setModifier(SessionCache.getUserCode());
        payment.setAuditDate(new Date());
        payment.setIsaudit(1);
        return paymentClient.audit(payment);
    }

    @ResponseBody
    @RequestMapping(value = "/retreat",method = RequestMethod.POST)
    public String retreat(HttpServletRequest request, Model model, Payment payment) {
        return paymentClient.retreat(payment);
    }

}
