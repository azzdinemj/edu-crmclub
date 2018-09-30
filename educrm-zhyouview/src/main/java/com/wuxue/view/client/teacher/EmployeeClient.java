package com.wuxue.view.client.teacher;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Employee;
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
public class EmployeeClient extends SystemBaseClient
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

    /**
     * 查询 zhyou
     * @param course
     * @return
     */
    public Response<PageInfo<Employee>> findzhyou(Employee course) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDZHYOU),course);
        Response<PageInfo<Employee>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<Employee>>>(){});
        return response;
    }

    /**
     *保存 zhyou
     * @param employee
     * @return
     */
    public String savezhyou(Employee employee){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEZHYOU),employee);
        return responseXml;
    }

    /**
     * 主键查询  zhyou
     * @param employee
     * @return
     */
    public Response<Employee> findByPrimaryKeyzhyou(Employee employee) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDZHYOU),employee);
        Response<Employee> response = JSON.parseObject(responseXml,new TypeReference<Response<Employee>>(){});
        return response;
    }
}
