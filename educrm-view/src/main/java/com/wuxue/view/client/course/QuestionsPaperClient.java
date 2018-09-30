package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.QuestionsPaper;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class QuestionsPaperClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<QuestionsPaper,Response<QuestionsPaper>>,ISaveClient<QuestionsPaper,String>{

    @Override
    protected String getPageName() {
        return "/questionsPaper";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<PageInfo<QuestionsPaper>> find(QuestionsPaper questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<PageInfo<QuestionsPaper>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<QuestionsPaper>>>(){});
        return response;
    }

    @Override
    public Response<QuestionsPaper> findByPrimaryKey(QuestionsPaper questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<QuestionsPaper> response = JSON.parseObject(responseXml,new TypeReference<Response<QuestionsPaper>>(){});
        return response;
    }

    @Override
    public String save(QuestionsPaper questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }


}
