package com.wuxue.view.controllers.report;

import com.wuxue.model.StudentCourseReport;
import com.wuxue.model.TeacherCourseReport;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.report.StudentCourseReportClient;
import com.wuxue.view.client.report.TeacherCourseReportClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/report/courseReport")
public class CourseReportController extends BaseController
        implements IQueryController<TeacherCourseReport, String>, IQueryByPagingController<TeacherCourseReport, Map<String, Object>> {

    @Autowired
    private TeacherCourseReportClient teacherCourseReportClient;
    @Autowired
    private StudentCourseReportClient studentCourseReportClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, TeacherCourseReport teacherCourseReport) {
        return "/report/courseReportList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, TeacherCourseReport teacherCourseReport, Integer sEcho, Integer start, Integer length) {

        String type = request.getParameter("type");
        String times = request.getParameter("times");
        Date fromDate = null;
        Date toDate = null;
        if (times != null && !"".equals(times)){
            List<Date> dates = DateTimeUtils.StringSubToDate(times);
            fromDate=dates.get(0);
            toDate = dates.get(1);
        }
        if (type != null && "teacher".equals(type)) {
            teacherCourseReport.setPageNo((start / length) + 1);
            teacherCourseReport.setPageSize(length);
            teacherCourseReport.setFromDate(fromDate);
            teacherCourseReport.setToDate(toDate);
            Response<List<TeacherCourseReport>> listResponse = teacherCourseReportClient.find(teacherCourseReport);
            List<TeacherCourseReport> data = listResponse.getData();
            if (data == null) {
                data = new ArrayList<>();
            }
            return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);
        } else {
            StudentCourseReport studentCourseReport = new StudentCourseReport();
            studentCourseReport.setPageNo((start / length) + 1);
            studentCourseReport.setPageSize(length);
            studentCourseReport.setFromDate(fromDate);
            studentCourseReport.setToDate(toDate);
            Response<List<StudentCourseReport>> listResponse = studentCourseReportClient.find(studentCourseReport);
            List<StudentCourseReport> data = listResponse.getData();
            if (data == null) {
                data = new ArrayList<>();
            }
            return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);
        }

    }


}
