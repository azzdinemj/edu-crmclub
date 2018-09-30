package com.wuxue.api.controller.report;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.service.EnrolmentPerformanceReportService;
import com.wuxue.model.EnrolmentPerformanceReport;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/04/25.
 */
@RestController
@RequestMapping(value = "api/report/enrolmentPerformanceReport")
public class EnrolmentPerformanceReportController implements IFindController<EnrolmentPerformanceReport>{
    @Autowired
    private EnrolmentPerformanceReportService enrolmentPerformanceReportService;

    @Override
    public Response Find(@RequestBody Request<EnrolmentPerformanceReport> enrolmentPerformanceReport) {
        return enrolmentPerformanceReportService.find(enrolmentPerformanceReport);
    }
}
