package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.EnrolmentPerformanceReportMapper;
import com.wuxue.api.service.EnrolmentPerformanceReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.EnrolmentPerformanceReport;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("enrolmentPerformanceReportService")
public class EnrolmentPerformanceReportServiceImpl implements EnrolmentPerformanceReportService{
    @Autowired
    EnrolmentPerformanceReportMapper enrolmentPerformanceReportMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response find(Request<EnrolmentPerformanceReport> tParams) {
        Response response = Response.newResponse();

        String message = "";
        try {
            EnrolmentPerformanceReport enrolmentPerformanceReport = tParams.getData();
            List<EnrolmentPerformanceReport> reportList = enrolmentPerformanceReportMapper.select(enrolmentPerformanceReport);
            Integer countBy = enrolmentPerformanceReportMapper.countBy(enrolmentPerformanceReport);
            response.setTotal(countBy);
            response.setData(reportList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

}
