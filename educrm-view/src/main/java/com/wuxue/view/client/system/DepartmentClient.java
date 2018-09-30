package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Department;
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
public class DepartmentClient extends SystemBaseClient
        implements IFindClient<Department,Response<List<Department>>>,ISaveClient<Department,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Department,Response<Department>> {




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
     * @param department
     * @return
     */
    @Override
    public Response<List<Department>> find(Department department) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),department);
        Response<List<Department>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Department>>>(){});
        return response;
    }

    /**
     *保存
     * @param department
     * @return
     */
    @Override
    public String save(Department department){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),department);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/department";
    }

    /**
     * 主键查询
     * @param department
     * @return
     */
    @Override
    public Response<Department> findByPrimaryKey(Department department) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),department);
        Response<Department> response = JSON.parseObject(responseXml,new TypeReference<Response<Department>>(){});
        return response;
    }
}
