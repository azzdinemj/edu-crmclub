package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.SysDictMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.SysDict;
import com.wuxue.api.service.SysDictService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("sysDictService")
public class SysDictServiceImpl implements SysDictService{
    @Autowired
    SysDictMapper sysDictMapper;
    @Autowired
    UtilsService utilsService;

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
            iReuslt=sysDictMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<SysDict> tParams) {
        Response response = Response.newResponse();
        SysDict sysDict = tParams.getData();

        if(sysDict== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysDict.getPkSysDict();
        if(primaryKey !=null && !primaryKey.equals("")){
            SysDict byPrimaryKey = sysDictMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<SysDict> sysDictList = sysDictMapper.select(sysDict);
            if (sysDictList.size() > 0) {
                for (SysDict list : sysDictList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }

            response.setData(sysDictList);
            //response.setTotal(sysDictMapper.countBy(sysDict));

        }
        return response;
    }

    @Override
    public Response save(Request<SysDict> tParams) {
        Response response = Response.newResponse();
        SysDict sysDict = tParams.getData();

        if(sysDict== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysDict.getPkSysDict();
        SysDict select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = sysDictMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                sysDict.setLasteditDate(new Date());
                iReuslt = sysDictMapper.updateByPrimaryKeySelective(sysDict);
            } else {
                sysDict.setPkSysDict(GuidUtils.getGuid());
                sysDict.setCreationDate(new Date());
                sysDict.setLasteditDate(new Date());
                sysDict.setIsvalid(1);
                iReuslt = sysDictMapper.insertSelective(sysDict);
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
