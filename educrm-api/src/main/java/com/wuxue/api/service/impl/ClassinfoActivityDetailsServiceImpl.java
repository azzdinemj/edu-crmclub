package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.ClassinfoActivityDetailsMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.api.service.ClassinfoActivityDetailsService;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("classinfoActivityDetailsService")
public class ClassinfoActivityDetailsServiceImpl implements ClassinfoActivityDetailsService{
    @Autowired
    ClassinfoActivityDetailsMapper classinfoActivityDetailsMapper;
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
            iReuslt=classinfoActivityDetailsMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<ClassinfoActivityDetails> tParams) {
        Response response = Response.newResponse();
        ClassinfoActivityDetails classinfoActivityDetails = tParams.getData();

        if(classinfoActivityDetails== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classinfoActivityDetails.getPkClassActivityDetails();
        if(primaryKey !=null && !primaryKey.equals("")){
            ClassinfoActivityDetails byPrimaryKey = classinfoActivityDetailsMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<ClassinfoActivityDetails> activityDetailsList = classinfoActivityDetailsMapper.select(classinfoActivityDetails);
            if (activityDetailsList.size() > 0) {
                for (ClassinfoActivityDetails list : activityDetailsList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(activityDetailsList);
            //response.setTotal(classinfoActivityDetailsMapper.countBy(classinfoActivityDetails));
        }
        return response;
    }

    @Override
    public Response save(Request<ClassinfoActivityDetails> tParams) {
        Response response = Response.newResponse();
        ClassinfoActivityDetails classinfoActivityDetails = tParams.getData();

        if(classinfoActivityDetails== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classinfoActivityDetails.getPkClassActivityDetails();
        ClassinfoActivityDetails details = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            details = classinfoActivityDetailsMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (details != null) {
                classinfoActivityDetails.setLasteditDate(new Date());
                iReuslt = classinfoActivityDetailsMapper.updateByPrimaryKeySelective(classinfoActivityDetails);
            } else {
                classinfoActivityDetails.setPkClassActivityDetails(GuidUtils.getGuid());
                classinfoActivityDetails.setCreationDate(new Date());
                classinfoActivityDetails.setLasteditDate(new Date());
                iReuslt = classinfoActivityDetailsMapper.insertSelective(classinfoActivityDetails);
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
