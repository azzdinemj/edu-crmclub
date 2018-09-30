package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.EmployeeReportMapper;
import com.wuxue.api.mapper.StudentCourseReportMapper;
import com.wuxue.api.service.EmployeeReportService;
import com.wuxue.api.service.StudentCourseReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.EmployeeReport;
import com.wuxue.model.StudentCourseReport;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentCourseReportService")
public class StudentCourseReportServiceImpl implements StudentCourseReportService {
    @Autowired
    StudentCourseReportMapper studentCourseReportMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response find(Request<StudentCourseReport> tParams) {
        Response response = Response.newResponse();

        String message = "";
        try {
            StudentCourseReport studentCourseReport = tParams.getData();
            PageHelper.startPage(studentCourseReport.getPageNo(),studentCourseReport.getPageSize());
            List<StudentCourseReport> reportList = studentCourseReportMapper.select(studentCourseReport);
            PageInfo pageInfo = new PageInfo(reportList);
            response.setTotal(pageInfo.getTotal());
            response.setData(reportList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

}
