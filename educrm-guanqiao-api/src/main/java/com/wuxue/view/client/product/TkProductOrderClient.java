package com.wuxue.view.client.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ClassTime;
import com.wuxue.model.Schedule;
import com.wuxue.model.TkProductOrder;
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
public class TkProductOrderClient extends BaseClient implements
        IFindClient<TkProductOrder, Response<List<TkProductOrder>>>, ISaveClient<TkProductOrder, Response>,
        IDeleteClient<TkProductOrder, Object>, IFindByPrimaryKeyClient<TkProductOrder, Response<TkProductOrder>> {


    /**
     * 所属页面名称
     *
     * @return
     */
    @Override
    protected String getPageName() {
        return "/tkproductorder";
    }

    @Override
    protected String getModuleName() {
        return "/product";
    }

    /**
     * 删除
     *
     * @param pkId
     * @return
     */
    @Override
    public String delete( TkProductOrder pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE), pkId);
        return responseXml;
    }

    /**
     * 根据主键查询
     *
     * @param schedule
     * @return
     */
    @Override
    public Response<TkProductOrder> findByPrimaryKey(TkProductOrder schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND), schedule);
        Response<TkProductOrder> response = JSON.parseObject(responseXml, new TypeReference<Response<TkProductOrder>>() {});
        return response;
    }

    /**
     * 查询列表
     *
     * @param schedule
     * @return
     */
    @Override
    public Response<List<TkProductOrder>> find(TkProductOrder schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND), schedule);
        Response<List<TkProductOrder>> response = JSON.parseObject(responseXml, new TypeReference<Response<List<TkProductOrder>>>() {});
        return response;
    }

    /**
     * 保存
     *
     * @param schedule
     * @return
     */
    @Override
    public Response save(TkProductOrder schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE), schedule);
        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return response;
    }


}
