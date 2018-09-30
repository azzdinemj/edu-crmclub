package com.wuxue.view.client.canteen;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.ParentOrder;
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
public class ParentOrderClient extends CanteenBaseClient
        implements IFindClient<ParentOrder,Response<List<ParentOrder>>>,ISaveClient<ParentOrder,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ParentOrder,Response<ParentOrder>>{




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
     * @param parentOrder
     * @return
     */
    @Override
    public Response<List<ParentOrder>> find(ParentOrder parentOrder) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),parentOrder);
        Response<List<ParentOrder>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ParentOrder>>>(){});
        return response;
    }
    public Response<List<ParentOrder>> selectForRecord(ParentOrder parentOrder) {
        String responseXml = POST(getSendUrl(ActionEnum.SELECTFORRECORD),parentOrder);
        Response<List<ParentOrder>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ParentOrder>>>(){});
        return response;
    }

    /**
     *保存
     * @param parentOrder
     * @return
     */
    @Override
    public String save(ParentOrder parentOrder){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEORUPDATE),parentOrder);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/parentOrder";
    }

    /**
     * 主键查询
     * @param parentOrder
     * @return
     */
    public Response<ParentOrder> findParentOrderInfoById(ParentOrder  parentOrder) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDINFO),parentOrder);
        Response<ParentOrder> response = JSON.parseObject(responseXml,new TypeReference<Response<ParentOrder>>(){});
        return response;
    }
    @Override
    public Response<ParentOrder> findByPrimaryKey(ParentOrder  parentOrderId) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDINFO),parentOrderId);
        Response<ParentOrder> response = JSON.parseObject(responseXml,new TypeReference<Response<ParentOrder>>(){});
        return response;
    }
}
