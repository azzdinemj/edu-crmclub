package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ClassTimeMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.ClassTime;
import com.wuxue.api.service.ClassTimeService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("classTimeService")
public class ClassTimeServiceImpl implements ClassTimeService{
    @Autowired
    ClassTimeMapper classTimeMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 0;
        String message= "";
        try {
            ClassTime byprimaryKey = new ClassTime();
            byprimaryKey.setPkClassTime(primaryKey);
            ClassTime classTime = classTimeMapper.selectByPrimaryKey(byprimaryKey);
            if (classTime != null){
                classTime.setIsvalid(0);
                iReuslt=classTimeMapper.updateByPrimaryKeySelective(classTime);
            }

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
    public Response find(Request<ClassTime> tParams) {
        Response response = Response.newResponse();
        ClassTime classTime = tParams.getData();

        if(classTime== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classTime.getPkClassTime();
        if(primaryKey !=null && !primaryKey.equals("")){
            ClassTime byPrimaryKey = classTimeMapper.selectByPrimaryKey(classTime);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(classTime.getPageNo(),classTime.getPageSize());
            classTime.setIsvalid(1);
            List<ClassTime> timeList = classTimeMapper.select(classTime);
            PageInfo page = new PageInfo(timeList);
            response.setTotal(page.getTotal());
            if (timeList.size() > 0) {
                for (ClassTime list : timeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setDivisionKeyValue(list, list.getType(), LinkEntity.DIVISION_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(timeList);
            //response.setTotal(classTimeMapper.countBy(classTime));
        }
        return response;
    }

    @Override
    public Response save(Request<ClassTime> tParams) {
        Response response = Response.newResponse();
        ClassTime classTime = tParams.getData();

        if(classTime== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classTime.getPkClassTime();
        ClassTime select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = classTimeMapper.selectByPrimaryKey(classTime);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                classTime.setLasteditDate(new Date());
                iReuslt = classTimeMapper.updateByPrimaryKeySelective(classTime);
            } else {
                classTime.setPkClassTime(GuidUtils.getGuid());
                classTime.setCreationDate(new Date());
                classTime.setLasteditDate(new Date());
                classTime.setIsvalid(1);
                iReuslt = classTimeMapper.insertSelective(classTime);
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
