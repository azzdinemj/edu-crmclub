package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.HyOrderChangeMapper;
import com.wuxue.api.service.HyOrderChangeService;
import com.wuxue.model.HyOrderChange;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("hyOrderChangeService")
public class HyOrderChangeServiceImpl implements HyOrderChangeService {
    @Autowired
    HyOrderChangeMapper hyOrderChangeMapper;

    @Override
    public Response find(Request<HyOrderChange> tParams) {
        Response response = Response.newResponse();
        HyOrderChange hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        if(hyUser.getId()!=null){
            HyOrderChange byPrimaryKey = hyOrderChangeMapper.selectByPrimaryKey(hyUser.getId());
            response.setData(byPrimaryKey);
        } else{
            PageHelper.startPage(hyUser.getPageNo(), hyUser.getPageSize());
            List<HyOrderChange> hyUserList = hyOrderChangeMapper.select(hyUser);
            PageInfo page = new PageInfo(hyUserList);
            response.setData(hyUserList);
            response.setTotal(page.getTotal());
        }

        return  response;
    }

    @Override
    public Response save(Request<HyOrderChange> tParams) {
        Response response = Response.newResponse();
        HyOrderChange hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        Integer id=hyUser.getId();
        try {
            if (id != null) {
                iReuslt = hyOrderChangeMapper.updateByPrimaryKeySelective(hyUser);
            } else {
                iReuslt = hyOrderChangeMapper.insertSelective(hyUser);
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
