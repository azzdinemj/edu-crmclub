package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.EmployeeHomeInfoMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.EmployeeHomeInfo;
import com.wuxue.api.service.EmployeeHomeInfoService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("employeeHomeInfoService")
public class EmployeeHomeInfoServiceImpl implements EmployeeHomeInfoService{
    @Autowired
    EmployeeHomeInfoMapper employeeHomeInfoMapper;
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
            iReuslt=employeeHomeInfoMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<EmployeeHomeInfo> tParams) {
        Response response = Response.newResponse();
        EmployeeHomeInfo employeeHomeInfo = tParams.getData();

        if(employeeHomeInfo== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = employeeHomeInfo.getPkEmployeeHomeInfo();
        if(primaryKey !=null && !primaryKey.equals("")){
            EmployeeHomeInfo byPrimaryKey = employeeHomeInfoMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(employeeHomeInfo.getPageNo(),employeeHomeInfo.getPageSize());
            List<EmployeeHomeInfo> homeInfoList = employeeHomeInfoMapper.select(employeeHomeInfo);
            PageInfo pageInfo = new PageInfo(homeInfoList);
            response.setTotal(pageInfo.getTotal());
            if (homeInfoList.size() > 0) {
                for (EmployeeHomeInfo list : homeInfoList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(homeInfoList);
            //response.setTotal(employeeHomeInfoMapper.countBy(employeeHomeInfo));

        }
        return response;
    }

    @Override
    public Response save(Request<EmployeeHomeInfo> tParams) {
        Response response = Response.newResponse();
        EmployeeHomeInfo employeeHomeInfo = tParams.getData();

        if(employeeHomeInfo== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = employeeHomeInfo.getPkEmployeeHomeInfo();
        EmployeeHomeInfo select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = employeeHomeInfoMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                employeeHomeInfo.setLasteditDate(new Date());
                iReuslt = employeeHomeInfoMapper.updateByPrimaryKeySelective(employeeHomeInfo);
            } else {
                employeeHomeInfo.setPkEmployeeHomeInfo(GuidUtils.getGuid());
                employeeHomeInfo.setCreationDate(new Date());
                employeeHomeInfo.setLasteditDate(new Date());
                iReuslt = employeeHomeInfoMapper.insertSelective(employeeHomeInfo);
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
