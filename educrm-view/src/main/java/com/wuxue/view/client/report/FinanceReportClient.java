package com.wuxue.view.client.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.FinanceReport;
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
public class FinanceReportClient extends ReportClient{

    /**
     * 查询
     * @param financeReport
     * @return
     */
    public Response<List<FinanceReport>> find(FinanceReport financeReport) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),financeReport);
        Response<List<FinanceReport>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<FinanceReport>>>(){});
        return response;
    }


    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/financeReport"; }

}

