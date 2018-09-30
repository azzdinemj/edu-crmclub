package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ActivityStudent;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ClassInfoBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityStudentClient extends ClassInfoBaseClient
        implements IFindClient<ActivityStudent,Response<List<ActivityStudent>>>,ISaveClient<ActivityStudent,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ActivityStudent,Response<ActivityStudent>>{




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
     * @param activityStudent
     * @return
     */
    @Override
    public Response<List<ActivityStudent>> find(ActivityStudent activityStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),activityStudent);
        Response<List<ActivityStudent>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ActivityStudent>>>(){});
        return response;
    }

    /**
     *保存
     * @param activityStudent
     * @return
     */
    @Override
    public String save(ActivityStudent activityStudent){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),activityStudent);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/activityStudent";
    }

    /**
     * 主键查询
     * @param activityStudent
     * @return
     */
    @Override
    public Response<ActivityStudent> findByPrimaryKey(ActivityStudent activityStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),activityStudent);
        Response<ActivityStudent> response = JSON.parseObject(responseXml,new TypeReference<Response<ActivityStudent>>(){});
        return response;
    }
}
