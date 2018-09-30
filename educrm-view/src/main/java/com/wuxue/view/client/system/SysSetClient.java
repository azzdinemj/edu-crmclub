package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysSet;
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
public class SysSetClient extends SystemBaseClient
        implements IFindClient<SysSet,Response<List<SysSet>>>,ISaveClient<SysSet,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SysSet,Response<SysSet>> {




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
     * 查询
     * @param sysSet
     * @return
     */
    @Override
    public Response<List<SysSet>> find(SysSet sysSet) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysSet);
        Response<List<SysSet>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysSet>>>(){});
        return response;
    }

    /**
     *保存
     * @param sysSet
     * @return
     */
    @Override
    public String save(SysSet sysSet){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),sysSet);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/sysSet";
    }

    /**
     * 主键查询
     * @param sysSet
     * @return
     */
    @Override
    public Response<SysSet> findByPrimaryKey(SysSet sysSet) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysSet);
        Response<SysSet> response = JSON.parseObject(responseXml,new TypeReference<Response<SysSet>>(){});
        return response;
    }

    public Response<SysSet> getUrl(SysSet sysSet) {
        String responseXml = POST(getSendUrl(ActionEnum.GETURL),sysSet);
        Response<SysSet> response = JSON.parseObject(responseXml,new TypeReference<Response<SysSet>>(){});
        return response;
    }
}
