package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentInterview;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewClient extends StudentBaseClient
        implements IFindClient<StudentInterview,Response<List<StudentInterview>>>,IDeleteClient<String,Object>,
        IFindByPrimaryKeyClient<StudentInterview,Response<StudentInterview>>,ISaveClient<StudentInterview,String>,
        ISubmitClient<StudentInterview,String>,IAuditClient<StudentInterview,String>{




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
       // Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param student
     * @return
     */
    @Override
    public Response<List<StudentInterview>> find(StudentInterview student) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),student);
        Response<List<StudentInterview>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentInterview>>>(){});
        return response;
    }
    /**
     * 主键查询
     * @param student
     * @return
     */
    @Override
    public Response<StudentInterview> findByPrimaryKey(StudentInterview student) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),student);
        Response<StudentInterview> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentInterview>>(){});
        return response;
    }

    /**
     *保存
     * @param student
     * @return
     */
    @Override
    public String save(StudentInterview student){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),student);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentInterview";
    }


    @Override
    public String audit(StudentInterview studentInterview) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),studentInterview);
        return responseXml;
    }

    @Override
    public String submit(StudentInterview studentInterview) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),studentInterview);
        return responseXml;
    }
}
