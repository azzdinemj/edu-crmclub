package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.TkSetMeal;
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
public class TkSetMealClient extends SystemBaseClient
        implements IFindClient<TkSetMeal,Response<List<TkSetMeal>>>,ISaveClient<TkSetMeal,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<TkSetMeal,Response<TkSetMeal>> {




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
     * @param tkSetMeal
     * @return
     */
    @Override
    public Response<List<TkSetMeal>> find(TkSetMeal tkSetMeal) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),tkSetMeal);
        Response<List<TkSetMeal>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<TkSetMeal>>>(){});
        return response;
    }

    /**
     *保存
     * @param tkSetMeal
     * @return
     */
    @Override
    public String save(TkSetMeal tkSetMeal){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),tkSetMeal);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/tkSetMeal";
    }

    /**
     * 主键查询
     * @param tkSetMeal
     * @return
     */
    @Override
    public Response<TkSetMeal> findByPrimaryKey(TkSetMeal tkSetMeal) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),tkSetMeal);
        Response<TkSetMeal> response = JSON.parseObject(responseXml,new TypeReference<Response<TkSetMeal>>(){});
        return response;
    }
}
