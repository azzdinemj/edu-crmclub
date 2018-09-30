package com.wuxue.view.client.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentSignupDetails;
import com.wuxue.model.StudentSignupDetails;
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
public class StudentSignupDetailsClient extends StudentBaseClient implements
        IFindClient<StudentSignupDetails,Response<List<StudentSignupDetails>>>,ISaveClient<StudentSignupDetails,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentSignupDetails,Response<StudentSignupDetails>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentSignupDetails";
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
     * @param studentSignupDetails
     * @return
     */
    @Override
    public Response<StudentSignupDetails> findByPrimaryKey(StudentSignupDetails studentSignupDetails) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentSignupDetails);
        Response<StudentSignupDetails> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentSignupDetails>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentSignupDetailsSignup
     * @return
     */
    @Override
    public Response<List<StudentSignupDetails>> find(StudentSignupDetails studentSignupDetailsSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentSignupDetailsSignup);
        Response<List<StudentSignupDetails>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentSignupDetails>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentSignupDetails
     * @return
     */
    @Override
    public String save(StudentSignupDetails studentSignupDetails){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentSignupDetails);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
}
