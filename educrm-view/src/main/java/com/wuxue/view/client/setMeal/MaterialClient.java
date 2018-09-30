package com.wuxue.view.client.setMeal;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.junhwa.Material;
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
public class MaterialClient extends CanteenBaseClient
        implements IFindClient<Material,Response<List<Material>>>,IDeleteClient<String,Object>,
        IFindByPrimaryKeyClient<Material,Response<Material>>,ISaveClient<Material,String>{




    /**
     * 删除
     * @param material 主键
     * @return
     */
    @Override
    public String delete(String material) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),material);
       // Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param material
     * @return
     */
    @Override
    public Response<List<Material>> find(Material material) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),material);
        Response<List<Material>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Material>>>(){});
        return response;
    }

    /**
     * 主键查询
     * @param material
     * @return
     */
    @Override
    public Response<Material> findByPrimaryKey(Material material) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),material);
        Response<Material> response = JSON.parseObject(responseXml,new TypeReference<Response<Material>>(){});
        return response;
    }

    /**
     *保存
     * @param material
     * @return
     */
    @Override
    public String save(Material material){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),material);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/material";
    }

}
