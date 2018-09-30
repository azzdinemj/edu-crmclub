package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysRole;
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
public class SysRoleClient extends SystemBaseClient
        implements IFindClient<SysRole,Response<List<SysRole>>>,ISaveClient<SysRole,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SysRole,Response<SysRole>> {



    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
        //Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param sysRole
     * @return
     */
    @Override
    public Response<List<SysRole>> find(SysRole sysRole) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysRole);
        Response<List<SysRole>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysRole>>>(){});
        return response;
    }

    /**
     *保存
     * @param sysRole
     * @return
     */
    @Override
    public String save(SysRole sysRole){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),sysRole);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/sysrole";
    }



    public Response<List<SysRole>> getRoleList(String pkDomain){

        String responseXml = POST(getSendUrl(ActionEnum.DOMAINROLE),pkDomain);
        Response<List<SysRole>> response = JSON.parseObject(responseXml, new TypeReference<Response<List<SysRole>>>(){});
        return response;
    }

    public Response<SysRole> getUserRole(String pkUser){

        String responseXml = POST(getSendUrl(ActionEnum.GETUSERROLE),pkUser);
        Response<SysRole> response = JSON.parseObject(responseXml,new TypeReference<Response<SysRole>>(){});
        return response;
    }

    /**
     * 主键查询
     * @param sysRole
     * @return
     */
    @Override
    public Response<SysRole> findByPrimaryKey(SysRole sysRole) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysRole);
        Response<SysRole> response = JSON.parseObject(responseXml,new TypeReference<Response<SysRole>>(){});
        return response;
    }
}
