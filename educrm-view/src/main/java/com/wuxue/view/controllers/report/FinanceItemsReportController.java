package com.wuxue.view.controllers.report;

import com.wuxue.model.FinanceItemsReport;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.report.FinanceItemsReportClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/report/financeItemsReport")
public class FinanceItemsReportController extends BaseController
        implements IQueryController<FinanceItemsReport,String>,IQueryByPagingController<FinanceItemsReport,Map<String,Object>>{

    @Autowired
    private FinanceItemsReportClient financeItemsReportClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, FinanceItemsReport financeItemsReport)  {

        Response<List<FinanceItemsReport>> listResponse = financeItemsReportClient.find(financeItemsReport);
        model.addAttribute("list",listResponse.getData());
        return "/report/financeItemsReportList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, FinanceItemsReport financeItemsReport, Integer sEcho, Integer start, Integer length) {
//        financeItemsReport.setPageNo((start/length)+1);
//        financeItemsReport.setPageSize(length);
        Response<List<FinanceItemsReport>> listResponse = financeItemsReportClient.find(financeItemsReport);
        List<FinanceItemsReport> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
