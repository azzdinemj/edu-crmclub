package com.wuxue.view.controllers.report;

import com.wuxue.model.EnrolmentPerformanceReport;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.report.EnrolmentPerformanceReportClient;
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
@RequestMapping(value = "/report/enrolmentPerformanceReport")
public class EnrolmentPerformanceReportController extends BaseController
        implements IQueryController<EnrolmentPerformanceReport,String>,IQueryByPagingController<EnrolmentPerformanceReport,Map<String,Object>>{

    @Autowired
    private EnrolmentPerformanceReportClient enrolmentPerformanceReportClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, EnrolmentPerformanceReport enrolmentPerformanceReport)  {
        return "/report/enrolmentPerformanceReportList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, EnrolmentPerformanceReport enrolmentPerformanceReport, Integer sEcho, Integer start, Integer length) {
//        enrolmentPerformanceReport.setPageNo((start/length)+1);
//        enrolmentPerformanceReport.setPageSize(length);
        Response<List<EnrolmentPerformanceReport>> listResponse = enrolmentPerformanceReportClient.find(enrolmentPerformanceReport);
        List<EnrolmentPerformanceReport> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
