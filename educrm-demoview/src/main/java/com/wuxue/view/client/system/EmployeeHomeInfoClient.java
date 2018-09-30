package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.EmployeeHomeInfo;
import com.wuxue.model.EmployeeHomeInfo;
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
public class EmployeeHomeInfoClient extends SystemBaseClient
        implements IFindClient<EmployeeHomeInfo,Response<List<EmployeeHomeInfo>>>,ISaveClient<EmployeeHomeInfo,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<EmployeeHomeInfo,Response<EmployeeHomeInfo>> {




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
     * @param employeeHomeInfo
     * @return
     */
    @Override
    public Response<List<EmployeeHomeInfo>> find(EmployeeHomeInfo employeeHomeInfo) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employeeHomeInfo);
        Response<List<EmployeeHomeInfo>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<EmployeeHomeInfo>>>(){});
        return response;
    }

    /**
     *保存
     * @param employeeHomeInfo
     * @return
     */
    @Override
    public String save(EmployeeHomeInfo employeeHomeInfo){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),employeeHomeInfo);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/employeeHomeInfo";
    }

    /**
     * 主键查询
     * @param employeeHomeInfo
     * @return
     */
    @Override
    public Response<EmployeeHomeInfo> findByPrimaryKey(EmployeeHomeInfo employeeHomeInfo) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employeeHomeInfo);
        Response<EmployeeHomeInfo> response = JSON.parseObject(responseXml,new TypeReference<Response<EmployeeHomeInfo>>(){});
        return response;
    }
}
