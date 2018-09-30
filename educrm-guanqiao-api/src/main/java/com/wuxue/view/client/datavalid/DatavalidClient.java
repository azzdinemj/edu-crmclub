package com.wuxue.view.client.datavalid;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.wuxue.model.Datavalid;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.BaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class DatavalidClient extends BaseClient
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

    @Override
    protected String getModuleName() {
        return "/wxopenid";
    }
}
