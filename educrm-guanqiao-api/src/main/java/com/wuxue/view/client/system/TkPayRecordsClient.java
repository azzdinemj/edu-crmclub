package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.TkPayRecords;
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
public class TkPayRecordsClient extends SystemBaseClient
        implements IFindClient<TkPayRecords,Response<List<TkPayRecords>>>,ISaveClient<TkPayRecords,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<TkPayRecords,Response<TkPayRecords>> {




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
     * @param tkPayRecords
     * @return
     */
    @Override
    public Response<List<TkPayRecords>> find(TkPayRecords tkPayRecords) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),tkPayRecords);
        Response<List<TkPayRecords>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<TkPayRecords>>>(){});
        return response;
    }

    /**
     *保存
     * @param tkPayRecords
     * @return
     */
    @Override
    public String save(TkPayRecords tkPayRecords){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),tkPayRecords);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/tkPayRecords";
    }

    /**
     * 主键查询
     * @param tkPayRecords
     * @return
     */
    @Override
    public Response<TkPayRecords> findByPrimaryKey(TkPayRecords tkPayRecords) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),tkPayRecords);
        Response<TkPayRecords> response = JSON.parseObject(responseXml,new TypeReference<Response<TkPayRecords>>(){});
        return response;
    }
}
