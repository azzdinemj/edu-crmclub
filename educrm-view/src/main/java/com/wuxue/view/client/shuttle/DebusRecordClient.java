package com.wuxue.view.client.shuttle;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.shuttle.DebusRecord;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ShuttleBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebusRecordClient extends ShuttleBaseClient
        implements IFindClient<DebusRecord,Response<List<DebusRecord>>>,ISaveClient<DebusRecord,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<DebusRecord,Response<DebusRecord>> {




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
     * @param debusRecord
     * @return
     */
    @Override
    public Response<List<DebusRecord>> find(DebusRecord debusRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),debusRecord);
        Response<List<DebusRecord>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<DebusRecord>>>(){});
        return response;
    }

    /**
     *保存
     * @param department
     * @return
     */
    @Override
    public String save(DebusRecord department){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),department);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/debusRecord";
    }

    /**
     * 主键查询
     * @param department
     * @return
     */
    @Override
    public Response<DebusRecord> findByPrimaryKey(DebusRecord department) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),department);
        Response<DebusRecord> response = JSON.parseObject(responseXml,new TypeReference<Response<DebusRecord>>(){});
        return response;
    }
}
