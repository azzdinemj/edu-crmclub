package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Receipt;
import com.wuxue.model.Receipt;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.FinanceBaseClient;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReceiptClient extends FinanceBaseClient implements
        IFindClient<Receipt,Response<List<Receipt>>>,ISaveClient<Receipt,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Receipt,Response<Receipt>>,
        IAuditClient<Receipt,String>,ISubmitClient<Receipt,String>{


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/receipt";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(String s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param receipt
     * @return
     */
    @Override
    public Response<Receipt> findByPrimaryKey(Receipt receipt) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),receipt);
        Response<Receipt> response = JSON.parseObject(responseXml,new TypeReference<Response<Receipt>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param receipt
     * @return
     */
    @Override
    public Response<List<Receipt>> find(Receipt receipt) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),receipt);
        Response<List<Receipt>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Receipt>>>(){});
        return response;

    }

    /**
     *保存
     * @param receipt
     * @return
     */
    @Override
    public String save(Receipt receipt){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),receipt);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }

    @Override
    public String audit(Receipt receipt) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),receipt);
        return responseXml;
    }

    @Override
    public String submit(Receipt receipt) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),receipt);
        return responseXml;
    }

    public String retreat(Receipt receipt) {
        String responseXml = POST(getSendUrl(ActionEnum.RETREAT),receipt);
        return responseXml;
    }

    public Response<List<Map<String,Object>>> findFroRefund(Receipt receipt) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDFROREFUND),receipt);
        Response<List<Map<String,Object>>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Map<String,Object>>>>(){});
        return response;
    }
}
