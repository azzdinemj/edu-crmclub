package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsPaperType;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class QuestionsPaperTypeClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsPaperType,Response<QuestionsPaperType>>,ISaveClient<QuestionsPaperType,String>{

    @Override
    protected String getPageName() {
        return "/questionsPaperType";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<PageInfo<QuestionsPaperType>> find(QuestionsPaperType questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<PageInfo<QuestionsPaperType>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<QuestionsPaperType>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsPaperType> findByPrimaryKey(QuestionsPaperType questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<QuestionsPaperType> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsPaperType>>(){});
        return response;
    }

    @Override
    public String save(QuestionsPaperType questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }


}
