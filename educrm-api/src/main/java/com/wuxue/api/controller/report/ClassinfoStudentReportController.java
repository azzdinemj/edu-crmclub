package com.wuxue.api.controller.report;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ClassinfoStudentReportService;
import com.wuxue.model.ClassinfoStudentReport;
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
@RequestMapping(value = "api/report/classinfoStudentReport")
public class ClassinfoStudentReportController implements IFindController<ClassinfoStudentReport>{
    @Autowired
    private ClassinfoStudentReportService classinfoStudentReportService;

    @Override
    public Response Find(@RequestBody Request<ClassinfoStudentReport> classinfoStudentReport) {
        return classinfoStudentReportService.find(classinfoStudentReport);
    }
}
