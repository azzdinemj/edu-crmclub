package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.QuestionsProject;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsProjectClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsProject,Response<QuestionsProject>>,ISaveClient<QuestionsProject,String>{

    @Override
    protected String getPageName() {
        return "/questionsProject";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<List<QuestionsProject>> find(QuestionsProject questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<List<QuestionsProject>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<QuestionsProject>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsProject> findByPrimaryKey(QuestionsProject questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<QuestionsProject> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsProject>>(){});
        return response;
    }

    @Override
    public String save(QuestionsProject questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }


}
