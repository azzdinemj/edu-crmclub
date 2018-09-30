package com.wuxue.view.controllers.report;

import com.wuxue.model.MarketReport;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.report.MarketReportClient;
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
@RequestMapping(value = "/report/marketReport")
public class MarketReportController extends BaseController
        implements IQueryController<MarketReport,String>,IQueryByPagingController<MarketReport,Map<String,Object>>{

    @Autowired
    private MarketReportClient marketReportClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, MarketReport marketReport)  {
        return "/report/marketReportList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, MarketReport marketReport, Integer sEcho, Integer start, Integer length) {
//        marketReport.setPageNo((start/length)+1);
//        marketReport.setPageSize(length);
        Response<List<MarketReport>> listResponse = marketReportClient.find(marketReport);
        List<MarketReport> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
