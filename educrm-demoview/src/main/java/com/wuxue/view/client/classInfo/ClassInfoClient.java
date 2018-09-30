package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ClassInfoBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoClient extends ClassInfoBaseClient
        implements IFindClient<Classinfo,Response<List<Classinfo>>>,ISaveClient<Classinfo,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Classinfo,Response<Classinfo>>,
        ISubmitClient<Classinfo,String>,IAuditClient<Classinfo,String>{




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
     * @param classinfo
     * @return
     */
    @Override
    public Response<List<Classinfo>> find(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfo);
        Response<List<Classinfo>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Classinfo>>>(){});
        return response;
    }

    /**
     *保存
     * @param classinfo
     * @return
     */
    @Override
    public String save(Classinfo classinfo){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),classinfo);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classinfo";
    }

    /**
     * 主键查询
     * @param classinfo
     * @return
     */
    @Override
    public Response<Classinfo> findByPrimaryKey(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfo);
        Response<Classinfo> response = JSON.parseObject(responseXml,new TypeReference<Response<Classinfo>>(){});
        return response;
    }

    @Override
    public String audit(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),classinfo);
        return responseXml;
    }

    @Override
    public String submit(Classinfo classinfo) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),classinfo);
        return responseXml;
    }

    public Response<List<ClassinfoStudent>> findClasssStudentScores(ClassinfoStudent classinfoStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDCLASSSTUDENTSCORES),classinfoStudent);
        Response<List<ClassinfoStudent>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoStudent>>>(){});
        return response;
    }
}
