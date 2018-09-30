package com.wuxue.api.controller.smallroutine.client.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.controller.smallroutine.client.ProductBaseClient;
import com.wuxue.model.Product;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.enums.ActionEnum;
import com.wuxue.api.controller.smallroutine.interfaces.IDeleteClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.api.controller.smallroutine.interfaces.IFindClient;
import com.wuxue.api.controller.smallroutine.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductClient extends ProductBaseClient implements
        IFindClient<Product,Response<List<Product>>>,ISaveClient<Product,String>,
        IDeleteClient<Product,Object>,IFindByPrimaryKeyClient<Product,Response<Product>>
       {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/product";
    }
    /**
     * 删除
     * @param product
     * @return
     */
    @Override
    public String delete(Product product) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),product);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param product
     * @return
     */
    @Override
    public Response<Product> findByPrimaryKey(Product product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<Product> response = JSON.parseObject(responseXml,new TypeReference<Response<Product>>(){});
        return response;
    }

    /**
     * 根据主键查询详情  中航
     * @param str
     * @return
     */
    public Response findByPrimarypk(String  str) {
        String responseXml = POST(getSendUrl(ActionEnum.GETSTUDENT),str);
        Response<Product> response = JSON.parseObject(responseXml,new TypeReference<Response<Product>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param product
     * @return
     */
    @Override
    public Response<List<Product>> find(Product product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<List<Product>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Product>>>(){});
        return response;

    }


    /**
     *保存
     * @param product
     * @return
     */
    @Override
    public String save(Product product){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),product);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

   
}
