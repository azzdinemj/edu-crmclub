package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.SysMenuMapper;
import com.wuxue.model.SysMenu;
import com.wuxue.api.service.SysMenuService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService{
    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public Response delete(Request<String> tParams) {
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=sysMenuMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return Response.newResponse().DELETE_FAIL(message);
        }
        if(iReuslt>0) {
            return Response.newResponse();
        }
        return Response.newResponse().DELETE_FAIL(message);

    }

    @Override
    public Response find(Request<SysMenu> tParams) {
        SysMenu sysMenu = tParams.getData();

        if(sysMenu== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = sysMenu.getPkSysMenu();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( sysMenuMapper.selectByPrimaryKey(primaryKey));
        }else{
            response.setData(sysMenuMapper.select(sysMenu));
            response.setTotal(sysMenuMapper.countBy(sysMenu));

        }
        return response;
    }

    @Override
    public Response save(Request<SysMenu> tParams) {
        SysMenu sysMenu = tParams.getData();

        if(sysMenu== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = sysMenu.getPkSysMenu();
        SysMenu select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = sysMenuMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
            } else {
                sysMenu.setPkSysMenu(GuidUtils.getGuid());
                iReuslt = sysMenuMapper.insertSelective(sysMenu);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return Response.newResponse();
        }
        return Response.newResponse().SAVE_FAIL(message);
    }

    @Override
    public Response findparentisnotnull(Request<SysMenu> request) {
        SysMenu sysMenu = request.getData();

        if(sysMenu== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = sysMenu.getPkSysMenu();
        Response response = Response.newResponse();

        response.setData(sysMenuMapper.selectparentisnotNull(sysMenu));
        response.setTotal(sysMenuMapper.countByparentisnotNull(sysMenu));
        return response;
    }

    @Override
    public Response findparentisnull(Request<SysMenu> request) {
        SysMenu sysMenu = request.getData();

        if(sysMenu== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = sysMenu.getPkSysMenu();
        Response response = Response.newResponse();

        response.setData(sysMenuMapper.selectparentisNull(sysMenu));
        response.setTotal(sysMenuMapper.countByparentisNull(sysMenu));
        return response;
    }

}
