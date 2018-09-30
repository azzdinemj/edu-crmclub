package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentInterviewRecord;
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
public class StudentInterviewRecordClient extends StudentBaseClient implements
        IFindClient<StudentInterviewRecord,Response<List<StudentInterviewRecord>>>,ISaveClient<StudentInterviewRecord,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentInterviewRecord,Response<StudentInterviewRecord>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentInterviewRecord";
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
     * @param studentInterviewRecord
     * @return
     */
    @Override
    public Response<StudentInterviewRecord> findByPrimaryKey(StudentInterviewRecord studentInterviewRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentInterviewRecord);
        Response<StudentInterviewRecord> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentInterviewRecord>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentInterviewRecord
     * @return
     */
    @Override
    public Response<List<StudentInterviewRecord>> find(StudentInterviewRecord studentInterviewRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentInterviewRecord);
        Response<List<StudentInterviewRecord>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentInterviewRecord>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentInterviewRecord
     * @return
     */
    @Override
    public String save(StudentInterviewRecord studentInterviewRecord){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentInterviewRecord);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
