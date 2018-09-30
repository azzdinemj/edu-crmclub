package com.wuxue.view.client.canteen;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.ParentPay;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CanteenBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParentPayClient extends CanteenBaseClient
        implements IFindClient<ResultEntity,Response<List<ResultEntity>>>,ISaveClient<ParentPay,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ParentPay,Response<ParentPay>>{




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
     * @param map
     * @return
     */
    @Override
    public Response<List<ResultEntity>> find(ResultEntity map) {
        String responseXml = POST(getSendUrl(ActionEnum.GETPAYRECORDLIST),map);
        Response<List<ResultEntity>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ResultEntity>>>(){});
        return response;
    }

    /**
     *保存
     * @param parentPay
     * @return
     */
    @Override
    public String save(ParentPay parentPay){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEORUPDATE),parentPay);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/parentPay";
    }

    /**
     * 主键查询
     * @param parentPay
     * @return
     */
    public Response<ParentPay> findParentPayInfoById(ParentPay  parentPay) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDINFO),parentPay);
        Response<ParentPay> response = JSON.parseObject(responseXml,new TypeReference<Response<ParentPay>>(){});
        return response;
    }
    @Override
    public Response<ParentPay> findByPrimaryKey(ParentPay  parentPayId) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDINFO),parentPayId);
        Response<ParentPay> response = JSON.parseObject(responseXml,new TypeReference<Response<ParentPay>>(){});
        return response;
    }
}
