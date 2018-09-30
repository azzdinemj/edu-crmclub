package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.SysMenuButtonMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.SysMenuButton;
import com.wuxue.model.SysMenuButtonKey;
import com.wuxue.api.service.SysMenuButtonService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("sysMenuButtonService")
public class SysMenuButtonServiceImpl implements SysMenuButtonService{
    @Autowired
    SysMenuButtonMapper sysMenuButtonMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<SysMenuButtonKey> tParams) {
        Response response = Response.newResponse();
        SysMenuButtonKey sysMenuButtonKey = tParams.getData();
//        String primaryKey = tParams.getData();
        if(sysMenuButtonKey== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=sysMenuButtonMapper.deleteByPrimaryKey(sysMenuButtonKey);
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
    public Response find(Request<SysMenuButton> tParams) {
        Response response = Response.newResponse();
        SysMenuButton sysMenuButton = tParams.getData();

        if(sysMenuButton== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysMenuButton.getPkSysButton();
        String primaryKey2 = sysMenuButton.getPkSysMenu();
        if(primaryKey !=null && !primaryKey.equals("") || primaryKey2 !=null && !primaryKey2.equals("")){
            SysMenuButtonKey sysMenuButtonKey = new SysMenuButtonKey();
            sysMenuButtonKey.setPkSysMenu(primaryKey2);
            sysMenuButtonKey.setPkSysButton(primaryKey);
            SysMenuButton byPrimaryKey = sysMenuButtonMapper.selectByPrimaryKey(sysMenuButtonKey);
            response.setData(byPrimaryKey);
        }else{
            List<SysMenuButton> buttonList = sysMenuButtonMapper.select(sysMenuButton);

            response.setData(buttonList);
            //response.setTotal(sysMenuButtonMapper.countBy(sysMenuButton));

        }
        return response;
    }

    @Override
    public Response save(Request<SysMenuButton> tParams) {
        Response response = Response.newResponse();
        SysMenuButton sysMenuButton = tParams.getData();

        if(sysMenuButton== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey1 = sysMenuButton.getPkSysButton();
        String primaryKey2 = sysMenuButton.getPkSysMenu();
        SysMenuButton select = null;
        if (primaryKey1 != null && !primaryKey1.equals("") && primaryKey2 != null && !primaryKey2.equals("")) {
            SysMenuButtonKey sysMenuButtonKey = new SysMenuButtonKey();
            sysMenuButtonKey.setPkSysMenu(primaryKey2);
            sysMenuButtonKey.setPkSysButton(primaryKey1);
            select = sysMenuButtonMapper.selectByPrimaryKey(sysMenuButtonKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = sysMenuButtonMapper.updateByPrimaryKeySelective(sysMenuButton);
            } else {
//                sysMenuButton.setPkSysButton(GuidUtils.getGuid());
                iReuslt = sysMenuButtonMapper.insertSelective(sysMenuButton);
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
