package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.TeacherCourseReportMapper;
import com.wuxue.api.service.TeacherCourseReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.TeacherCourseReport;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("teacherCourseReportService")
public class TeacherCourseReportServiceImpl implements TeacherCourseReportService {
    @Autowired
    TeacherCourseReportMapper teacherCourseReportMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response find(Request<TeacherCourseReport> tParams) {
        Response response = Response.newResponse();

        String message = "";
        try {
            TeacherCourseReport teacherCourseReport = tParams.getData();
            PageHelper.startPage(teacherCourseReport.getPageNo(),teacherCourseReport.getPageSize());
            List<TeacherCourseReport> reportList = teacherCourseReportMapper.select(teacherCourseReport);
            PageInfo pageInfo = new PageInfo(reportList);
            response.setTotal(pageInfo.getTotal());
            response.setData(reportList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

}
