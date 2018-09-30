package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentInterviewRecords;
import com.wuxue.model.StudentInterviewRecords;
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
public class StudentInterviewRecordsClient extends SystemBaseClient implements
        IFindClient<StudentInterviewRecords,Response<List<StudentInterviewRecords>>>,ISaveClient<StudentInterviewRecords,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentInterviewRecords,Response<StudentInterviewRecords>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentInterviewRecords";
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
     * @param studentInterviewRecords
     * @return
     */
    @Override
    public Response<StudentInterviewRecords> findByPrimaryKey(StudentInterviewRecords studentInterviewRecords) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentInterviewRecords);
        Response<StudentInterviewRecords> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentInterviewRecords>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentInterviewRecords
     * @return
     */
    @Override
    public Response<List<StudentInterviewRecords>> find(StudentInterviewRecords studentInterviewRecords) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentInterviewRecords);
        Response<List<StudentInterviewRecords>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentInterviewRecords>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentInterviewRecords
     * @return
     */
    @Override
    public String save(StudentInterviewRecords studentInterviewRecords){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentInterviewRecords);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
