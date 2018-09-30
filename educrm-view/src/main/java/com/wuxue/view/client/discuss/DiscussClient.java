package com.wuxue.view.client.discuss;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.DiscussBaseClient;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.junhwa.Discuss;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CanteenBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussClient extends DiscussBaseClient
        implements IFindClient<ResultEntity,Response<List<ResultEntity>>>,IDeleteClient<String,Object>,
        IFindByPrimaryKeyClient<Discuss,Response<Discuss>>,ISaveClient<Discuss,String>{




    /**
     * 删除
     * @param discuss 主键
     * @return
     */
    @Override
    public String delete(String discuss) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),discuss);
       // Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param discuss
     * @return
     */
    @Override
    public Response<List<ResultEntity>> find(ResultEntity discuss) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),discuss);
        Response<List<ResultEntity>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ResultEntity>>>(){});
        return response;
    }

    public Response<List<Discuss>> getDiscussListForPC(Discuss discuss) {
        String responseXml = POST(getSendUrl(ActionEnum.GETDISCUSSLISTFORPC),discuss);
        Response<List<Discuss>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Discuss>>>(){});
        return response;
    }

    /**
     * 主键查询
     * @param discuss
     * @return
     */
    @Override
    public Response<Discuss> findByPrimaryKey(Discuss discuss) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),discuss);
        Response<Discuss> response = JSON.parseObject(responseXml,new TypeReference<Response<Discuss>>(){});
        return response;
    }

    /**
     *保存
     * @param discuss
     * @return
     */
    @Override
    public String save(Discuss discuss){
        String responseXml = POST(getSendUrl(ActionEnum.THEACHERREPLY),discuss);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    public String theacherReply(Discuss discuss){
        String responseXml = POST(getSendUrl(ActionEnum.THEACHERREPLY),discuss);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/discuss";
    }

}
