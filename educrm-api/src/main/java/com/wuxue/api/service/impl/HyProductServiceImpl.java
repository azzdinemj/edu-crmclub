package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.HyProductMapper;
import com.wuxue.api.service.HyProductService;
import com.wuxue.model.HyProduct;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("hyProductService")
public class HyProductServiceImpl implements HyProductService {


    @Autowired
    HyProductMapper hyProductMapper;
    @Override
    public Response find(Request<HyProduct> tParams) {
        Response response = Response.newResponse();
        HyProduct hyProduct = tParams.getData();
        if(hyProduct== null){
            return  response.PARAMS_ISNULL();
        }
        if(hyProduct.getId()!=null){
            HyProduct byPrimaryKey = hyProductMapper.selectByPrimaryKey(hyProduct.getId());
            response.setData(byPrimaryKey);
        } else{
            PageHelper.startPage(hyProduct.getPageNo(), hyProduct.getPageSize());
            List<HyProduct> hyProductList = hyProductMapper.select(hyProduct);
            PageInfo page = new PageInfo(hyProductList);
            response.setData(hyProductList);
            response.setTotal(page.getTotal());
        }

        return  response;
    }

    @Override
    public Response save(Request<HyProduct> tParams) {
        Response response = Response.newResponse();
        HyProduct hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        Integer id=hyUser.getId();
        try {
            if (id != null) {
                iReuslt = hyProductMapper.updateByPrimaryKeySelective(hyUser);
            } else {
                hyUser.setCreatorDate(new Date());
                iReuslt = hyProductMapper.insertSelective(hyUser);
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



}
