package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsTest;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class QuestionsTestClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsTest,Response<QuestionsTest>>,ISaveClient<QuestionsTest,String>{

    @Override
    protected String getPageName() {
        return "/questionsTest";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<PageInfo<QuestionsTest>> find(QuestionsTest questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<PageInfo<QuestionsTest>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<QuestionsTest>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsTest> findByPrimaryKey(QuestionsTest questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<QuestionsTest> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsTest>>(){});
        return response;
    }

    @Override
    public String save(QuestionsTest questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }


}
