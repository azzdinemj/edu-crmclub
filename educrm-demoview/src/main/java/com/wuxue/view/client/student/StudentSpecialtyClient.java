package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentSpecialty;
import com.wuxue.model.StudentSpecialty;
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
public class StudentSpecialtyClient extends StudentBaseClient implements
        IFindClient<StudentSpecialty,Response<List<StudentSpecialty>>>,ISaveClient<StudentSpecialty,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentSpecialty,Response<StudentSpecialty>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentSpecialty";
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
     * @param studentSpecialty
     * @return
     */
    @Override
    public Response<StudentSpecialty> findByPrimaryKey(StudentSpecialty studentSpecialty) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentSpecialty);
        Response<StudentSpecialty> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentSpecialty>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentSpecialty
     * @return
     */
    @Override
    public Response<List<StudentSpecialty>> find(StudentSpecialty studentSpecialty) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentSpecialty);
        Response<List<StudentSpecialty>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentSpecialty>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentSpecialty
     * @return
     */
    @Override
    public String save(StudentSpecialty studentSpecialty){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentSpecialty);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
