package com.wuxue.api.controller.smallroutine.client.heyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.HyUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.client.HeYunBaseClient;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyUserClient extends HeYunBaseClient implements IFindClient<HyUser,Response<List<HyUser>>>
,ISaveClient<HyUser,Response>,IFindByPrimaryKeyClient<HyUser,Response<HyUser>> {

    @Override
    protected String getPageName() { return "/hyUser"; }


    @Override
    public Response<List<HyUser>> find(HyUser hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyUser);
        Response<List<HyUser>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<HyUser>>>(){});
        return response;
    }


    @Override
    public Response<HyUser> findByPrimaryKey(HyUser hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyUser);
        Response<HyUser> response = JSON.parseObject(responseXml,new TypeReference<Response<HyUser>>(){});
        return response;
    }

    @Override
    public Response save(HyUser hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),hyUser);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }

}
