package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SchoolBus;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolBusClient extends SystemBaseClient implements
        IFindClient<SchoolBus,Response<List<SchoolBus>>>,ISaveClient<SchoolBus,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SchoolBus,Response<SchoolBus>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/schoolbus";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(String s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param schoolBus
     * @return
     */
    @Override
    public Response<SchoolBus> findByPrimaryKey(SchoolBus schoolBus) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),schoolBus);
        Response<SchoolBus> response = JSON.parseObject(responseXml,new TypeReference<Response<SchoolBus>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param schoolBus
     * @return
     */
    @Override
    public Response<List<SchoolBus>> find(SchoolBus schoolBus) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),schoolBus);
        Response<List<SchoolBus>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SchoolBus>>>(){});
        return response;

    }

    /**
     *保存
     * @param schoolBus
     * @return
     */
    @Override
    public String save(SchoolBus schoolBus){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),schoolBus);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
