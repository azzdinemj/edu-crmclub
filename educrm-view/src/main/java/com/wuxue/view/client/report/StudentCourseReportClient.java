package com.wuxue.view.client.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentCourseReport;
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
public class StudentCourseReportClient extends ReportClient{

    /**
     * 查询
     * @param studentCourseReport
     * @return
     */
    public Response<List<StudentCourseReport>> find(StudentCourseReport studentCourseReport) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentCourseReport);
        Response<List<StudentCourseReport>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentCourseReport>>>(){});
        return response;
    }


    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/studentCourseReport"; }

}

