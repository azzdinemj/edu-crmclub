package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsPaperData;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class QuestionsPaperDataClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsPaperData,Response<QuestionsPaperData>>,ISaveClient<QuestionsPaperData,String>{

    @Override
    protected String getPageName() {
        return "/questionsPaperData";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<PageInfo<QuestionsPaperData>> find(QuestionsPaperData questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<PageInfo<QuestionsPaperData>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<QuestionsPaperData>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsPaperData> findByPrimaryKey(QuestionsPaperData questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<QuestionsPaperData> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsPaperData>>(){});
        return response;
    }

    @Override
    public String save(QuestionsPaperData questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }


}
