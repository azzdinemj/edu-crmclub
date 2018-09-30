package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentActivityExpMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentActivityExp;
import com.wuxue.api.service.StudentActivityExpService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentActivityExpService")
public class StudentActivityExpServiceImpl implements StudentActivityExpService{
    @Autowired
    StudentActivityExpMapper studentActivityExpMapper;
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
            iReuslt=studentActivityExpMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentActivityExp> tParams) {
        Response response = Response.newResponse();
        StudentActivityExp studentActivityExp = tParams.getData();

        if(studentActivityExp== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentActivityExp.getPkStudentActivityExp();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentActivityExp byPrimaryKey = studentActivityExpMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentActivityExp.getPageNo(),studentActivityExp.getPageSize());
            List<StudentActivityExp> activityExpList = studentActivityExpMapper.select(studentActivityExp);
            PageInfo page = new PageInfo(activityExpList);
            response.setTotal(page.getTotal());
            if (activityExpList.size() > 0) {
                for (StudentActivityExp list : activityExpList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(activityExpList);
            //response.setTotal(studentActivityExpMapper.countBy(studentActivityExp));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentActivityExp> tParams) {
        Response response = Response.newResponse();
        StudentActivityExp studentActivityExp = tParams.getData();

        if(studentActivityExp== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentActivityExp.getPkStudentActivityExp();
        StudentActivityExp select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentActivityExpMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentActivityExp.setLasteditDate(new Date());
                iReuslt = studentActivityExpMapper.updateByPrimaryKeySelective(studentActivityExp);
            } else {
                studentActivityExp.setPkStudentActivityExp(GuidUtils.getGuid());
                studentActivityExp.setCreationDate(new Date());
                studentActivityExp.setLasteditDate(new Date());
                iReuslt = studentActivityExpMapper.insertSelective(studentActivityExp);
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
