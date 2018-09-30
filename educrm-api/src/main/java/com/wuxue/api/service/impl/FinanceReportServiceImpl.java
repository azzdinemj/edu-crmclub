package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.FinanceReportMapper;
import com.wuxue.api.service.FinanceReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.FinanceReport;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("financeReportService")
public class FinanceReportServiceImpl implements FinanceReportService{
    @Autowired
    FinanceReportMapper financeReportMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response find(Request<FinanceReport> tParams) {
        Response response = Response.newResponse();

        String message = "";
        try {
            FinanceReport financeReport = tParams.getData();
            List<FinanceReport> reportList = financeReportMapper.select(financeReport);
            Integer countBy = financeReportMapper.countBy(financeReport);
            response.setTotal(countBy);
            response.setData(reportList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

}
