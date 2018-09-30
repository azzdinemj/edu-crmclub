package com.wuxue.view.controllers.jiedian.product;


import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;

import com.wuxue.view.client.product.ProductClient;

import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 意向学生
 */
@Controller("jiedianmyproduct")
@RequestMapping(value = "/jiedian/product")
public class ProductController extends BaseController
implements IQueryController<Product, String>,IQueryByPagingController<Product,Map<String,Object>>,ICreateController<Product, String>, IEditController<Product, String>,ISaveController<Product, String>{

    @InitBinder("product")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("pro.");
    }

    @Autowired
    private SysDictUtils sysDictUtils;

    @Autowired
    private ProductClient productClient;
    
    private void initPageAttribute(Model model, String pkDomain) {
        //科目类型
        sysDictUtils.setModeAttribute(model, "subject", SysDicEmnuUtils.STU_DISCIPLINE);
       
    }
    
    /**
     * 学生列表
     *
     * @param model
     * @param product
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Product product) {
      
        model.addAttribute("isType",3);
      
        //年级
        //sysDictUtils.setModeAttribute(model, "grade", SysDicEmnuUtils.STUDENT_GRADE);

        return "/jiedian/product/query";
    }
    
    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Product product) {
       
    	initPageAttribute(model, product.getPkDomain());
        return "/jiedian/product/product";
    }
    /**
     * 添加页面
     *
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Product product) {
    	Response<Product> byPrimaryKey = productClient.findByPrimaryKey(product);
        model.addAttribute("product", byPrimaryKey.getData());
    	initPageAttribute(model, product.getPkDomain());
    	return "/jiedian/product/product";
    }
    /**
     * 保存
     *
     * @param request
     * @param model
     * @param product
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Product product) {

        Enumeration<String> parameterNames = request.getParameterNames();



        if(product.getPkProduct() == null && "".equals(product.getPkProduct())){
        	product.setCreator(SessionCache.getUserCode());
        	product.setModifier(SessionCache.getUserCode());
        }else {
        	product.setModifier(SessionCache.getUserCode());

        }

        return productClient.save(product);

    }
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Product product, Integer sEcho, Integer start, Integer length) {
    	product.setPageNo((start/length)+1);
    	product.setPageSize(length);
       
        Response<List<Product>> listResponse = productClient.find(product);
        List<Product> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }
    
   
}
