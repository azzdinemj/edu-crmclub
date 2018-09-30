package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysUser;
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
public class SysUserClient extends SystemBaseClient
        implements IFindClient<SysUser,Response<List<SysUser>>>,ISaveClient<SysUser,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SysUser,Response<SysUser>> {

    /**
     * 登录
     * @param sysUser
     * @return
     */
    public Response<SysUser> login(SysUser sysUser) {
        String responseXml = POST(getSendUrl(ActionEnum.LOGIN),sysUser);
        Response<SysUser> response = JSON.parseObject(responseXml,new TypeReference<Response<SysUser>>(){});
        return response;
    }


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
     * @param sysUser
     * @return
     */
    @Override
    public Response<List<SysUser>> find(SysUser sysUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysUser);
        Response<List<SysUser>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysUser>>>(){});
        return response;
    }

    /**
     *保存
     * @param sysUser
     * @return
     */
    @Override
    public String save(SysUser sysUser){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),sysUser);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/sysUser";
    }


    @Override
    public Response<SysUser> findByPrimaryKey(SysUser sysUser) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysUser);
        Response<SysUser> response = JSON.parseObject(responseXml,new TypeReference<Response<SysUser>>(){});
        return response;
    }

    public String updatePassword(SysUser sysUser) {
        String responseXml = POST(getSendUrl(ActionEnum.UPDATEPASSWORD),sysUser);
//        Response<SysUser> response = JSON.parseObject(responseXml,new TypeReference<Response<SysUser>>(){});
        return responseXml;
    }
}
