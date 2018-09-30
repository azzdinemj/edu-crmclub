package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ProductMapper;
import com.wuxue.api.mapper.SysDictValuesMapper;
import com.wuxue.api.mapper.TalkCloudRoomMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.CommonUtils;
import com.wuxue.model.AskForLeave;
import com.wuxue.model.Product;
import com.wuxue.api.service.ProductService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.SysDictValues;
import com.wuxue.model.TalkCloudRoom;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	@Autowired
    ProductMapper productMapper;
    @Autowired
    UtilsService utilsService;

    @Autowired
    TalkCloudRoomMapper talkCloudRoomMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=productMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Product> tParams) {
        Response response = Response.newResponse();
        Product product = tParams.getData();

        if(product== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = product.getPkProduct();
        if(primaryKey !=null && !primaryKey.equals("")){
        	Product byPrimaryKey = productMapper.selectByPrimaryKey(primaryKey);
           // utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
           // utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
           // utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getSubmitor(),LinkEntity.SUBMITOR_ENTITY);
           // utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getAuditor(),LinkEntity.AUDITOR_ENTITY);
           // utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCancel(),LinkEntity.CANCEL_ENTITY);
           // utilsService.setStudentKeyValue(byPrimaryKey,byPrimaryKey.getPkStudent(),LinkEntity.STUDENT_ENTITY);
          //  utilsService.setEmployeeKeyValue(byPrimaryKey,byPrimaryKey.getPkSysUser(),LinkEntity.EMP_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<Product> productList = productMapper.select(product);
            if (productList.size() > 0) {
                for (Product list : productList) {
                   // utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                  //  utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                  //  utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                  //  utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                  //  utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                  //  utilsService.setStudentKeyValue(list,list.getPkStudent(),LinkEntity.STUDENT_ENTITY);
                  //  utilsService.setEmployeeKeyValue(list,list.getPkSysUser(),LinkEntity.EMP_ENTITY);
                }
            }
            response.setData(productList);
            //response.setTotal(paymentMapper.countBy(payment));

        }
        return response;
    }

    @Override
    public Response save(Request<Product> tParams) {
        Response response = Response.newResponse();
        Product product = tParams.getData();

        if(product== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = product.getPkProduct();
        Product select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = productMapper.selectByPrimaryKey(primaryKey);
        }

        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
            	product.setLasteditDate(new Date());
                iReuslt = productMapper.updateByPrimaryKeySelective(product);
            } else {
            	product.setPkProduct(GuidUtils.getGuid());
            	product.setCreationDate(new Date());
            	product.setLasteditDate(new Date());

                iReuslt = productMapper.insertSelective(product);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }


    @Override
    public Response selectBy(Request<Product> tParams) {
        Response response = Response.newResponse();
        Product product = tParams.getData();

        if(product== null){
            return  response.PARAMS_ISNULL();
        }
        List<Product> productList = productMapper.selectBy(product);
        response.setData(productList);
        return response;
    }

    @Autowired
    SysDictValuesMapper sysDictValuesMapper;

    /**
     * 查找冠桥产品列表
     * @param params
     * @return
     */
    @Override
    public Response findGuanqiao(Request<Product> params) {
        Response response = Response.newResponse();
        Product product = params.getData();
        if(product== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = product.getPkProduct();
        if(primaryKey !=null && !primaryKey.equals("")){
            Product byPrimaryKey = productMapper.selectByPrimaryKey(primaryKey);
            SysDictValues sysDictValues=sysDictValuesMapper.selectByPrimaryKey(byPrimaryKey.getProductType());

            byPrimaryKey.put("sysDictValues",sysDictValues);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(product.getPageNo(), product.getPageSize());
            List<Product> productList = productMapper.select(product);
            PageInfo page = new PageInfo(productList);

            for (Product product1:productList) {
                SysDictValues sysDictValues=sysDictValuesMapper.selectByPrimaryKey(product1.getProductType());
                product1.put("sysDictValues",sysDictValues);
            }
            response.setData(productList);
            response.setTotal(page.getTotal());
        }
        return response;

    }

}
