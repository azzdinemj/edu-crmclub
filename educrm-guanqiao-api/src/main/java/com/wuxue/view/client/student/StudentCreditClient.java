package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentCredit;
import com.wuxue.model.StudentCredit;
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
public class StudentCreditClient extends StudentBaseClient implements
        IFindClient<StudentCredit,Response<List<StudentCredit>>>,ISaveClient<StudentCredit,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentCredit,Response<StudentCredit>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentCredit";
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
     * @param studentCredit
     * @return
     */
    @Override
    public Response<StudentCredit> findByPrimaryKey(StudentCredit studentCredit) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentCredit);
        Response<StudentCredit> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentCredit>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentCredit
     * @return
     */
    @Override
    public Response<List<StudentCredit>> find(StudentCredit studentCredit) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentCredit);
        Response<List<StudentCredit>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentCredit>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentCredit
     * @return
     */
    @Override
    public String save(StudentCredit studentCredit){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentCredit);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
