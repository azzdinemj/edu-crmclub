package com.wuxue.view.client.finance;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Receivable;

import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.FinanceBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivableClient extends FinanceBaseClient
        implements IFindClient<Receivable,Response<List<Receivable>>>,ISaveClient<Receivable,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Receivable,Response<Receivable>>,
        ISubmitClient<Receivable,String>,IAuditClient<Receivable,String> {




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
    public Response<List<Receivable>> find(Receivable receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),receivable);
        Response<List<Receivable>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Receivable>>>(){});
        return response;
    }

    /**
     *保存
     * @param receivable
     * @return
     */
    @Override
    public String save(Receivable receivable){
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
        return "/receivable";
    }


    @Override
    public Response<Receivable> findByPrimaryKey(Receivable receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),receivable);
        Response<Receivable> response = JSON.parseObject(responseXml,new TypeReference<Response<Receivable>>(){});
        return response;
    }

    @Override
    public String submit(Receivable receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),receivable);
        return responseXml;
    }

    @Override
    public String audit(Receivable receivable) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),receivable);
        return responseXml;
    }
}
