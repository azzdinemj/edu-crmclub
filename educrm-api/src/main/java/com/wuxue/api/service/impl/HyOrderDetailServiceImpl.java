package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.HyOrderDetailMapper;
import com.wuxue.api.service.HyOrderDetailService;
import com.wuxue.model.HyOrderDetail;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("hyOrderDetailService")
public class HyOrderDetailServiceImpl implements HyOrderDetailService {

    @Autowired
    HyOrderDetailMapper hyOrderDetailMapper;
    @Override
    public Response find(Request<HyOrderDetail> tParams) {
        Response response = Response.newResponse();
        HyOrderDetail hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        if(hyUser.getId()!=null){
            HyOrderDetail byPrimaryKey = hyOrderDetailMapper.selectByPrimaryKey(hyUser.getId());
            response.setData(byPrimaryKey);
        } else{
            PageHelper.startPage(hyUser.getPageNo(), hyUser.getPageSize());
            List<HyOrderDetail> hyUserList = hyOrderDetailMapper.select(hyUser);
            PageInfo page = new PageInfo(hyUserList);
            response.setData(hyUserList);
            response.setTotal(page.getTotal());
        }

        return  response;
    }

    @Override
    public Response save(Request<HyOrderDetail> tParams) {
        Response response = Response.newResponse();
        HyOrderDetail hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        Integer id=hyUser.getId();
        try {
            if (id != null) {
                iReuslt = hyOrderDetailMapper.updateByPrimaryKeySelective(hyUser);
            } else {
                hyUser.setCreatorDate(new Date());
                iReuslt = hyOrderDetailMapper.insertSelective(hyUser);
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
