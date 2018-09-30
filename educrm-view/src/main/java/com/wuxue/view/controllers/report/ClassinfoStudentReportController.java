package com.wuxue.view.controllers.report;

import com.wuxue.model.ClassinfoStudentReport;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.report.ClassinfoStudentReportClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/report/classinfoStudentReport")
public class ClassinfoStudentReportController extends BaseController
        implements IQueryController<ClassinfoStudentReport,String>,IQueryByPagingController<ClassinfoStudentReport,Map<String,Object>>{

    @Autowired
    private ClassinfoStudentReportClient classinfoStudentReportClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, ClassinfoStudentReport classinfoStudentReport)  {
        return "/report/classinfoStudentReportList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ClassinfoStudentReport classinfoStudentReport, Integer sEcho, Integer start, Integer length) {
        classinfoStudentReport.setPageNo((start/length)+1);
        classinfoStudentReport.setPageSize(length);
        Response<List<ClassinfoStudentReport>> listResponse = classinfoStudentReportClient.find(classinfoStudentReport);
        List<ClassinfoStudentReport> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
