package com.wuxue.view.client.question;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Questions;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.QuestionsBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题库知识点
 */
@Service
public class QuestionsSubjectClient extends QuestionsBaseClient implements IFindClient<QuestionsSubject,Response>{


    @Override
    protected String getPageName() {
        return "/questionsSubject";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response find(QuestionsSubject questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<List<QuestionsSubject>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<QuestionsSubject>>>(){});
        return response;
    }



}

