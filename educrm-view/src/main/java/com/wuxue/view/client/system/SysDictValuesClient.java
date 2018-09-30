package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SysDictValues;
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
public class SysDictValuesClient extends SystemBaseClient
        implements IFindClient<SysDictValues,Response<List<SysDictValues>>>,ISaveClient<SysDictValues,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SysDictValues,Response<SysDictValues>> {




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
     * @param sysDictValues
     * @return
     */
    @Override
    public Response<List<SysDictValues>> find(SysDictValues sysDictValues) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysDictValues);
        Response<List<SysDictValues>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SysDictValues>>>(){});
        return response;
    }

    /**
     *保存
     * @param sysDictValues
     * @return
     */
    @Override
    public String save(SysDictValues sysDictValues){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),sysDictValues);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/sysDictValues";
    }

    /**
     * 主键查询
     * @param sysDictValues
     * @return
     */
    @Override
    public Response<SysDictValues> findByPrimaryKey(SysDictValues sysDictValues) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),sysDictValues);
        Response<SysDictValues> response = JSON.parseObject(responseXml,new TypeReference<Response<SysDictValues>>(){});
        return response;
    }
}
