package com.wuxue.api.controller.smallroutine.client.heyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.HyOrderDetail;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.client.HeYunBaseClient;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyOrderDetailClient extends HeYunBaseClient implements IFindClient<HyOrderDetail,Response<List<HyOrderDetail>>>
,ISaveClient<HyOrderDetail,Response>,IFindByPrimaryKeyClient<HyOrderDetail,Response<HyOrderDetail>> {

    @Override
    protected String getPageName() { return "/hyOrderDetail"; }


    @Override
    public Response<List<HyOrderDetail>> find(HyOrderDetail hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyUser);
        Response<List<HyOrderDetail>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<HyOrderDetail>>>(){});
        return response;
    }


    @Override
    public Response<HyOrderDetail> findByPrimaryKey(HyOrderDetail hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyUser);
        Response<HyOrderDetail> response = JSON.parseObject(responseXml,new TypeReference<Response<HyOrderDetail>>(){});
        return response;
    }

    @Override
    public Response save(HyOrderDetail hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),hyUser);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }

}
