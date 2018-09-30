package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentPlans;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPlansClient extends StudentBaseClient implements
        IFindClient<StudentPlans,Response<List<StudentPlans>>>,ISaveClient<StudentPlans,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentPlans,Response<StudentPlans>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentPlans";
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
     * @param studentPlans
     * @return
     */
    @Override
    public Response<StudentPlans> findByPrimaryKey(StudentPlans studentPlans) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentPlans);
        Response<StudentPlans> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentPlans>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentPlans
     * @return
     */
    @Override
    public Response<List<StudentPlans>> find(StudentPlans studentPlans) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentPlans);
        Response<List<StudentPlans>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentPlans>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentPlans
     * @return
     */
    @Override
    public String save(StudentPlans studentPlans){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentPlans);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
