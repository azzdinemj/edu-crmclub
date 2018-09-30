package com.wuxue.view.client.junhua;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.JhAnswer;
import com.wuxue.model.JhQuestion;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.JunhuaBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JhAnswerClient extends JunhuaBaseClient
        implements IFindClient<JhAnswer,Response<List<JhAnswer>>>,IDeleteClient<String,Object>,
        IFindByPrimaryKeyClient<JhAnswer,Response<JhAnswer>>,ISaveClient<JhAnswer,String>{




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
       // Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param jhAnswer
     * @return
     */
    @Override
    public Response<List<JhAnswer>> find(JhAnswer jhAnswer) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),jhAnswer);
        Response<List<JhAnswer>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<JhAnswer>>>(){});
        return response;
    }

    public Response<List<JhAnswer>> queryAnswerList(JhAnswer jhAnswer) {
        String responseXml = POST(getSendUrl(ActionEnum.QUERYANSWERLIST),jhAnswer);
        Response<List<JhAnswer>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<JhAnswer>>>(){});
        return response;
    }

    public Response<List<JhQuestion>> statistics(JhQuestion jhAnswer) {
        String responseXml = POST(getSendUrl(ActionEnum.STATISTICS),jhAnswer);
        Response<List<JhQuestion>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<JhQuestion>>>(){});
        return response;
    }
    /**
     * 主键查询
     * @param jhAnswer
     * @return
     */
    @Override
    public Response<JhAnswer> findByPrimaryKey(JhAnswer jhAnswer) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),jhAnswer);
        Response<JhAnswer> response = JSON.parseObject(responseXml,new TypeReference<Response<JhAnswer>>(){});
        return response;
    }

    /**
     *保存
     * @param jhAnswer
     * @return
     */
    @Override
    public String save(JhAnswer jhAnswer){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),jhAnswer);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/answer";
    }

}
