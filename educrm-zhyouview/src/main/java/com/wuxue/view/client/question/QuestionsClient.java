package com.wuxue.view.client.question;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Questions;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.QuestionsBaseClient;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class QuestionsClient extends QuestionsBaseClient implements
       IFindByPrimaryKeyClient<String,Response>,IFindClient<Questions,Response>{


    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/questions"; }


    @Override
    public Response  findByPrimaryKey(String s ) {
        String responseXml = POST(getSendUrl(ActionEnum.GETSTUDENT),s);
        Response<Student> response = JSON.parseObject(responseXml, new TypeReference<Response<Student>>(){});
        return response;
    }


    @Override
    public Response find(Questions questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<PageInfo<Questions>> response = JSON.parseObject(responseXml, new TypeReference<Response<PageInfo<Questions>>>(){});
        return response;
    }

}

