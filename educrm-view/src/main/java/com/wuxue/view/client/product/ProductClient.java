package com.wuxue.view.client.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Product;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.BaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductClient extends BaseClient implements
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
    @Override
    protected String getModuleName() {
        return "/product";
    }
    /**
     * 删除
     * @param s
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
     * @param student
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
     * @param studentSignup
     * @return
     */
    @Override
    public Response<List<Product>> find(Product product) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),product);
        Response<List<Product>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Product>>>(){});
        return response;

    }

   public Response<List<Product>> selectBy(Product product) {
       String responseXml = POST(getSendUrl(ActionEnum.SELECTBY),product);
       Response<List<Product>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Product>>>(){});
       return response;

   }


    

    /**
     *保存
     * @param student
     * @return
     */
    @Override
    public String save(Product product){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),product);
//        Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

   
}
