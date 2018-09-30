package com.wuxue.view.client.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentStatusReport;
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
public class StudentStatusReportClient extends ReportClient{

    /**
     * 查询
     * @param studentStatusReport
     * @return
     */
    public Response<List<StudentStatusReport>> find(StudentStatusReport studentStatusReport) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentStatusReport);
        Response<List<StudentStatusReport>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentStatusReport>>>(){});
        return response;
    }


    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/studentStatusReport"; }

}

