package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ClassinfoEmployee;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ClassInfoBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class ClassInfoEmployeeClient extends ClassInfoBaseClient
        implements IFindClient<ClassinfoEmployee,Response<List<ClassinfoEmployee>>>,ISaveClient<ClassinfoEmployee,String>,
        IDeleteClient<ClassinfoEmployee,Object>,IFindByPrimaryKeyClient<ClassinfoEmployee,Response<ClassinfoEmployee>> {




    /**
     * 删除
     * @return
     */
    @Override
    public String delete(ClassinfoEmployee classinfoEmployee) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),classinfoEmployee);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param classinfoEmployee
     * @return
     */
    @Override
    public Response<List<ClassinfoEmployee>> find(ClassinfoEmployee classinfoEmployee) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfoEmployee);
        Response<List<ClassinfoEmployee>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoEmployee>>>(){});
        return response;
    }

    /**
     *      班级老师
     * @param classinfoEmployee
     * @return
     */

    public Response<List<ClassinfoEmployee>> findTeacher(ClassinfoEmployee classinfoEmployee) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDTEACHER),classinfoEmployee);
        Response<List<ClassinfoEmployee>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoEmployee>>>(){});
        return response;
    }

    public Response<List<ClassinfoEmployee>> findClassinfoTeacher(ClassinfoEmployee classinfoEmployee) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDCLASSINFOTEACHER),classinfoEmployee);
        Response<List<ClassinfoEmployee>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassinfoEmployee>>>(){});
        return response;
    }

    /**
     *保存
     * @param classinfoEmployee
     * @return
     */
    @Override
    public String save(ClassinfoEmployee classinfoEmployee){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),classinfoEmployee);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    public String saveAll(ClassinfoEmployee classinfoEmployee){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEALL),classinfoEmployee);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }
    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classinfoEmployee";
    }

    /**
     * 主键查询
     * @param classinfoEmployee
     * @return
     */
    @Override
    public Response<ClassinfoEmployee> findByPrimaryKey(ClassinfoEmployee classinfoEmployee) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classinfoEmployee);
        Response<ClassinfoEmployee> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassinfoEmployee>>(){});
        return response;
    }

}
