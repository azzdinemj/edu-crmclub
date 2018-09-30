package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Questions;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IAuditClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class QuestionsClient extends CourseBaseClient implements
        IFindByPrimaryKeyClient<Questions,Response<Questions>>,ISaveClient<Questions,String>,
        IAuditClient<Questions,String> {

    @Override
    protected String getPageName() {
        return "/questions";
    }

    /**
     * 查询
     * @param questions
     * @return
     */
    public Response<PageInfo<Questions>> find(Questions questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<PageInfo<Questions>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<Questions>>>(){});
        return response;
    }

    @Override
    public Response<Questions> findByPrimaryKey(Questions questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<Questions> response = JSON.parseObject(responseXml,new TypeReference<Response<Questions>>(){});
        return response;
    }

    @Override
    public String save(Questions questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }

    /**
     * 创建审核对象
     * @param questions
     * @return
     */
    @Override
    public String audit(Questions questions) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),questions);
        return responseXml;
    }
}
