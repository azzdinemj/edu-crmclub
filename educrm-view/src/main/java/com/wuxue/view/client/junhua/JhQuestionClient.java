package com.wuxue.view.client.junhua;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
public class JhQuestionClient extends JunhuaBaseClient
        implements IFindClient<JhQuestion,Response<List<JhQuestion>>>,IDeleteClient<JhQuestion,Object>,
        IFindByPrimaryKeyClient<JhQuestion,Response<JhQuestion>>,ISaveClient<JhQuestion,String>{




    /**
     * 删除
     * @param jhQuestion 主键
     * @return
     */
    @Override
    public String delete(JhQuestion jhQuestion) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),jhQuestion);
       // Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param jhQuestion
     * @return
     */
    @Override
    public Response<List<JhQuestion>> find(JhQuestion jhQuestion) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),jhQuestion);
        Response<List<JhQuestion>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<JhQuestion>>>(){});
        return response;
    }

    public Response<List<JhQuestion>> findByUser(JhQuestion jhQuestion) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDBYUSER),jhQuestion);
        Response<List<JhQuestion>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<JhQuestion>>>(){});
        return response;
    }
    /**
     * 主键查询
     * @param jhQuestion
     * @return
     */
    @Override
    public Response<JhQuestion> findByPrimaryKey(JhQuestion jhQuestion) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),jhQuestion);
        Response<JhQuestion> response = JSON.parseObject(responseXml,new TypeReference<Response<JhQuestion>>(){});
        return response;
    }

    /**
     *保存
     * @param jhQuestion
     * @return
     */
    @Override
    public String save(JhQuestion jhQuestion){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),jhQuestion);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/jhQuestion";
    }

}
