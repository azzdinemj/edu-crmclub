package com.wuxue.api.controller.report;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.service.MarketReportService;
import com.wuxue.api.service.TeacherCourseReportService;
import com.wuxue.model.MarketReport;
import com.wuxue.model.TeacherCourseReport;
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
@RequestMapping(value = "api/report/teacherCourseReport")
public class TeacherCourseReportController implements IFindController<TeacherCourseReport>{
    @Autowired
    private TeacherCourseReportService teacherCourseReportService;

    @Override
    public Response Find(@RequestBody Request<TeacherCourseReport> teacherCourseReportRequest) {
        return teacherCourseReportService.find(teacherCourseReportRequest);
    }
}
