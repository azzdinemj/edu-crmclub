package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ClassinfoStudent;
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
public class ClassInfoStudentClient extends ClassInfoBaseClient
        implements IFindClient<ClassinfoStudent,Response<List<ClassinfoStudent>>>,ISaveClient<ClassinfoStudent,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ClassinfoStudent,Response<ClassinfoStudent>> {




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
     * @param classinfoStudent
     * @return
     */
    @Override
    public Response<List<ClassinfoStudent>> find(ClassinfoStudent classinfoStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfoStudent);
        Response<List<ClassinfoStudent>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoStudent>>>(){});
        return response;
    }

    /**
     *保存
     * @param classinfoStudent
     * @return
     */
    @Override
    public String save(ClassinfoStudent classinfoStudent){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),classinfoStudent);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classinfoStudent";
    }

    /**
     * 主键查询
     * @param classinfoStudent
     * @return
     */
    @Override
    public Response<ClassinfoStudent> findByPrimaryKey(ClassinfoStudent classinfoStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfoStudent);
        Response<ClassinfoStudent> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassinfoStudent>>(){});
        return response;
    }

    public String studentReturnClass(ClassinfoStudent classinfoStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.STUDENTRETURNCLASS),classinfoStudent);
//        Response<ClassinfoStudent> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassinfoStudent>>(){});
        return responseXml;
    }
}
