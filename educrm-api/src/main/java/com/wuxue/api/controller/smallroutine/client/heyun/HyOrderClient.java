package com.wuxue.api.controller.smallroutine.client.heyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.HyOrder;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.client.HeYunBaseClient;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyOrderClient extends HeYunBaseClient implements IFindClient<HyOrder,Response<List<HyOrder>>>
,ISaveClient<HyOrder,Response>,IFindByPrimaryKeyClient<HyOrder,Response<HyOrder>> {

    @Override
    protected String getPageName() { return "/hyOrder"; }


    @Override
    public Response<List<HyOrder>> find(HyOrder hyOrder) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyOrder);
        Response<List<HyOrder>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<HyOrder>>>(){});
        return response;
    }


    @Override
    public Response<HyOrder> findByPrimaryKey(HyOrder hyOrder) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyOrder);
        Response<HyOrder> response = JSON.parseObject(responseXml,new TypeReference<Response<HyOrder>>(){});
        return response;
    }

    @Override
    public Response save(HyOrder hyOrder) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),hyOrder);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }


    //保存订单  关联添加操作
    public Response saveOrder(String str) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVEORDER),str);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }

}
