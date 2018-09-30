package com.wuxue.view.client.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysMenu;
import com.wuxue.model.SysMenuButton;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuButtonClient extends SystemBaseClient
        implements IFindClient<SysMenuButton,Response<List<SysMenuButton>>>,
        IFindByPrimaryKeyClient<SysMenuButton,Response<SysMenuButton>>,ISaveClient<SysMenuButton,String> {

    @Override
    public Response<List<SysMenuButton>> find(SysMenuButton sysMenuButton) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysMenuButton);
        Response<List<SysMenuButton>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysMenuButton>>>(){});
        return response;
    }

    @Override
    public Response<SysMenuButton> findByPrimaryKey(SysMenuButton sysMenu) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysMenu);
        Response<SysMenuButton> response = JSON.parseObject(responseXml,new TypeReference<Response<SysMenuButton>>(){});
        return response;
    }

    @Override
    public String save(SysMenuButton sysMenuButton) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),sysMenuButton);
        return responseXml;

    }


    @Override
    protected String getPageName() {
        return "/sysMenuButton";
    }


}
