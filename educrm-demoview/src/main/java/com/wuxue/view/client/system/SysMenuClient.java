package com.wuxue.view.client.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysMenu;
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
public class SysMenuClient extends SystemBaseClient
        implements IFindClient<SysMenu,Response<List<SysMenu>>>,ISaveClient<SysMenu,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SysMenu,Response<SysMenu>> {

    @Override
    protected String getPageName() {
        return "/sysMenu";
    }

    @Override
    public Object delete(String id) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),id);
//        Response<List<SysMenu>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysMenu>>>(){});
        return responseXml;
    }

    @Override
    public Response<SysMenu> findByPrimaryKey(SysMenu sysMenu) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysMenu);
        Response<SysMenu> response = JSON.parseObject(responseXml,new TypeReference<Response<SysMenu>>(){});
        return response;
    }

    @Override
    public Response<List<SysMenu>> find(SysMenu sysMenu) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysMenu);
        Response<List<SysMenu>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysMenu>>>(){});
        return response;
    }

    @Override
    public String save(SysMenu sysMenu){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),sysMenu);
//        Response<List<SysMenu>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysMenu>>>(){});
        return responseXml;

    }

    public Response<List<SysMenu>> findparentisnotnull(SysMenu sysMenu) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDPARENTISNOTNULL),sysMenu);
        Response<List<SysMenu>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysMenu>>>(){});
        return response;
    }

    public Response<List<SysMenu>> findparentisnull(SysMenu sysMenu) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDPARENTISNULL),sysMenu);
        Response<List<SysMenu>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysMenu>>>(){});
        return response;
    }

}
