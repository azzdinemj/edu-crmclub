package com.wuxue.view.client.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.TkProductMark;
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
public class TkProductMarkClient extends BaseClient implements
        IFindClient<TkProductMark, Response<List<TkProductMark>>>, ISaveClient<TkProductMark, String>,
        IDeleteClient<String, Object>, IFindByPrimaryKeyClient<TkProductMark, Response<TkProductMark>> {


    /**
     * 所属页面名称
     *
     * @return
     */
    @Override
    protected String getPageName() {
        return "/tkproductmark";
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
    public Response<TkProductMark> findByPrimaryKey(TkProductMark schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND), schedule);
        Response<TkProductMark> response = JSON.parseObject(responseXml, new TypeReference<Response<TkProductMark>>() {
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
    public Response<List<TkProductMark>> find(TkProductMark schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND), schedule);
        Response<List<TkProductMark>> response = JSON.parseObject(responseXml, new TypeReference<Response<List<TkProductMark>>>() {});
        return response;
    }

    /**
     * 保存
     *
     * @param schedule
     * @return
     */
    @Override
    public String save(TkProductMark schedule) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE), schedule);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }


}
