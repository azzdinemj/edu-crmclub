package com.wuxue.view.controllers.report;

import com.wuxue.model.EmployeeReport;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.report.EmployeeReportClient;
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
@RequestMapping(value = "/report/employeeReport")
public class EmployeeReportController extends BaseController
        implements IQueryController<EmployeeReport,String>,IQueryByPagingController<EmployeeReport,Map<String,Object>>{

    @Autowired
    private EmployeeReportClient employeeReportClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, EmployeeReport employeeReport)  {
        return "/report/employeeReportList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, EmployeeReport employeeReport, Integer sEcho, Integer start, Integer length) {
//        employeeReport.setPageNo((start/length)+1);
//        employeeReport.setPageSize(length);
        Response<List<EmployeeReport>> listResponse = employeeReportClient.find(employeeReport);
        List<EmployeeReport> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
