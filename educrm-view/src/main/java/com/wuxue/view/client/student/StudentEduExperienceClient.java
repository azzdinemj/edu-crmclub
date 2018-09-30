package com.wuxue.view.client.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentEduExperience;
import com.wuxue.model.StudentEduExperience;
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
public class StudentEduExperienceClient extends StudentBaseClient implements
        IFindClient<StudentEduExperience,Response<List<StudentEduExperience>>>,ISaveClient<StudentEduExperience,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentEduExperience,Response<StudentEduExperience>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentEduExperience";
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
     * @param studentEduExperience
     * @return
     */
    @Override
    public Response<StudentEduExperience> findByPrimaryKey(StudentEduExperience studentEduExperience) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentEduExperience);
        Response<StudentEduExperience> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentEduExperience>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentEduExperienceSignup
     * @return
     */
    @Override
    public Response<List<StudentEduExperience>> find(StudentEduExperience studentEduExperienceSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentEduExperienceSignup);
        Response<List<StudentEduExperience>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentEduExperience>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentEduExperience
     * @return
     */
    @Override
    public String save(StudentEduExperience studentEduExperience){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentEduExperience);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
}
