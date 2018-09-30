package com.wuxue.api.controller.report;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.service.FinanceReportService;
import com.wuxue.model.FinanceReport;
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
@RequestMapping(value = "api/report/financeReport")
public class FinanceReportController implements IFindController<FinanceReport>{
    @Autowired
    private FinanceReportService financeReportService;

    @Override
    public Response Find(@RequestBody Request<FinanceReport> financeReport) {
        return financeReportService.find(financeReport);
    }
}
