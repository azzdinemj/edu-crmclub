package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentScores;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ClassInfoBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentScoresClient extends ClassInfoBaseClient
        implements IFindClient<StudentScores,Response<List<StudentScores>>>,ISaveClient<StudentScores,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentScores,Response<StudentScores>>{




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
     * @param studentScores
     * @return
     */
    @Override
    public Response<List<StudentScores>> find(StudentScores studentScores) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentScores);
        Response<List<StudentScores>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentScores>>>(){});
        return response;
    }

    /**
     *保存
     * @param studentScores
     * @return
     */
    @Override
    public String save(StudentScores studentScores){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentScores);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentScores";
    }

    /**
     * 主键查询
     * @param studentScores
     * @return
     */
    @Override
    public Response<StudentScores> findByPrimaryKey(StudentScores studentScores) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentScores);
        Response<StudentScores> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentScores>>(){});
        return response;
    }

    public String saveAll(List<StudentScores> studentScores){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEALL),studentScores);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
}
