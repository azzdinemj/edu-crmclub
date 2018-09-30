package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentSignup;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSignupClient extends StudentBaseClient implements
        IFindClient<StudentSignup,Response<List<StudentSignup>>>,ISaveClient<StudentSignup,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentSignup,Response<StudentSignup>> ,
        ISubmitClient<StudentSignup,String>,IAuditClient<StudentSignup,String>{


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentSignup";
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
     * @param studentSignup
     * @return
     */
    @Override
    public Response<StudentSignup> findByPrimaryKey(StudentSignup studentSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentSignup);
        Response<StudentSignup> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentSignup>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentSignup
     * @return
     */
    @Override
    public Response<List<StudentSignup>> find(StudentSignup studentSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentSignup);
        Response<List<StudentSignup>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentSignup>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentSignup
     * @return
     */
    @Override
    public String save(StudentSignup studentSignup){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentSignup);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }


    public Response<List<StudentSignup>> getNotPlacement(StudentSignup studentSignup){
        String responseXml = POST(getSendUrl(ActionEnum.GETNOTPLACEMENT),studentSignup);
        Response<List<StudentSignup>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentSignup>>>(){});
        return response;
    }

    @Override
    public String submit(StudentSignup studentSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),studentSignup);
        return responseXml;
    }

    @Override
    public String audit(StudentSignup studentSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),studentSignup);
        return responseXml;
    }

    public String updateStatus(StudentSignup studentSignup){
        String responseXml = POST(getSendUrl(ActionEnum.UPDATESTATUS),studentSignup);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
