package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Linkman;
import com.wuxue.model.Linkman;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkManClient extends StudentBaseClient implements
        IFindClient<Linkman,Response<List<Linkman>>>,ISaveClient<Linkman,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Linkman,Response<Linkman>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/linkman";
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
     * @param linkman
     * @return
     */
    @Override
    public Response<Linkman> findByPrimaryKey(Linkman linkman) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),linkman);
        Response<Linkman> response = JSON.parseObject(responseXml,new TypeReference<Response<Linkman>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param linkman
     * @return
     */
    @Override
    public Response<List<Linkman>> find(Linkman linkman) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),linkman);
        Response<List<Linkman>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Linkman>>>(){});
        return response;

    }

    /**
     *保存
     * @param linkman
     * @return
     */
    @Override
    public String save(Linkman linkman){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),linkman);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
