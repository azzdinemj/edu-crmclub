package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SchoolBusStudent;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolBusStudentClient extends SystemBaseClient implements
        IFindClient<SchoolBusStudent,Response<List<SchoolBusStudent>>>,ISaveClient<SchoolBusStudent,String>,
        IDeleteClient<SchoolBusStudent,Object>,IFindByPrimaryKeyClient<SchoolBusStudent,Response<SchoolBusStudent>>
        ,IAuditClient<SchoolBusStudent,String>,ISubmitClient<SchoolBusStudent,String> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/schoolbusstudent";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(SchoolBusStudent s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param schoolBusStudent
     * @return
     */
    @Override
    public Response<SchoolBusStudent> findByPrimaryKey(SchoolBusStudent schoolBusStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),schoolBusStudent);
        Response<SchoolBusStudent> response = JSON.parseObject(responseXml,new TypeReference<Response<SchoolBusStudent>>(){});
        return response;
    }



    /**
     * 查询列表
     * @param schoolBusStudentSignup
     * @return
     */
    @Override
    public Response<List<SchoolBusStudent>> find(SchoolBusStudent schoolBusStudentSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),schoolBusStudentSignup);
        Response<List<SchoolBusStudent>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SchoolBusStudent>>>(){});
        return response;

    }





    /**
     *保存
     * @param schoolBusStudent
     * @return
     */
    @Override
    public String save(SchoolBusStudent schoolBusStudent){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),schoolBusStudent);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }



    @Override
    public String audit(SchoolBusStudent schoolBusStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),schoolBusStudent);

        return responseXml;
    }

    @Override
    public String submit(SchoolBusStudent schoolBusStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),schoolBusStudent);
        return responseXml;
    }
}
