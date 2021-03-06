package com.wuxue.api.controller.smallroutine.client.heyun;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.controller.smallroutine.client.HeYunBaseClient;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IDeleteClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import com.wuxue.model.HyLinkman;
import com.wuxue.utils.contract.Response;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyLinkmanClient extends HeYunBaseClient implements
        IFindClient<HyLinkman,Response<List<HyLinkman>>>,ISaveClient<HyLinkman,Response>,
        IDeleteClient<HyLinkman,Object>,IFindByPrimaryKeyClient<HyLinkman,Response<HyLinkman>>
       {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/hyLinkman";
    }
    /**
     * 删除
     * @param
     * @return
     */
    @Override
    public String delete(HyLinkman product) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),product);
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param product
     * @return
     */
    @Override
    public Response<HyLinkman> findByPrimaryKey(HyLinkman product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<HyLinkman> response = JSON.parseObject(responseXml,new TypeReference<Response<HyLinkman>>(){});
        return response;
    }


    /**
     * 查询列表
     * @param
     * @return
     */
    @Override
    public Response<List<HyLinkman>> find(HyLinkman product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<List<HyLinkman>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<HyLinkman>>>(){});
        return response;

    }
    

    /**
     *保存
     * @param
     * @return
     */
    @Override
    public Response save(HyLinkman schedule){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),schedule);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }

   
}
