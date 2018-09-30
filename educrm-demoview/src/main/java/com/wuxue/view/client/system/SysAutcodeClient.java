package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysAutocode;
import com.wuxue.utils.common.DataUtils;
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
public class SysAutcodeClient extends SystemBaseClient implements
        IFindClient<SysAutocode,Response<List<SysAutocode>>>,ISaveClient<SysAutocode,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SysAutocode,Response<SysAutocode>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/sysAutocode";
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
     * @param sysAutocode
     * @return
     */
    @Override
    public Response<SysAutocode> findByPrimaryKey(SysAutocode sysAutocode) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysAutocode);
        Response<SysAutocode> response = JSON.parseObject(responseXml,new TypeReference<Response<SysAutocode>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param sysAutocode
     * @return
     */
    @Override
    public Response<List<SysAutocode>> find(SysAutocode sysAutocode) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysAutocode);
        Response<List<SysAutocode>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysAutocode>>>(){});
        return response;

    }

    /**
     *保存
     * @param sysAutocode
     * @return
     */
    @Override
    public String save(SysAutocode sysAutocode){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),sysAutocode);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }


    public String getCode(SysAutocode sysAutocode){

        String responseXml = POST(getSendUrl(ActionEnum.GETCODE),sysAutocode);
        Response<String> response = JSON.parseObject(responseXml, new TypeReference<Response<String>>(){});
        return response.getData();
    }
}
