package com.wuxue.view.client.finance;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Payment;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.FinanceBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentClient extends FinanceBaseClient
        implements IFindClient<Payment,Response<List<Payment>>>,ISaveClient<Payment,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Payment,Response<Payment>>,
        ISubmitClient<Payment,String>,IAuditClient<Payment,String> {




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param receivable
     * @return
     */
    @Override
    public Response<List<Payment>> find(Payment receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),receivable);
        Response<List<Payment>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Payment>>>(){});
        return response;
    }

    /**
     *保存
     * @param receivable
     * @return
     */
    @Override
    public String save(Payment receivable){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),receivable);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/payment";
    }


    @Override
    public Response<Payment> findByPrimaryKey(Payment receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),receivable);
        Response<Payment> response = JSON.parseObject(responseXml,new TypeReference<Response<Payment>>(){});
        return response;
    }

    @Override
    public String submit(Payment receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),receivable);
        return responseXml;
    }

    @Override
    public String audit(Payment receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),receivable);
        return responseXml;
    }

    public String retreat(Payment receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.RETREAT),receivable);
        return responseXml;
    }
}
