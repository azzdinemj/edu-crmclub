package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.HyUserMapper;
import com.wuxue.api.service.HyUserService;
import com.wuxue.model.HyUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("hyUserService")
public class HyUserServiceImpl implements HyUserService {

    @Autowired
    HyUserMapper hyUserMapper;

    @Override
    public Response find(Request<HyUser> tParams) {
        Response response = Response.newResponse();
        HyUser hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        if(hyUser.getId()!=null){
            HyUser byPrimaryKey = hyUserMapper.selectByPrimaryKey(hyUser.getId());
            response.setData(byPrimaryKey);
        } else{
            PageHelper.startPage(hyUser.getPageNo(), hyUser.getPageSize());
            List<HyUser> hyUserList = hyUserMapper.select(hyUser);
            PageInfo page = new PageInfo(hyUserList);
            response.setData(hyUserList);
            response.setTotal(page.getTotal());
        }

        return  response;
    }

    @Override
    public Response save(Request<HyUser> tParams) {
        Response response = Response.newResponse();
        HyUser hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        Integer id=hyUser.getId();
        try {
            if (id != null) {
                iReuslt = hyUserMapper.updateByPrimaryKeySelective(hyUser);
            } else {
                hyUser.setCreatorDate(new Date());
                iReuslt = hyUserMapper.insertSelective(hyUser);
                response.setData(hyUser.getId());
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
    public Response countBy(Request<HyUser> tParams) {
        Response response=Response.newResponse();
        HyUser hyOrder=tParams.getData();
        Integer  i= hyUserMapper.countBy(hyOrder);
        response.setData(i);
        return response;
    }


}
