package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.DormRoomEmployee;
import com.wuxue.model.DormRoomEmployee;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormRoomEmployeeClient extends SystemBaseClient implements
        IFindClient<DormRoomEmployee,Response<List<DormRoomEmployee>>>,ISaveClient<DormRoomEmployee,String>,
        IDeleteClient<DormRoomEmployee,Object>,IFindByPrimaryKeyClient<DormRoomEmployee,Response<DormRoomEmployee>>
        ,IAuditClient<DormRoomEmployee,String>,ISubmitClient<DormRoomEmployee,String> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/dormroomemployee";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(DormRoomEmployee s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param dormRoomEmployee
     * @return
     */
    @Override
    public Response<DormRoomEmployee> findByPrimaryKey(DormRoomEmployee dormRoomEmployee) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dormRoomEmployee);
        Response<DormRoomEmployee> response = JSON.parseObject(responseXml,new TypeReference<Response<DormRoomEmployee>>(){});
        return response;
    }



    /**
     * 查询列表
     * @param dormRoomEmployeeSignup
     * @return
     */
    @Override
    public Response<List<DormRoomEmployee>> find(DormRoomEmployee dormRoomEmployeeSignup) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dormRoomEmployeeSignup);
        Response<List<DormRoomEmployee>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<DormRoomEmployee>>>(){});
        return response;

    }





    /**
     *保存
     * @param dormRoomEmployee
     * @return
     */
    @Override
    public String save(DormRoomEmployee dormRoomEmployee){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),dormRoomEmployee);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }



    @Override
    public String audit(DormRoomEmployee dormRoomEmployee) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),dormRoomEmployee);

        return responseXml;
    }

    @Override
    public String submit(DormRoomEmployee dormRoomEmployee) {
        String responseXml = POST(getSendUrl(ActionEnum.SUBMIT),dormRoomEmployee);
        return responseXml;
    }
}
