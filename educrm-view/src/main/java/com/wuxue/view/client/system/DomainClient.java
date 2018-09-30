package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Domain;
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
public class DomainClient extends SystemBaseClient
        implements IFindClient<Domain,Response<List<Domain>>>,ISaveClient<Domain,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Domain,Response<Domain>> {




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
     * @param domain
     * @return
     */
    @Override
    public Response<List<Domain>> find(Domain domain) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),domain);
        Response<List<Domain>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Domain>>>(){});
        return response;
    }

    /**
     *保存
     * @param domain
     * @return
     */
    @Override
    public String save(Domain domain){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),domain);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/domain";
    }

    /**
     * 主键查询
     * @param domain
     * @return
     */
    @Override
    public Response<Domain> findByPrimaryKey(Domain domain) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),domain);
        Response<Domain> response = JSON.parseObject(responseXml,new TypeReference<Response<Domain>>(){});
        return response;
    }

    public Response<Domain> getUrl(Domain domain) {
        String responseXml = POST(getSendUrl(ActionEnum.GETURL),domain);
        Response<Domain> response = JSON.parseObject(responseXml,new TypeReference<Response<Domain>>(){});
        return response;
    }
}
