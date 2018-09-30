package com.wuxue.view.client.heyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.HyOrderChange;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.HeYunBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyOrderChangeClient extends HeYunBaseClient implements IFindClient<HyOrderChange,Response<List<HyOrderChange>>>
,ISaveClient<HyOrderChange,Response>,IFindByPrimaryKeyClient<HyOrderChange,Response<HyOrderChange>> {

    @Override
    protected String getPageName() { return "/hyOrderChange"; }


    @Override
    public Response<List<HyOrderChange>> find(HyOrderChange hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyUser);
        Response<List<HyOrderChange>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<HyOrderChange>>>(){});
        return response;
    }


    @Override
    public Response<HyOrderChange> findByPrimaryKey(HyOrderChange hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyUser);
        Response<HyOrderChange> response = JSON.parseObject(responseXml,new TypeReference<Response<HyOrderChange>>(){});
        return response;
    }

    @Override
    public Response save(HyOrderChange hyUser) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),hyUser);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }

}
