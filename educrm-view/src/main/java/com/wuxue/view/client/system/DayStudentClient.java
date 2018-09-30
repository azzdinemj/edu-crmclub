package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.junhwa.DayStudent;
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
public class DayStudentClient extends SystemBaseClient
        implements IFindClient<DayStudent,Response<List<DayStudent>>>,ISaveClient<DayStudent,String>,
        IDeleteClient<Integer,Object>,IFindByPrimaryKeyClient<DayStudent,Response<DayStudent>> {




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(Integer pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param dayStudent
     * @return
     */
    @Override
    public Response<List<DayStudent>> find(DayStudent dayStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dayStudent);
        Response<List<DayStudent>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<DayStudent>>>(){});
        return response;
    }

    /**
     *保存
     * @param dayStudent
     * @return
     */
    @Override
    public String save(DayStudent dayStudent){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),dayStudent);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/dayStudent";
    }

    /**
     * 主键查询
     * @param dayStudent
     * @return
     */
    @Override
    public Response<DayStudent> findByPrimaryKey(DayStudent dayStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dayStudent);
        Response<DayStudent> response = JSON.parseObject(responseXml,new TypeReference<Response<DayStudent>>(){});
        return response;
    }

    public String saveAll(DayStudent dayStudent) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVEALL),dayStudent);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;

    }
}
