package com.wuxue.api.controller.smallroutine.client.teacher;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.controller.smallroutine.client.EmployeeBaseClient;
import com.wuxue.model.Employee;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IDeleteClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeClient extends EmployeeBaseClient
        implements IFindClient<Employee,Response<List<Employee>>>,ISaveClient<Employee,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Employee,Response<Employee>> {




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
     * @param employee
     * @return
     */
    @Override
    public Response<List<Employee>> find(Employee employee) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employee);
        Response<List<Employee>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Employee>>>(){});
        return response;
    }

    /**
     *保存
     * @param employee
     * @return
     */
    @Override
    public String save(Employee employee){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),employee);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/employee";
    }

    /**
     * 主键查询
     * @param employee
     * @return
     */
    @Override
    public Response<Employee> findByPrimaryKey(Employee employee) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employee);
        Response<Employee> response = JSON.parseObject(responseXml,new TypeReference<Response<Employee>>(){});
        return response;
    }



    //根据电话查找
    public Response findByphone(String string) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDBYPHONE),string);
        Response<Employee> response = JSON.parseObject(responseXml, new TypeReference<Response<Employee>>(){});
        return response;
    }

    //主键查询 jiedian
    public Response findjiedian(Employee employee){
        String responseXml = POST(getSendUrl(ActionEnum.FINDJIEDIAN),employee);
        Response<Employee> response = JSON.parseObject(responseXml,new TypeReference<Response<Employee>>(){});
        return response;
    }

}
