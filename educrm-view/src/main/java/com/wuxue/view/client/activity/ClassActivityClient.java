package com.wuxue.view.client.activity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.junhwa.ClassActivity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ActivityBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassActivityClient extends ActivityBaseClient
        implements IFindClient<ClassActivity,Response<List<ClassActivity>>>,ISaveClient<ClassActivity,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ClassActivity,Response<ClassActivity>>{




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
     * @param classActivity
     * @return
     */
    @Override
    public Response<List<ClassActivity>> find(ClassActivity classActivity) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDLIST),classActivity);
        Response<List<ClassActivity>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassActivity>>>(){});
        return response;
    }

    /**
     *保存
     * @param classActivity
     * @return
     */
    @Override
    public String save(ClassActivity classActivity){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEORUPDATE),classActivity);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classActivity";
    }

    /**
     * 主键查询
     * @param classActivityId
     * @return
     */
    public Response<ClassActivity> findClassActivityInfoById(ClassActivity  classActivityId) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDINFO),classActivityId);
        Response<ClassActivity> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassActivity>>(){});
        return response;
    }
    @Override
    public Response<ClassActivity> findByPrimaryKey(ClassActivity  classActivityId) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDINFO),classActivityId);
        Response<ClassActivity> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassActivity>>(){});
        return response;
    }
}
