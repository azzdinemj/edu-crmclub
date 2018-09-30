package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsPaperItem;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class QuestionsPaperItemClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsPaperItem,Response<QuestionsPaperItem>>,ISaveClient<QuestionsPaperItem,String>{

    @Override
    protected String getPageName() {
        return "/questionsPaperItem";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<PageInfo<QuestionsPaperItem>> find(QuestionsPaperItem questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<PageInfo<QuestionsPaperItem>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<QuestionsPaperItem>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsPaperItem> findByPrimaryKey(QuestionsPaperItem questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<QuestionsPaperItem> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsPaperItem>>(){});
        return response;
    }

    @Override
    public String save(QuestionsPaperItem questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }


}
