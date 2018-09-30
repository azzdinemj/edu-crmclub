package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.SysDictMapper;
import com.wuxue.api.mapper.SysDictValuesMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.base.KeyValue;
import com.wuxue.model.SysDict;
import com.wuxue.model.SysDictValues;
import com.wuxue.api.service.SysDictValuesService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("sysDictValuesService")
public class SysDictValuesServiceImpl implements SysDictValuesService{
    @Autowired
    SysDictValuesMapper sysDictValuesMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    private SysDictMapper sysDictMapper;

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
            SysDictValues sysDictValues = sysDictValuesMapper.selectByPrimaryKey(primaryKey);
            if (sysDictValues != null){
                sysDictValues.setIsvalid(0);
                iReuslt=sysDictValuesMapper.updateByPrimaryKeySelective(sysDictValues);
            }

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
    public Response find(Request<SysDictValues> tParams) {
        Response response = Response.newResponse();
        SysDictValues sysDictValues = tParams.getData();

        if(sysDictValues== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysDictValues.getPkSysDictValues();
        if(primaryKey !=null && !primaryKey.equals("")){
            SysDictValues byPrimaryKey = sysDictValuesMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            SysDict sysDict = sysDictMapper.selectByPrimaryKey(byPrimaryKey.getPkSysDict());
            byPrimaryKey.put(LinkEntity.SYSDIC_ENTITY,sysDict);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(sysDictValues.getPageNo(),sysDictValues.getPageSize());
            List<SysDictValues> dictValuesList = sysDictValuesMapper.select(sysDictValues);
            PageInfo page = new PageInfo(dictValuesList);
            response.setTotal(page.getTotal());
            if (dictValuesList.size() > 0) {
                for (SysDictValues list : dictValuesList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }

            response.setData(dictValuesList);
            //response.setTotal(sysDictValuesMapper.countBy(sysDictValues));

        }
        return response;
    }

    @Override
    public Response save(Request<SysDictValues> tParams) {
        Response response = Response.newResponse();
        SysDictValues sysDictValues = tParams.getData();

        if(sysDictValues== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysDictValues.getPkSysDictValues();
        SysDictValues select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = sysDictValuesMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                sysDictValues.setLasteditDate(new Date());
                iReuslt = sysDictValuesMapper.updateByPrimaryKeySelective(sysDictValues);
            } else {
                sysDictValues.setPkSysDictValues(GuidUtils.getGuid());
                sysDictValues.setCreationDate(new Date());
                sysDictValues.setLasteditDate(new Date());
                sysDictValues.setIsvalid(1);
                iReuslt = sysDictValuesMapper.insertSelective(sysDictValues);
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
    public Response findValue(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        String message;

        if(primaryKey != null && !"".equals(primaryKey)){
            SysDictValues sysDictValues = new SysDictValues();
            sysDictValues.setPkSysDict(primaryKey);
            try {
                List<SysDictValues> sysDictValuesList = sysDictValuesMapper.select(sysDictValues);
                response.setData(sysDictValuesList);
            }catch (Exception ex){
                message = ex.getMessage();
                return response.FIND_FAIL(message);
            }
        }else{
            return  response.PARAMS_ISNULL();
        }
        return response;
    }

    @Override
    public List<SysDictValues> selectBySysDict(String pkSysDict) {

        List<SysDictValues> list = sysDictValuesMapper.selectBySysDict(pkSysDict);
        return list;
    }

    @Override
    public List<KeyValue> findWorkType(String pkStudent) {

        List<KeyValue> list = sysDictValuesMapper.selectWorkType(pkStudent);
        return list;
    }
}
