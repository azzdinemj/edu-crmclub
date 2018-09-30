package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ClassinfoActivityMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.ClassinfoActivity;
import com.wuxue.api.service.ClassinfoActivityService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("classinfoActivityService")
public class ClassinfoActivityServiceImpl implements ClassinfoActivityService{
    @Autowired
    ClassinfoActivityMapper classinfoActivityMapper;
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
            iReuslt=classinfoActivityMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<ClassinfoActivity> tParams) {
        Response response = Response.newResponse();
        ClassinfoActivity classinfoActivity = tParams.getData();

        if(classinfoActivity== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classinfoActivity.getPkClassinfoActivity();
        if(primaryKey !=null && !primaryKey.equals("")){
            ClassinfoActivity byPrimaryKey = classinfoActivityMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getSubmitor(),LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getAuditor(),LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCancel(),LinkEntity.CANCEL_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(classinfoActivity.getPageNo(),classinfoActivity.getPageSize());
            List<ClassinfoActivity> activityList = classinfoActivityMapper.select(classinfoActivity);
            PageInfo page = new PageInfo(activityList);
            response.setTotal(page.getTotal());
            if (activityList.size() > 0) {
                for (ClassinfoActivity list : activityList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                }
            }
            response.setData(activityList);
            //response.setTotal(classinfoActivityMapper.countBy(classinfoActivity));
        }
        return response;
    }

    @Override
    public Response save(Request<ClassinfoActivity> tParams) {
        Response response = Response.newResponse();
        ClassinfoActivity classinfoActivity = tParams.getData();

        if(classinfoActivity== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classinfoActivity.getPkClassinfoActivity();
        ClassinfoActivity activity = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            activity = classinfoActivityMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (activity != null) {
                classinfoActivity.setLasteditDate(new Date());
                iReuslt = classinfoActivityMapper.updateByPrimaryKeySelective(classinfoActivity);
            } else {
                classinfoActivity.setPkClassinfoActivity(GuidUtils.getGuid());
                classinfoActivity.setCreationDate(new Date());
                classinfoActivity.setLasteditDate(new Date());
                iReuslt = classinfoActivityMapper.insertSelective(classinfoActivity);
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
