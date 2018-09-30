package com.wuxue.view.client.heyun;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.HyProduct;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.HeYunBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyProductClient extends HeYunBaseClient implements
        ISaveClient<HyProduct,Response>, IFindClient<HyProduct,Response<List<HyProduct>>>,
        IDeleteClient<HyProduct,Object>,IFindByPrimaryKeyClient<HyProduct,Response<HyProduct>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/hyProduct";
    }
    /**
     * 删除
     * @param
     * @return
     */
    @Override
    public String delete(HyProduct product) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),product);
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param product
     * @return
     */
    @Override
    public Response<HyProduct> findByPrimaryKey(HyProduct product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<HyProduct> response = JSON.parseObject(responseXml,new TypeReference<Response<HyProduct>>(){});
        return response;
    }


    /**
     * 查询列表
     * @param
     * @return
     */
    @Override
    public Response<List<HyProduct>> find(HyProduct hyPay) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),hyPay);
        Response<List<HyProduct>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<HyProduct>>>(){});
        return response;

    }


    /**
     *保存
     * @param
     * @return
     */
    @Override
    public Response save(HyProduct schedule){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),schedule);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }



}
