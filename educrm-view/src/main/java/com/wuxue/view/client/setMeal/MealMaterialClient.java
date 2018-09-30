package com.wuxue.view.client.setMeal;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.junhwa.MealMaterial;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CanteenBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealMaterialClient extends CanteenBaseClient
        implements IFindClient<MealMaterial,Response<List<MealMaterial>>>,IDeleteClient<String,Object>,
        IFindByPrimaryKeyClient<MealMaterial,Response<MealMaterial>>,ISaveClient<MealMaterial,String>{




    /**
     * 删除
     * @param mealMaterial 主键
     * @return
     */
    @Override
    public String delete(String mealMaterial) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),mealMaterial);
       // Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param mealMaterial
     * @return
     */
    @Override
    public Response<List<MealMaterial>> find(MealMaterial mealMaterial) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),mealMaterial);
        Response<List<MealMaterial>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<MealMaterial>>>(){});
        return response;
    }

    /**
     * 主键查询
     * @param mealMaterial
     * @return
     */
    @Override
    public Response<MealMaterial> findByPrimaryKey(MealMaterial mealMaterial) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),mealMaterial);
        Response<MealMaterial> response = JSON.parseObject(responseXml,new TypeReference<Response<MealMaterial>>(){});
        return response;
    }

    /**
     *保存
     * @param mealMaterial
     * @return
     */
    @Override
    public String save(MealMaterial mealMaterial){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),mealMaterial);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/mealMaterial";
    }

}
