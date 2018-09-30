package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.ClassinfoStudentReportMapper;
import com.wuxue.api.service.ClassinfoStudentReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.ClassinfoStudentReport;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("classinfoStudentReportService")
public class ClassinfoStudentReportServiceImpl implements ClassinfoStudentReportService{
    @Autowired
    ClassinfoStudentReportMapper classinfoStudentReportMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response find(Request<ClassinfoStudentReport> tParams) {
        Response response = Response.newResponse();

        String message = "";
        try {
            ClassinfoStudentReport classinfoStudentReport = tParams.getData();
            List<ClassinfoStudentReport> reportList = classinfoStudentReportMapper.select(classinfoStudentReport);
            Integer countBy = classinfoStudentReportMapper.countBy(classinfoStudentReport);
            response.setTotal(countBy);
            response.setData(reportList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

}
