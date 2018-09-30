package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.DormRoomStudent;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormRoomStudentClient extends SystemBaseClient implements
        IFindClient<DormRoomStudent,Response<List<DormRoomStudent>>>,ISaveClient<DormRoomStudent,String>,
        IDeleteClient<DormRoomStudent,Object>,IFindByPrimaryKeyClient<DormRoomStudent,Response<DormRoomStudent>>
        ,IAuditClient<DormRoomStudent,String>,ISubmitClient<DormRoomStudent,String> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/dormroomstudent";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(DormRoomStudent s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param dormRoomStudent
     * @return
     */
    @Override
    public Response<DormRoomStudent> findByPrimaryKey(DormRoomStudent dormRoomStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dormRoomStudent);
        Response<DormRoomStudent> response = JSON.parseObject(responseXml,new TypeReference<Response<DormRoomStudent>>(){});
        return response;
    }



    /**
     * 查询列表
     * @param dormRoomStudentSignup
     * @return
     */
    @Override
    public Response<List<DormRoomStudent>> find(DormRoomStudent dormRoomStudentSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dormRoomStudentSignup);
        Response<List<DormRoomStudent>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<DormRoomStudent>>>(){});
        return response;

    }





    /**
     *保存
     * @param dormRoomStudent
     * @return
     */
    @Override
    public String save(DormRoomStudent dormRoomStudent){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),dormRoomStudent);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }



    @Override
    public String audit(DormRoomStudent dormRoomStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),dormRoomStudent);

        return responseXml;
    }

    @Override
    public String submit(DormRoomStudent dormRoomStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),dormRoomStudent);
        return responseXml;
    }
}
