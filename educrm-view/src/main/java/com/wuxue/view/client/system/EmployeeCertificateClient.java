package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.EmployeeCertificate;
import com.wuxue.model.EmployeeCertificate;
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
public class EmployeeCertificateClient extends SystemBaseClient implements
        IFindClient<EmployeeCertificate,Response<List<EmployeeCertificate>>>,ISaveClient<EmployeeCertificate,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<EmployeeCertificate,Response<EmployeeCertificate>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/employeeCertificate";
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
     * @param employeeCertificate
     * @return
     */
    @Override
    public Response<EmployeeCertificate> findByPrimaryKey(EmployeeCertificate employeeCertificate) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employeeCertificate);
        Response<EmployeeCertificate> response = JSON.parseObject(responseXml,new TypeReference<Response<EmployeeCertificate>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param employeeCertificate
     * @return
     */
    @Override
    public Response<List<EmployeeCertificate>> find(EmployeeCertificate employeeCertificate) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),employeeCertificate);
        Response<List<EmployeeCertificate>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<EmployeeCertificate>>>(){});
        return response;

    }

    /**
     *保存
     * @param employeeCertificate
     * @return
     */
    @Override
    public String save(EmployeeCertificate employeeCertificate){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),employeeCertificate);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
