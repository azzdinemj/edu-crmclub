package com.wuxue.view.client.setMeal;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Canteen;
import com.wuxue.model.SetMeal;
import com.wuxue.model.JhQuestion;
import com.wuxue.model.SetMeal;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CanteenBaseClient;
import com.wuxue.view.client.JunhuaBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetMealClient extends CanteenBaseClient
        implements IFindClient<SetMeal,Response<List<SetMeal>>>,IDeleteClient<String,Object>,
        IFindByPrimaryKeyClient<SetMeal,Response<SetMeal>>,ISaveClient<SetMeal,String>{




    /**
     * 删除
     * @param setMeal 主键
     * @return
     */
    @Override
    public String delete(String setMeal) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),setMeal);
       // Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param setMeal
     * @return
     */
    @Override
    public Response<List<SetMeal>> find(SetMeal setMeal) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),setMeal);
        Response<List<SetMeal>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SetMeal>>>(){});
        return response;
    }

    public Response<List<SetMeal>> mealStatistics(SetMeal setMeal) {
        String responseXml = POST(getSendUrl(ActionEnum.MEALSTATISTICS),setMeal);
        Response<List<SetMeal>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SetMeal>>>(){});
        return response;
    }

    /**
     * 主键查询
     * @param setMeal
     * @return
     */
    @Override
    public Response<SetMeal> findByPrimaryKey(SetMeal setMeal) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),setMeal);
        Response<SetMeal> response = JSON.parseObject(responseXml,new TypeReference<Response<SetMeal>>(){});
        return response;
    }

    /**
     *保存
     * @param setMeal
     * @return
     */
    @Override
    public String save(SetMeal setMeal){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),setMeal);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    public String queryDefault(SetMeal setMeal){
        String responseXml = POST(getSendUrl(ActionEnum.QUERYDEFAULT),setMeal);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    public String queryCode(SetMeal setMeal){
        String responseXml = POST(getSendUrl(ActionEnum.QUERYCODE),setMeal);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/setMeal";
    }

}
