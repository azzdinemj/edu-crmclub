package com.wuxue.api.service;


import com.wuxue.api.interfaces.*;
import com.wuxue.model.Product;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ProductService extends ISaveService<Product>,IFindService<Product>,IDeleteService<String> {

    /**
     * 查询启用的产品
     * */
    Response selectBy(Request<Product> product);

    /**
     * 查询冠桥产品
     * */
    Response findGuanqiao(Request<Product> product);
}
