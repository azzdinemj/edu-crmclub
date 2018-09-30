package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.SysSetMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.SysSet;
import com.wuxue.api.service.SysSetService;
import com.wuxue.model.SysSetKey;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("sysSetService")
public class SysSetServiceImpl implements SysSetService{
    @Autowired
    SysSetMapper sysSetMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<SysSet> tParams) {
        Response response = Response.newResponse();
        SysSet sysSet = tParams.getData();

        if(sysSet.getPkDomain() == null || sysSet.getPkDomain().equals("") || sysSet.getPkSysSet() == null || sysSet.getPkSysSet().equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            SysSetKey sysSetKey = new SysSetKey();
            sysSetKey.setPkDomain(sysSet.getPkDomain());
            sysSetKey.setPkSysSet(sysSet.getPkSysSet());
            iReuslt=sysSetMapper.deleteByPrimaryKey(sysSetKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<SysSet> tParams) {
        Response response = Response.newResponse();
        SysSet sysSet = tParams.getData();

        if(sysSet== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysSet.getPkSysSet();
        String pkDomin = sysSet.getPkDomain();
        if(primaryKey !=null && !primaryKey.equals("") && pkDomin != null && !pkDomin.equals("")){
            SysSetKey sysSetKey = new SysSetKey();
            sysSetKey.setPkSysSet(primaryKey);
            sysSetKey.setPkDomain(pkDomin);
            SysSet byPrimaryKey = sysSetMapper.selectByPrimaryKey(sysSetKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<SysSet> sysSetList = sysSetMapper.select(sysSet);
            if (sysSetList.size() > 0) {
                for (SysSet list : sysSetList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(sysSetList);
            //response.setTotal(sysSetMapper.countBy(sysSet));

        }
        return response;
    }

    @Override
    public Response save(Request<SysSet> tParams) {
        Response response = Response.newResponse();
        SysSet sysSet = tParams.getData();

        if(sysSet== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysSet.getPkSysSet();
        String pkDomin = sysSet.getPkDomain();
        SysSet select = null;
        if (primaryKey != null && !primaryKey.equals("") && pkDomin != null && !pkDomin.equals("")) {
            SysSetKey sysSetKey = new SysSetKey();
            sysSetKey.setPkSysSet(primaryKey);
            sysSetKey.setPkDomain(pkDomin);
            select = sysSetMapper.selectByPrimaryKey(sysSetKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                sysSet.setLasteditDate(new Date());
                iReuslt = sysSetMapper.updateByPrimaryKeySelective(sysSet);
            } else {
                sysSet.setPkSysSet(GuidUtils.getGuid());
                sysSet.setCreationDate(new Date());
                sysSet.setLasteditDate(new Date());
                iReuslt = sysSetMapper.insertSelective(sysSet);
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
