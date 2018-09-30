package com.wuxue.view.client.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ScheduleConstraint;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.BaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleConstraintClient extends BaseClient implements
        IFindClient<ScheduleConstraint,Response<List<ScheduleConstraint>>>,ISaveClient<ScheduleConstraint,String>,
        IDeleteClient<Long,Object>,IFindByPrimaryKeyClient<ScheduleConstraint,Response<ScheduleConstraint>>
       {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/scheduleConstraint";
    }
    @Override
    protected String getModuleName() {
        return "/product";
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public String delete(Long id) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),id);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param scheduleConstraint
     * @return
     */
    @Override
    public Response<ScheduleConstraint> findByPrimaryKey(ScheduleConstraint scheduleConstraint) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),scheduleConstraint);
        Response<ScheduleConstraint> response = JSON.parseObject(responseXml,new TypeReference<Response<ScheduleConstraint>>(){});
        return response;
    }



    /**
     * 查询列表
     * @param scheduleConstraint
     * @return
     */
    @Override
    public Response<List<ScheduleConstraint>> find(ScheduleConstraint scheduleConstraint) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),scheduleConstraint);
        Response<List<ScheduleConstraint>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ScheduleConstraint>>>(){});
        return response;

    }




    

    /**
     *保存
     * @param scheduleConstraint
     * @return
     */
    @Override
    public String save(ScheduleConstraint scheduleConstraint){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),scheduleConstraint);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

   
}
