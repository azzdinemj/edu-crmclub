package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysDict;
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
public class SysDicClient extends SystemBaseClient
        implements IFindClient<SysDict,Response<List<SysDict>>>,ISaveClient<SysDict,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SysDict,Response<SysDict>> {




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
     * @param sysDict
     * @return
     */
    @Override
    public Response<List<SysDict>> find(SysDict sysDict) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysDict);
        Response<List<SysDict>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysDict>>>(){});
        return response;
    }

    /**
     *保存
     * @param sysDict
     * @return
     */
    @Override
    public String save(SysDict sysDict){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),sysDict);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/sysDict";
    }

    /**
     * 主键查询
     * @param sysDict
     * @return
     */
    @Override
    public Response<SysDict> findByPrimaryKey(SysDict sysDict) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysDict);
        Response<SysDict> response = JSON.parseObject(responseXml,new TypeReference<Response<SysDict>>(){});
        return response;
    }
}
