package com.wuxue.view.client.junhua;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.JhExamination;
import com.wuxue.model.JhQuestion;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.JunhuaBaseClient;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JhExaminationClient extends JunhuaBaseClient
        implements IFindClient<JhExamination,Response<List<JhExamination>>>,IDeleteClient<String,Object>,
        IFindByPrimaryKeyClient<JhExamination,Response<JhExamination>>,ISaveClient<JhExamination,String>{




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
     * @param student
     * @return
     */
    @Override
    public Response<List<JhExamination>> find(JhExamination student) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),student);
        Response<List<JhExamination>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<JhExamination>>>(){});
        return response;
    }


    public Response<List<JhQuestion>> statistics(JhQuestion jhQuestion) {
        String responseXml = POST(getSendUrl(ActionEnum.STATISTICS),jhQuestion);
        Response<List<JhQuestion>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<JhQuestion>>>(){});
        return response;
    }
    /**
     * 主键查询
     * @param student
     * @return
     */
    @Override
    public Response<JhExamination> findByPrimaryKey(JhExamination student) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),student);
        Response<JhExamination> response = JSON.parseObject(responseXml,new TypeReference<Response<JhExamination>>(){});
        return response;
    }

    /**
     *保存
     * @param student
     * @return
     */
    @Override
    public String save(JhExamination student){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),student);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/examination";
    }

}
