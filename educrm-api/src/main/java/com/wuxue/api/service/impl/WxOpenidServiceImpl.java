package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.*;
import com.wuxue.api.service.WxOpenidService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("wxOpenidService")
public class WxOpenidServiceImpl implements WxOpenidService{
    @Autowired
    WxOpenidMapper wxOpenidMapper;

    @Autowired
    EmployeeMapper employeeMapper;


    @Override
    public Response find(Request<WxOpenid> tParams) {
        Response response = Response.newResponse();
        WxOpenid wxOpenid = tParams.getData();
        if(wxOpenid== null){
            return  response.PARAMS_ISNULL();
        }
        if(wxOpenid.getId()!=null&&wxOpenid.getId()!=""){
            WxOpenid byPrimaryKey = wxOpenidMapper.selectByPrimaryKey(wxOpenid.getId());
            response.setData(byPrimaryKey);
        } else{
            if(wxOpenid.getOpenid()!=null&&wxOpenid.getOpenid()!=""){
                List<WxOpenid> studentList = wxOpenidMapper.select(wxOpenid);
                if(studentList.size()>0){
                    response.setData(studentList.get(0));
                }
            }
        }

        return  response;
    }

    @Override
    public Response save(Request<WxOpenid> tParams) {
        Response response = Response.newResponse();
        WxOpenid wxOpenid = tParams.getData();
        if(wxOpenid== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        Employee select=null;
        try {
            //查找 openid和用户主键的绑定记录是否已存在，不存在则添加。
            List<WxOpenid> list=wxOpenidMapper.select(wxOpenid);
            if(list.size()<=0){
                wxOpenid.setId(GuidUtils.getGuid());
                iReuslt = wxOpenidMapper.insertSelective(wxOpenid);
                response.setData(wxOpenid.getId());
            }else{
                response.setMessage("登录成功,已存在openid");
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


    /**
     * 保存openid
     * @param tParams
     * @return
     */
    @Override
    public Response saveOpenid(Request<WxOpenid> tParams) {
        Response response = Response.newResponse();
        WxOpenid wxOpenid = tParams.getData();
        if(wxOpenid== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            wxOpenid.setId(GuidUtils.getGuid());
            iReuslt = wxOpenidMapper.insertSelective(wxOpenid);
            response.setData(wxOpenid.getId());
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    //查找openid
    @Override
    public Response findOpenid(Request<WxOpenid> tParams) {
        Response response = Response.newResponse();
        WxOpenid wxOpenid = tParams.getData();
        if(wxOpenid== null){
            return  response.PARAMS_ISNULL();
        }

        if(wxOpenid.getOpenid()!=null&&wxOpenid.getOpenid()!=""){
            List<WxOpenid> studentList = wxOpenidMapper.select(wxOpenid);
            if(studentList.size()>0){
                response.setData(studentList.get(0));
            }
        }
        return  response;
    }

}
