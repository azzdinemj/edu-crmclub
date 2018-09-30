package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsSubjectClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsSubject,Response<QuestionsSubject>>,ISaveClient<QuestionsSubject,String>{

    @Override
    protected String getPageName() {
        return "/questionsSubject";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<List<QuestionsSubject>> find(QuestionsSubject questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<List<QuestionsSubject>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<QuestionsSubject>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsSubject> findByPrimaryKey(QuestionsSubject questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<QuestionsSubject> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsSubject>>(){});
        return response;
    }

    @Override
    public String save(QuestionsSubject questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }

}
