package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.EmployeeReportMapper;
import com.wuxue.api.service.EmployeeReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.EmployeeReport;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("employeeReportService")
public class EmployeeReportServiceImpl implements EmployeeReportService {
    @Autowired
    EmployeeReportMapper employeeReportMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response find(Request<EmployeeReport> tParams) {
        Response response = Response.newResponse();

        String message = "";
        try {
            EmployeeReport employeeReport = tParams.getData();
            List<EmployeeReport> reportList = employeeReportMapper.select(employeeReport);
            Integer countBy = employeeReportMapper.countBy(employeeReport);
            response.setTotal(countBy);
            response.setData(reportList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

}
