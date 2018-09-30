package com.wuxue.api.controller.report;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.service.StudentCourseReportService;
import com.wuxue.api.service.TeacherCourseReportService;
import com.wuxue.model.StudentCourseReport;
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
@RequestMapping(value = "api/report/studentCourseReport")
public class StudentCourseReportController implements IFindController<StudentCourseReport>{
    @Autowired
    private StudentCourseReportService studentCourseReportService;

    @Override
    public Response Find(@RequestBody Request<StudentCourseReport> studentCourseReportRequest) {
        return studentCourseReportService.find(studentCourseReportRequest);
    }
}
