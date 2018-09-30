package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Questions;
import com.wuxue.model.QuestionsOption;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IAuditClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsOptionClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsOption,Response<QuestionsOption>>,ISaveClient<QuestionsOption,String>{

    @Override
    protected String getPageName() {
        return "/questionsOption";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<List<QuestionsOption>> find(QuestionsOption questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<List<QuestionsOption>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<QuestionsOption>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsOption> findByPrimaryKey(QuestionsOption questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<QuestionsOption> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsOption>>(){});
        return response;
    }

    @Override
    public String save(QuestionsOption questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }


}
