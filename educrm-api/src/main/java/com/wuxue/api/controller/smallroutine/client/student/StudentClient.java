package com.wuxue.api.controller.smallroutine.client.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.controller.smallroutine.client.StudentBaseClient;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class StudentClient extends StudentBaseClient implements
        ISaveClient<Student,String>,IFindByPrimaryKeyClient<String,Response>,IFindClient<Student,Response> {


    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/student"; }


    @Override
    public Response  findByPrimaryKey(String s ) {
        String responseXml = POST(getSendUrl(ActionEnum.GETSTUDENT),s);
        Response<Student> response = JSON.parseObject(responseXml, new TypeReference<Response<Student>>(){});
        return response;
    }

    /**
     * 修改
     * @param student
     * @return
     */
    @Override
    public String save(Student student) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),student);
       // Response<Student> response = JSON.parseObject(responseXml, new TypeReference<Response<Student>>(){});
        return responseXml;
    }


    @Override
    public Response find(Student student) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),student);
        Response<Student> response = JSON.parseObject(responseXml, new TypeReference<Response<Student>>(){});
        return response;
    }


    //根据电话查找学生
    public Response findByphone(String string) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDBYPHONE),string);
        Response<Student> response = JSON.parseObject(responseXml, new TypeReference<Response<Student>>(){});
        return response;
    }


    //主键查询 jiedian
    public Response findjiedian(Student student){
        String responseXml = POST(getSendUrl(ActionEnum.FINDJIEDIAN),student);
        Response<Student> response = JSON.parseObject(responseXml,new TypeReference<Response<Student>>(){});
        return response;
    }

}

