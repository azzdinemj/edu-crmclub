package com.wuxue.view.client.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentActivityExp;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentActivityExpClient extends StudentBaseClient implements
        IFindClient<StudentActivityExp,Response<List<StudentActivityExp>>>,ISaveClient<StudentActivityExp,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentActivityExp,Response<StudentActivityExp>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentActivityExp";
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
     * @param studentActivityExp
     * @return
     */
    @Override
    public Response<StudentActivityExp> findByPrimaryKey(StudentActivityExp studentActivityExp) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentActivityExp);
        Response<StudentActivityExp> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentActivityExp>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentActivityExpSignup
     * @return
     */
    @Override
    public Response<List<StudentActivityExp>> find(StudentActivityExp studentActivityExpSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentActivityExpSignup);
        Response<List<StudentActivityExp>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentActivityExp>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentActivityExp
     * @return
     */
    @Override
    public String save(StudentActivityExp studentActivityExp){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentActivityExp);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
}
