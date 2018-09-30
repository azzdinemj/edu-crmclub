package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.HyPayService;
import com.wuxue.model.HyPay;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("hyPayService")
public class HyPayServiceImpl implements HyPayService {

    @Autowired
    HyPayMapper hyPayMapper;

    @Override
    public Response find(Request<HyPay> tParams) {
        Response response = Response.newResponse();
        HyPay hyPay = tParams.getData();
        if(hyPay== null){
            return  response.PARAMS_ISNULL();
        }
        if(hyPay.getId()!=null){
            HyPay byPrimaryKey = hyPayMapper.selectByPrimaryKey(hyPay.getId());
            response.setData(byPrimaryKey);
        } else{
            PageHelper.startPage(hyPay.getPageNo(), hyPay.getPageSize());
            List<HyPay> hyPayList = hyPayMapper.select(hyPay);
            PageInfo page = new PageInfo(hyPayList);
            response.setData(hyPayList);
            response.setTotal(page.getTotal());
        }

        return  response;
    }

    @Override
    public Response save(Request<HyPay> tParams) {
        Response response = Response.newResponse();
        HyPay hyPay = tParams.getData();
        if(hyPay== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        Integer id=hyPay.getId();
        try {
            if (id != null) {
                iReuslt = hyPayMapper.updateByPrimaryKeySelective(hyPay);
            } else {
                hyPay.setCeratorDate(new Date());
                iReuslt = hyPayMapper.insertSelective(hyPay);
                response.setData(hyPay.getId());
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
