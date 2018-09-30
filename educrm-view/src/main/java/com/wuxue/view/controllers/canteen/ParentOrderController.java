package com.wuxue.view.controllers.canteen;

import com.wuxue.model.Classinfo;
import com.wuxue.model.ParentOrder;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.canteen.ParentOrderClient;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.ClassinfoUtils;
import com.wuxue.view.utils.DateTimeUtils;
import com.wuxue.view.utils.DomainUtils;
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
 * 点餐记录
 */
@Controller
@RequestMapping(value = "/canteen/parentOrderRecord")
public class ParentOrderController extends BaseController
        implements IQueryController<ParentOrder,String>,IQueryByPagingController<ParentOrder,Map<String,Object>> {


    @InitBinder("parentOrder")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("exp.");
    }

    @Autowired
    private ParentOrderClient parentOrderClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private ClassInfoClient  classInfoClient;
    @Autowired
    private ClassinfoUtils classinfoUtils;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, ParentOrder parentOrder)  {

        List<Classinfo> classinfo = classinfoUtils.getClassinfo();
        model.addAttribute("classinfoList",classinfo);

        return "/canteen/parentorderList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ParentOrder parentOrder, Integer sEcho, Integer start, Integer length) {

        parentOrder.setPageNo((start/length)+1);
        parentOrder.setPageSize(length);

        String dateTime = request.getParameter("dateTime");
        if (dateTime != null && !"".equals(dateTime)){
            List<Date> list = DateTimeUtils.StringSubToDate(dateTime);
            parentOrder.setFromDate(list.get(0));
            parentOrder.setToDate(list.get(1));
        }

        Response<List<ParentOrder>> listResponse = parentOrderClient.selectForRecord(parentOrder);
        List<ParentOrder> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    public void initPageAttribute(Model model,String pkDomain){
        domainUtils.setModeAttribute(model,"domain",pkDomain);
    }



}
