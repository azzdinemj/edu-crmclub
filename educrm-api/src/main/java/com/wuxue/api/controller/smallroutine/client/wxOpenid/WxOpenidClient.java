package com.wuxue.api.controller.smallroutine.client.wxOpenid;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.controller.smallroutine.client.WxOpenidBaseClient;
import com.wuxue.model.WxOpenid;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IDeleteClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxOpenidClient extends WxOpenidBaseClient
        implements ISaveClient<WxOpenid,Response>,IFindClient<WxOpenid,Response>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<WxOpenid,Response<WxOpenid>> {




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
     *保存
     * @param employee
     * @return
     */
    @Override
    public Response save(WxOpenid employee){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),employee);
        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return response;
    }

    /**
     * 主键查询
     * @param employee
     * @return
     */
    @Override
    public Response<WxOpenid> findByPrimaryKey(WxOpenid employee) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employee);
        Response<WxOpenid> response = JSON.parseObject(responseXml,new TypeReference<Response<WxOpenid>>(){});
        return response;
    }


    /**
     * @param wxOpenid
     * @return
     */
    @Override
    public Response<WxOpenid> find(WxOpenid wxOpenid) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),wxOpenid);
        Response<WxOpenid> response = JSON.parseObject(responseXml,new TypeReference<Response<WxOpenid>>(){});
        return response;
    }

    /**
     * @param wxOpenid
     * @return
     */
    public Response<List<WxOpenid>> findbyopenid(WxOpenid wxOpenid) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),wxOpenid);
        Response<List<WxOpenid>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<WxOpenid>>>(){});
        return response;
    }


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/wxopenid";
    }
}
