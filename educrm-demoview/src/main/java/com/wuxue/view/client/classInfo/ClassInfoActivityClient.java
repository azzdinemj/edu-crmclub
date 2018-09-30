package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ClassinfoActivity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ClassInfoBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoActivityClient extends ClassInfoBaseClient
        implements IFindClient<ClassinfoActivity,Response<List<ClassinfoActivity>>>,ISaveClient<ClassinfoActivity,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ClassinfoActivity,Response<ClassinfoActivity>>,
        ISubmitClient<ClassinfoActivity,String>,IAuditClient<ClassinfoActivity,String>{




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param classinfoActivity
     * @return
     */
    @Override
    public Response<List<ClassinfoActivity>> find(ClassinfoActivity classinfoActivity) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfoActivity);
        Response<List<ClassinfoActivity>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoActivity>>>(){});
        return response;
    }

    /**
     *保存
     * @param classinfoActivity
     * @return
     */
    @Override
    public String save(ClassinfoActivity classinfoActivity){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),classinfoActivity);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classinfoActivity";
    }

    /**
     * 主键查询
     * @param classinfoActivity
     * @return
     */
    @Override
    public Response<ClassinfoActivity> findByPrimaryKey(ClassinfoActivity classinfoActivity) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfoActivity);
        Response<ClassinfoActivity> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassinfoActivity>>(){});
        return response;
    }

    @Override
    public String audit(ClassinfoActivity classinfoActivity) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),classinfoActivity);
        return responseXml;
    }

    @Override
    public String submit(ClassinfoActivity classinfoActivity) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),classinfoActivity);
        return responseXml;
    }
}
