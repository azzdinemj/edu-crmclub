package com.wuxue.view.controllers.financial;

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
import com.wuxue.view.enums.SysDictEnum;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生收费
 */

@Controller
@RequestMapping(value = "/finance/studentCharge")
public class StudentChargeController extends BaseController implements
        IQueryController<Receipt, String>, IEditController<Receipt, String>,IQueryByPagingController<Receipt,Map<String,Object>>
        , ISaveController<Receipt, String>,ISubmitController<Receipt,String>,IAuditController<Receipt,String> {

    @Autowired
    private ReceivableClient receivableClient;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private StudentSignupClient studentSignupClient;
    @Autowired
    private ReceiptClient receiptClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private StudentUtils studentUtils;
    @Autowired
    private StudentSignupDetailsClient studentSignupDetailsClient;
    @Autowired
    private EmployeeUtils employeeUtils;

    @InitBinder("receipt")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("recp.");
    }

    @Override
    public String query(HttpServletRequest request, Model model, Receipt receipt) {

//        Response<List<Receipt>> listResponse = receiptClient.find(receipt);
//        model.addAttribute("receipt", listResponse.getData());

        model.addAttribute("type",1);
        return "/financial/studentChargeList";
    }
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Receipt receipt, Integer sEcho, Integer start, Integer length) {
        receipt.setPageNo((start/length)+1);
        receipt.setPageSize(length);

        String dateTime = receipt.getDateTime();
        if(dateTime != null && !"".equals(dateTime)){
            receipt.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
            receipt.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
        }

        Response<List<Receipt>> listResponse = receiptClient.find(receipt);
        List<Receipt> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }
    @ResponseBody
    @RequestMapping(value = "/queryByPagingForDrop",method = RequestMethod.GET)
    public Map<String, Object> queryByPagingForDrop(HttpServletRequest request, HttpServletResponse response, Receipt receipt, Integer sEcho, Integer start, Integer length) {
        receipt.setPageNo((start/length)+1);
        receipt.setPageSize(length);

        Response<List<Map<String,Object>>> listResponse = receiptClient.findFroRefund(receipt);
        List<Map<String,Object>> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @Override
    public String edit(HttpServletRequest request, Model model, Receipt receipt) {

        Response<Receipt> receiptResponse1 = receiptClient.findByPrimaryKey(receipt);
        model.addAttribute("receivable", receiptResponse1.getData());

        if (receiptResponse1.getData() != null) {
            Student student = new Student();
            student.setPkStudent(receiptResponse1.getData().getPkStudent());
            Response<Student> studentResponse = Response.newResponse();
            if (student.getPkStudent() != null && !"".equals(student.getPkStudent())) {
                studentResponse = studentClient.findByPrimaryKey(student);
            }
            model.addAttribute("student", studentResponse.getData());

            employeeUtils.setModeAttribute(model, "employee", receiptResponse1.getData().getPkSysUser());

            //费用明细
            Receivable receivable = new Receivable();
            receivable.setPkReceivable(receiptResponse1.getData().getPkParent());
            Response<Receivable> receivableResponse = receivableClient.findByPrimaryKey(receivable);

            studentUtils.setDetailsModeAttribute(model, "receiptList", receivableResponse.getData().getPkParent());


        }

        //支付方式
        List<SysDictValues> paymethod = sysDictUtils.getSysDictValue(SysDicEmnuUtils.PAY_METHOD);
        model.addAttribute("paymethod", paymethod);

        //年级
        List<SysDictValues> grade = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STUDENT_GRADE);
        model.addAttribute("grade", grade);

        //项目
        List<SysDictValues> program = sysDictUtils.getSysDictValue(SysDicEmnuUtils.STUDENT_PROJECT);
        model.addAttribute("program", program);

        //学年
        sysDictUtils.setModeAttribute(model, "schoolYear", SysDicEmnuUtils.SCHOOL_YEAR);
        return "/financial/collection";
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Receipt receipt) {
        if (receipt.getPkReceipt() == null || "".equals(receipt.getPkReceipt())) {
//            receipt.setPkReceipt(GuidUtils.getGuid());
            receipt.setCreator(SessionCache.getUserCode());
//            if (receipt.getMoney() != null) {
//                Receivable receivable = new Receivable();
//                receivable.setPkReceivable(receipt.getPkParent());
//                Response<Receivable> byPrimaryKey = receivableClient.findByPrimaryKey(receivable);
//                Receivable receivables = byPrimaryKey.getData();
//                if(receivables.getMoney() == null){
//                    receivables.setMoney(BigDecimal.valueOf(0));
//                    receivables.setMoney(receivables.getMoney().add(receipt.getMoney()));
//                }else{
//                    receivables.setMoney(receivables.getMoney().add(receipt.getMoney()));
//                }
//                receivableClient.save(receivables);
//            }
        }


        receipt.setModifier(SessionCache.getUserCode());
        receipt.setDate(DateTimeUtils.stringToDate(receipt.getDateTime()));
        String save = receiptClient.save(receipt);
        return save;

    }

    /**
     * 提交
     * @param request
     * @param model
     * @param receipt
     * @return
     */
    @Override
    @ResponseBody
    public String submit(HttpServletRequest request, Model model, Receipt receipt) {
        receipt.setSubmitDate(new Date());
        receipt.setSubmitor(SessionCache.getUserCode());
        receipt.setIssubmit(1);
        return receiptClient.submit(receipt);
    }

    /**
     * s审核
     * @param request
     * @param model
     * @param receipt
     * @return
     */
    @Override
    @ResponseBody
    public String audit(HttpServletRequest request, Model model, Receipt receipt) {
        receipt.setIsaudit(0);
        receipt.setAuditDate(new Date());
        receipt.setAuditor(SessionCache.getUserCode());
        return receiptClient.audit(receipt);
    }

    @ResponseBody
    @RequestMapping(value = "/retreat",method = RequestMethod.POST)
    public String retreat(HttpServletRequest request, Model model, Receipt receipt) {
//        receipt.setIsaudit(0);
//        receipt.setAuditDate(new Date());
//        receipt.setAuditor(SessionCache.getUserCode());
        return receiptClient.retreat(receipt);
    }
}
