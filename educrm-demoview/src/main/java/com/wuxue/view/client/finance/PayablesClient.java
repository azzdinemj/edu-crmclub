package com.wuxue.view.client.finance;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Payables;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.FinanceBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayablesClient extends FinanceBaseClient
        implements IFindClient<Payables,Response<List<Payables>>>,ISaveClient<Payables,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Payables,Response<Payables>>,
        ISubmitClient<Payables,String>,IAuditClient<Payables,String> {




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
     * @param payables
     * @return
     */
    @Override
    public Response<List<Payables>> find(Payables payables) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),payables);
        Response<List<Payables>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Payables>>>(){});
        return response;
    }

    /**
     *保存
     * @param payables
     * @return
     */
    @Override
    public String save(Payables payables){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),payables);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/payables";
    }


    @Override
    public Response<Payables> findByPrimaryKey(Payables payables) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),payables);
        Response<Payables> response = JSON.parseObject(responseXml,new TypeReference<Response<Payables>>(){});
        return response;
    }

    @Override
    public String submit(Payables payables) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),payables);
        return responseXml;
    }

    @Override
    public String audit(Payables payables) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),payables);
        return responseXml;
    }

    /**
     *退费收款保存应付单
     * @param payables
     * @return
     */
    public String receiptSavePayables(Payables payables){
        String responseXml = POST(getSendUrl(ActionEnum.RECEIPTSAVEPAYABLES),payables);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    public String refund(Payables payables) {
        String responseXml = POST(getSendUrl(ActionEnum.REFUND),payables);
        return responseXml;
    }
}
