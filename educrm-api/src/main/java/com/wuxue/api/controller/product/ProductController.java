package com.wuxue.api.controller.product;

import com.wuxue.api.interfaces.*;
import com.wuxue.api.service.ProductService;
import com.wuxue.model.Product;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/product/product")
public class ProductController implements IFindController<Product>,
        ISaveController<Product>,IDeleteController<String> {
    @Autowired
    private ProductService productService;

    @Override
    public Response Find(@RequestBody Request<Product> product) {
        return productService.find(product);
    }

    @Override
    public Response Save(@RequestBody Request<Product> product) {
        return productService.save(product);
    }

    @Override
    public Response Delete(@RequestBody Request<String> product) {
        return productService.delete(product);
    }

    @RequestMapping(value = "/selectby",method = RequestMethod.POST)
    public Response selectBy(@RequestBody Request<Product> product) {
        return productService.selectBy(product);
    }

    @RequestMapping(value = "/findguanqiao",method = RequestMethod.POST)
    public Response findGuanqiao(@RequestBody Request<Product> product) {
        return productService.findGuanqiao(product);
    }


}
