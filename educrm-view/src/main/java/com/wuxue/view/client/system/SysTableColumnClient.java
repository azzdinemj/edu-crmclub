package com.wuxue.view.client.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysTableColumn;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import org.springframework.stereotype.Service;

/**
 * Created by Jamie on 2018/4/1.
 */
@Service
public class SysTableColumnClient extends SystemBaseClient
    implements IFindByPrimaryKeyClient<SysTableColumn,Response<SysTableColumn>> {
    @Override
    protected String getPageName() {
        return "/sysTableColumn";
    }

    @Override
    public Response<SysTableColumn> findByPrimaryKey(SysTableColumn sysTableColumn) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysTableColumn);
        Response<SysTableColumn> response = JSON.parseObject(responseXml,new TypeReference<Response<SysTableColumn>>(){});
        return response;
    }
}
