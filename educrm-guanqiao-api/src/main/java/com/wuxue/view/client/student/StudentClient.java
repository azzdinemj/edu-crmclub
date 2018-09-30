package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Course;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClient extends StudentBaseClient implements
        IFindClient<Student,Response<List<Student>>>,ISaveClient<Student,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Student,Response<Student>>
         {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/student";
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
     * @param student
     * @return
     */
    @Override
    public Response<Student> findByPrimaryKey(Student student) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),student);
        Response<Student> response = JSON.parseObject(responseXml,new TypeReference<Response<Student>>(){});
        return response;
    }

    /**
     * 根据主键查询详情  中航
     * @param str
     * @return
     */
    public Response findByPrimarypk(String  str) {
        String responseXml = POST(getSendUrl(ActionEnum.GETSTUDENT),str);
        Response<Student> response = JSON.parseObject(responseXml,new TypeReference<Response<Student>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentSignup
     * @return
     */
    @Override
    public Response<List<Student>> find(Student studentSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentSignup);
        Response<List<Student>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Student>>>(){});
        return response;

    }

    public Response<List<Student>> findEmployeeStudent(Student studentSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDEMPLOYEESTUDENT),studentSignup);
        Response<List<Student>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Student>>>(){});
        return response;

    }


    /**
     * 查询学生列表 中航油
     * @param student
     * @return
     */
    public Response<PageInfo<Student>> findStudent(Student student) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDZHYOU),student);
        Response<PageInfo<Student>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<Student>>>(){});
        return response;
    }

    /**
     *保存
     * @param student
     * @return
     */
    @Override
    public String save(Student student){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),student);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     *后台新增中航学生
     * @param student
     * @return
     */
    public String saveStudentzhy(Student student){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEZHYOU),student);
        return responseXml;
    }



    /**
     * 冠桥学生登录
     * @param student
     * @return
     */
    public Response<Student> login(Student student) {
        String responseXml = POST(getSendUrl(ActionEnum.STUDENTLOGIN),student);
        Response<Student> response = JSON.parseObject(responseXml,new TypeReference<Response<Student>>(){});
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


}
