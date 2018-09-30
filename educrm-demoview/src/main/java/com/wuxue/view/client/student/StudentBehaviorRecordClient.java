package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentBehaviorRecord;
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
public class StudentBehaviorRecordClient extends StudentBaseClient implements
        IFindClient<StudentBehaviorRecord,Response<List<StudentBehaviorRecord>>>,ISaveClient<StudentBehaviorRecord,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentBehaviorRecord,Response<StudentBehaviorRecord>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentBehaviorRecord";
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
     * @param studentBehaviorRecord
     * @return
     */
    @Override
    public Response<StudentBehaviorRecord> findByPrimaryKey(StudentBehaviorRecord studentBehaviorRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentBehaviorRecord);
        Response<StudentBehaviorRecord> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentBehaviorRecord>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentBehaviorRecord
     * @return
     */
    @Override
    public Response<List<StudentBehaviorRecord>> find(StudentBehaviorRecord studentBehaviorRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentBehaviorRecord);
        Response<List<StudentBehaviorRecord>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentBehaviorRecord>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentBehaviorRecord
     * @return
     */
    @Override
    public String save(StudentBehaviorRecord studentBehaviorRecord){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentBehaviorRecord);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
