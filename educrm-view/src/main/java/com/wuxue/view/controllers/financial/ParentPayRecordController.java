package com.wuxue.view.controllers.financial;

import com.wuxue.base.ResultEntity;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ParentPay;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.canteen.ParentPayClient;
import com.wuxue.view.client.classInfo.ClassInfoClient;
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
import java.util.*;

/**
 * 支付记录
 */
@Controller
@RequestMapping(value = "/finance/parentPayRecord")
public class ParentPayRecordController extends BaseController
        implements IQueryController<Classinfo,String>,IQueryByPagingController<ResultEntity,Map<String,Object>> {


    @InitBinder("parentPay")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("exp.");
    }

    @Autowired
    private ParentPayClient parentPayClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private ClassInfoClient  classInfoClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Classinfo classinfo)  {

        classinfo.setPageSize(10000);
        classinfo.setType(0);
        Response<List<Classinfo>> listResponse = classInfoClient.find(classinfo);
        model.addAttribute("classinfoList",listResponse.getData());

        return "/financial/parentPayRecordList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ResultEntity parentPay, Integer sEcho, Integer start, Integer length) {

        parentPay.setPageNo((start/length)+1);
        parentPay.setPageSize(length);
        String dateTime = request.getParameter("dateTime");
//        String className = request.getParameter("className");
//        String studentName = request.getParameter("studentName");
        if (dateTime != null && !"".equals(dateTime)){
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            List<Date> dates = DateTimeUtils.StringSubToDate(dateTime);
            parentPay.setStartTime(dates.get(0));
            parentPay.setEndTime(dates.get(1));

        }
//        parentPay.setClassName(className);
//        parentPay.setStudentName(studentName);
//        map.put("className",className);
//        map.put("studentName",studentName);
//        parentPay.setPageNo((start/length)+1);
//        parentPay.setPageSize(length);
//        ResultEntity resultEntity = new ResultEntity();

        Response<List<ResultEntity>> listResponse = parentPayClient.find(parentPay);
        List<ResultEntity> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    public void initPageAttribute(Model model,String pkDomain){
        domainUtils.setModeAttribute(model,"domain",pkDomain);
    }



}
