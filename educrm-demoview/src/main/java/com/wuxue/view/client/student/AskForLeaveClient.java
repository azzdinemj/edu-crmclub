package com.wuxue.view.client.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.AskForLeave;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请假（老师 、学生）
 */
@Service
public class AskForLeaveClient extends StudentBaseClient implements IFindClient<AskForLeave,Response<List<AskForLeave>>>,
        IFindByPrimaryKeyClient<AskForLeave,Response<AskForLeave>>,ISaveClient<AskForLeave,String>{

    @Override
    protected String getPageName() {
        return "/askForLeave";
    }

    /**
     * 查询列表
     * @param askForLeave
     * @return
     */
    @Override
    public Response<List<AskForLeave>> find(AskForLeave askForLeave) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),askForLeave);
        Response<List<AskForLeave>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<AskForLeave>>>(){});
        return response;

    }

    @Override
    public Response<AskForLeave> findByPrimaryKey(AskForLeave questions) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),questions);
        Response<AskForLeave> response = JSON.parseObject(responseXml,new TypeReference<Response<AskForLeave>>(){});
        return response;
    }

    @Override
    public String save(AskForLeave questions ) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),questions);
        return responseXml;
    }


}
