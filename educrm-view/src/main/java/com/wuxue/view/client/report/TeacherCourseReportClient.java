package com.wuxue.view.client.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.TeacherCourseReport;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ReportClient;
import com.wuxue.view.enums.ActionEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description:  预约
 * 访问api服务类
* @author Rogue
* @date  13:42 2018/3/23
* @version V1.0
*/
@Service
public class TeacherCourseReportClient extends ReportClient{

    /**
     * 查询
     * @param teacherCourseReport
     * @return
     */
    public Response<List<TeacherCourseReport>> find(TeacherCourseReport teacherCourseReport) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),teacherCourseReport);
        Response<List<TeacherCourseReport>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<TeacherCourseReport>>>(){});
        return response;
    }


    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/teacherCourseReport"; }

}

