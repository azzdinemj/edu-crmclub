package com.wuxue.view.client.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.TkLearnRecords;
import com.wuxue.model.TkProductMark;
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
public class TkLearnRecordsClient extends BaseClient implements
        IFindClient<TkLearnRecords, Response<List<TkLearnRecords>>>, ISaveClient<TkLearnRecords, String>,
        IDeleteClient<String, Object>, IFindByPrimaryKeyClient<TkLearnRecords, Response<TkLearnRecords>> {

    /**
     * 所属页面名称
     *
     * @return
     */
    @Override
    protected String getPageName() {
        return "/tklearnrecords";
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
    public String delete(String pkId) {
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
    public Response<TkLearnRecords> findByPrimaryKey(TkLearnRecords schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND), schedule);
        Response<TkLearnRecords> response = JSON.parseObject(responseXml, new TypeReference<Response<TkLearnRecords>>() {
        });
        return response;
    }

    /**
     * 查询列表
     *
     * @param schedule
     * @return
     */
    @Override
    public Response<List<TkLearnRecords>> find(TkLearnRecords schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND), schedule);
        Response<List<TkLearnRecords>> response = JSON.parseObject(responseXml, new TypeReference<Response<List<TkLearnRecords>>>() {});
        return response;
    }

    /**
     * 保存
     *
     * @param schedule
     * @return
     */
    @Override
    public String save(TkLearnRecords schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE), schedule);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }


}
