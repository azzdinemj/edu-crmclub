package com.wuxue.view.client.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.wuxue.model.Student;
import com.wuxue.model.StudentAssign;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

@Service
public class StudentClient extends StudentBaseClient implements
        ISaveClient<Student,String>,IFindByPrimaryKeyClient<String,Response>,IFindClient<Student,Response> {

    /**
     * 登录
     * @param student
     * @return
     */
    public Response<Student> login(Student student) {
        String responseXml = POST(getSendUrl(ActionEnum.STUDENTLOGIN),student);
        Response<Student> response = JSON.parseObject(responseXml,new TypeReference<Response<Student>>(){});
        return response;
    }

    /**
     * 注册
     * @param student
     * @return
     */
    public Response register(Student student) {
        String responseXml = POST(getSendUrl(ActionEnum.REGISTER),student);
        Response response = JSON.parseObject(responseXml, new TypeReference<Response>(){});
        return response;
    }

    /**
     * 修改密码
     * @param student
     * @return
     */
    public Response updatepassword(Student student){
        String responseXml = POST(getSendUrl(ActionEnum.UPDATEPASSWORD),student);
        Response response = JSON.parseObject(responseXml, new TypeReference<Response>(){});
        return response;
    }



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

}

