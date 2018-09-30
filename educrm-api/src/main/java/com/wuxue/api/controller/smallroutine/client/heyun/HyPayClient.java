package com.wuxue.api.controller.smallroutine.client.heyun;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.HyPay;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.client.HeYunBaseClient;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IDeleteClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyPayClient extends HeYunBaseClient implements
        ISaveClient<HyPay,Response>, IFindClient<HyPay,Response<List<HyPay>>>,
        IDeleteClient<HyPay,Object>,IFindByPrimaryKeyClient<HyPay,Response<HyPay>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/hyPay";
    }
    /**
     * 删除
     * @param
     * @return
     */
    @Override
    public String delete(HyPay product) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),product);
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param product
     * @return
     */
    @Override
    public Response<HyPay> findByPrimaryKey(HyPay product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<HyPay> response = JSON.parseObject(responseXml,new TypeReference<Response<HyPay>>(){});
        return response;
    }


    /**
     * 查询列表
     * @param
     * @return
     */
    @Override
    public Response<List<HyPay>> find(HyPay hyPay) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyPay);
        Response<List<HyPay>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<HyPay>>>(){});
        return response;

    }


    /**
     *保存
     * @param
     * @return
     */
    @Override
    public Response save(HyPay schedule){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),schedule);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }



}
