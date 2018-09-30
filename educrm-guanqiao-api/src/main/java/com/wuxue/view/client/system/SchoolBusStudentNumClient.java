package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.SchoolBusStudentNum;
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
public class SchoolBusStudentNumClient extends SystemBaseClient implements
        IFindClient<SchoolBusStudentNum,Response<List<SchoolBusStudentNum>>>,ISaveClient<SchoolBusStudentNum,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<SchoolBusStudentNum,Response<SchoolBusStudentNum>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/schoolbusstudentnum";
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
     * @param schoolBusStudentNum
     * @return
     */
    @Override
    public Response<SchoolBusStudentNum> findByPrimaryKey(SchoolBusStudentNum schoolBusStudentNum) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),schoolBusStudentNum);
        Response<SchoolBusStudentNum> response = JSON.parseObject(responseXml,new TypeReference<Response<SchoolBusStudentNum>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param schoolBusStudentNum
     * @return
     */
    @Override
    public Response<List<SchoolBusStudentNum>> find(SchoolBusStudentNum schoolBusStudentNum) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),schoolBusStudentNum);
        Response<List<SchoolBusStudentNum>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SchoolBusStudentNum>>>(){});
        return response;

    }

    /**
     *保存
     * @param schoolBusStudentNum
     * @return
     */
    @Override
    public String save(SchoolBusStudentNum schoolBusStudentNum){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),schoolBusStudentNum);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
