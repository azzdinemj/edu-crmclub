package com.wuxue.view.client.heyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.HyOrder;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.HeYunBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyOrderClient extends HeYunBaseClient implements IFindClient<HyOrder,Response<List<HyOrder>>>
,ISaveClient<HyOrder,String>,IFindByPrimaryKeyClient<HyOrder,Response<HyOrder>> {

    @Override
    protected String getPageName() { return "/hyOrder"; }


    @Override
    public Response<List<HyOrder>> find(HyOrder hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyUser);
        Response<List<HyOrder>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<HyOrder>>>(){});
        return response;
    }


    @Override
    public Response<HyOrder> findByPrimaryKey(HyOrder hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyUser);
        Response<HyOrder> response = JSON.parseObject(responseXml,new TypeReference<Response<HyOrder>>(){});
        return response;
    }

    @Override
    public String save(HyOrder hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),hyUser);
       // Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    //更改订单状态（添加更改记录）
    public String updateStatus(HyOrder hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.UPDATESTATUS),hyUser);
        return responseXml;
    }

    //统计
    public Response countby(HyOrder hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.COUNTBY),hyUser);
        Response response = JSON.parseObject(responseXml, new TypeReference<Response>(){});
        return response;
    }



}
