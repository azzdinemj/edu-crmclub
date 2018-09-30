package com.wuxue.view.client.question;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsItem;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsItemClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsItem,Response<QuestionsItem>>,ISaveClient<QuestionsItem,String>{

    @Override
    protected String getPageName() {
        return "/questionsItem";
    }

    /**
     * 查询
     * @param questionsItem
     * @return
     */
    public Response<PageInfo<QuestionsItem>> find(QuestionsItem questionsItem) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questionsItem);
        Response<PageInfo<QuestionsItem>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<QuestionsItem>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsItem> findByPrimaryKey(QuestionsItem questionsItem) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questionsItem);
        Response<QuestionsItem> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsItem>>(){});
        return response;
    }

    @Override
    public String save(QuestionsItem questionsItem ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questionsItem);
        return responseXml;
    }


}
