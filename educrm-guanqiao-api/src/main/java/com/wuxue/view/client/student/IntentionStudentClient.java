package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentSignup;
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
public class IntentionStudentClient extends StudentBaseClient
        implements IFindClient<StudentSignup,Response<List<StudentSignup>>>,IDeleteClient<String,Object>,
        IFindByPrimaryKeyClient<StudentSignup,Response<StudentSignup>>,ISaveClient<StudentSignup,String> {




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param student
     * @return
     */
    @Override
    public Response<List<StudentSignup>> find(StudentSignup student) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),student);
        Response<List<StudentSignup>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentSignup>>>(){});
        return response;
    }

    /**
     *保存
     * @param student
     * @return
     */
    @Override
    public String save(StudentSignup student){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),student);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 主键查询
     * @param student
     * @return
     */

    public Response<StudentSignup> findByPrimaryKey(StudentSignup student) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),student);
        Response<StudentSignup> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentSignup>>(){});
        return response;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentInterview";
    }


}
