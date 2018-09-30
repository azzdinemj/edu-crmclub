package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.MarketReportMapper;
import com.wuxue.api.service.MarketReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.MarketReport;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("marketReportService")
public class MarketReportServiceImpl implements MarketReportService{
    @Autowired
    MarketReportMapper marketReportMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response find(Request<MarketReport> tParams) {
        Response response = Response.newResponse();

        String message = "";
        try {
            MarketReport marketReport = tParams.getData();
            List<MarketReport> reportList = marketReportMapper.select(marketReport);
            Integer countBy = marketReportMapper.countBy(marketReport);
            response.setTotal(countBy);
            response.setData(reportList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

}
