package com.wuxue.view.controllers.report;

import com.wuxue.model.FinanceReport;
import com.wuxue.model.StudentStatusReport;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.report.FinanceReportClient;
import com.wuxue.view.client.report.StudentStatusReportClient;
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
@RequestMapping(value = "/report/studentStatusReport")
public class StudentStatusReportController extends BaseController
        implements IQueryController<StudentStatusReport,String>,IQueryByPagingController<StudentStatusReport,Map<String,Object>>{

    @Autowired
    private StudentStatusReportClient studentStatusReportClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, StudentStatusReport studentStatusReport)  {
        return "/report/studentStatusReportList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, StudentStatusReport studentStatusReport, Integer sEcho, Integer start, Integer length) {
//        financeReport.setPageNo((start/length)+1);
//        financeReport.setPageSize(length);
        Response<List<StudentStatusReport>> listResponse = studentStatusReportClient.find(studentStatusReport);
        List<StudentStatusReport> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
