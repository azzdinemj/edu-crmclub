package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysLog;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogClient extends SystemBaseClient
        implements IFindClient<SysLog,Response<List<SysLog>>> {




  

    /**
     * 查询
     * @param sysDict
     * @return
     */
    @Override
    public Response<List<SysLog>> find(SysLog sysLog) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysLog);
        Response<List<SysLog>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysLog>>>(){});
        return response;
    }


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/sysLog";
    }

   
}
