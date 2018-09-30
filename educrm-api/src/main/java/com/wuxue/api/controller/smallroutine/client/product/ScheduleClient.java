package com.wuxue.api.controller.smallroutine.client.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.controller.smallroutine.client.ProductBaseClient;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IDeleteClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleClient extends ProductBaseClient implements
        IFindClient<Schedule,Response<List<Schedule>>>,ISaveClient<Schedule,Response>,
        IDeleteClient<Schedule,Object>,IFindByPrimaryKeyClient<Schedule,Response<Schedule>>
       {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/schedule";
    }
    /**
     * 删除
     * @param
     * @return
     */
    @Override
    public String delete(Schedule product) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),product);
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param product
     * @return
     */
    @Override
    public Response<Schedule> findByPrimaryKey(Schedule product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<Schedule> response = JSON.parseObject(responseXml,new TypeReference<Response<Schedule>>(){});
        return response;
    }

    /**
     * 根据主键查询详情
     * @param str
     * @return
     */
    public Response findByPrimarypk(String  str) {
        String responseXml = POST(getSendUrl(ActionEnum.GETSTUDENT),str);
        Response<Schedule> response = JSON.parseObject(responseXml,new TypeReference<Response<Schedule>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param
     * @return
     */
    @Override
    public Response<List<Schedule>> find(Schedule product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<List<Schedule>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Schedule>>>(){});
        return response;

    }
    

    /**
     *保存
     * @param
     * @return
     */
    @Override
    public Response save(Schedule schedule){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),schedule);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }

   
}
