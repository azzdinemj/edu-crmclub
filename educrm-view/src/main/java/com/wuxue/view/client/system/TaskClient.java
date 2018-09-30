package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Task;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskClient extends SystemBaseClient implements
        IFindClient<Task,Response<List<Task>>>,ISaveClient<Task,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Task,Response<Task>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/task";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(String s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param task
     * @return
     */
    @Override
    public Response<Task> findByPrimaryKey(Task task) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),task);
        Response<Task> response = JSON.parseObject(responseXml,new TypeReference<Response<Task>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param task
     * @return
     */
    @Override
    public Response<List<Task>> find(Task task) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),task);
        Response<List<Task>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Task>>>(){});
        return response;

    }

    /**
     *保存
     * @param task
     * @return
     */
    @Override
    public String save(Task task){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),task);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
