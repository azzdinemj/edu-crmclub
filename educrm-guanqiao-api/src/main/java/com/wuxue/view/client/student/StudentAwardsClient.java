package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentAwards;
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
public class StudentAwardsClient extends StudentBaseClient implements
        IFindClient<StudentAwards,Response<List<StudentAwards>>>,ISaveClient<StudentAwards,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentAwards,Response<StudentAwards>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentAwards";
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
     * @param studentAwards
     * @return
     */
    @Override
    public Response<StudentAwards> findByPrimaryKey(StudentAwards studentAwards) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentAwards);
        Response<StudentAwards> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentAwards>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentAwards
     * @return
     */
    @Override
    public Response<List<StudentAwards>> find(StudentAwards studentAwards) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentAwards);
        Response<List<StudentAwards>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentAwards>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentAwards
     * @return
     */
    @Override
    public String save(StudentAwards studentAwards){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentAwards);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
