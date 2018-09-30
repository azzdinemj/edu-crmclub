package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.EmployeeJobExp;
import com.wuxue.model.EmployeeJobExp;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeJobExpClient extends SystemBaseClient
        implements IFindClient<EmployeeJobExp,Response<List<EmployeeJobExp>>>,ISaveClient<EmployeeJobExp,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<EmployeeJobExp,Response<EmployeeJobExp>> {




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
     * @param employeeJobExp
     * @return
     */
    @Override
    public Response<List<EmployeeJobExp>> find(EmployeeJobExp employeeJobExp) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employeeJobExp);
        Response<List<EmployeeJobExp>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<EmployeeJobExp>>>(){});
        return response;
    }

    /**
     *保存
     * @param employeeJobExp
     * @return
     */
    @Override
    public String save(EmployeeJobExp employeeJobExp){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),employeeJobExp);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/employeeJobExp";
    }

    /**
     * 主键查询
     * @param employeeJobExp
     * @return
     */
    @Override
    public Response<EmployeeJobExp> findByPrimaryKey(EmployeeJobExp employeeJobExp) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employeeJobExp);
        Response<EmployeeJobExp> response = JSON.parseObject(responseXml,new TypeReference<Response<EmployeeJobExp>>(){});
        return response;
    }
}
