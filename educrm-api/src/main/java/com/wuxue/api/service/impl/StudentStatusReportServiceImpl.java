package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.StudentStatusReportMapper;
import com.wuxue.api.service.StudentStatusReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentStatusReport;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentStatusReportService")
public class StudentStatusReportServiceImpl implements StudentStatusReportService{
    @Autowired
    StudentStatusReportMapper studentStatusReportMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response find(Request<StudentStatusReport> tParams) {
        Response response = Response.newResponse();

        String message = "";
        try {
            StudentStatusReport studentStatusReport = tParams.getData();
            List<StudentStatusReport> reportList = studentStatusReportMapper.select(studentStatusReport);
            Integer countBy = studentStatusReportMapper.countBy(studentStatusReport);
            response.setTotal(countBy);
            response.setData(reportList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

}
