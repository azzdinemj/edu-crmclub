package com.wuxue.api.controller.smallroutine.client.wxOpenid;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.controller.smallroutine.client.WxOpenidBaseClient;
import com.wuxue.model.Datavalid;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IDeleteClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class DatavalidClient extends WxOpenidBaseClient
        implements ISaveClient<Datavalid,Response>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Datavalid,Response<Datavalid>> {




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
    public Response save(Datavalid employee){
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
    public Response<Datavalid> findByPrimaryKey(Datavalid employee) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employee);
        Response<Datavalid> response = JSON.parseObject(responseXml,new TypeReference<Response<Datavalid>>(){});
        return response;
    }


    /**
     * 根据电话查找最近的一条验证码记录
     * @param employee
     * @return
     */
    public Response<Datavalid> findByphone(Datavalid employee) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employee);
        Response<Datavalid> response = JSON.parseObject(responseXml,new TypeReference<Response<Datavalid>>(){});
        return response;
    }

    /**
     * 根据 pkempstu
     * @param datavalid
     * @return
     */
    public Response<Datavalid> findBypkempstu(Datavalid datavalid) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),datavalid);
        Response<Datavalid> response = JSON.parseObject(responseXml,new TypeReference<Response<Datavalid>>(){});
        return response;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/datavalid";
    }
}
