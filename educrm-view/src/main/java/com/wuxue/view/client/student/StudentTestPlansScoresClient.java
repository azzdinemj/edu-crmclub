package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentTestPlansScores;
import com.wuxue.model.StudentTestPlansScores;
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
public class StudentTestPlansScoresClient extends SystemBaseClient implements
        IFindClient<StudentTestPlansScores,Response<List<StudentTestPlansScores>>>,ISaveClient<StudentTestPlansScores,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentTestPlansScores,Response<StudentTestPlansScores>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentTestPlansScores";
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
     * @param studentTestPlansScores
     * @return
     */
    @Override
    public Response<StudentTestPlansScores> findByPrimaryKey(StudentTestPlansScores studentTestPlansScores) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentTestPlansScores);
        Response<StudentTestPlansScores> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentTestPlansScores>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentTestPlansScores
     * @return
     */
    @Override
    public Response<List<StudentTestPlansScores>> find(StudentTestPlansScores studentTestPlansScores) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentTestPlansScores);
        Response<List<StudentTestPlansScores>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentTestPlansScores>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentTestPlansScores
     * @return
     */
    @Override
    public String save(StudentTestPlansScores studentTestPlansScores){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentTestPlansScores);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
