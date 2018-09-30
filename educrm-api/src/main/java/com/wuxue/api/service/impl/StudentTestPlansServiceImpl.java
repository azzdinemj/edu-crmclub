package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.StudentTestPlansMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentTestPlans;
import com.wuxue.api.service.StudentTestPlansService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentTestPlansService")
public class StudentTestPlansServiceImpl implements StudentTestPlansService{
    @Autowired
    StudentTestPlansMapper studentTestPlansMapper;
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
            iReuslt=studentTestPlansMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentTestPlans> tParams) {
        Response response = Response.newResponse();
        StudentTestPlans studentTestPlans = tParams.getData();

        if(studentTestPlans== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentTestPlans.getPkStudentTestPlans();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentTestPlans byPrimaryKey = studentTestPlansMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<StudentTestPlans> testPlansList = studentTestPlansMapper.select(studentTestPlans);
            if (testPlansList.size() > 0) {
                for (StudentTestPlans list : testPlansList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(testPlansList);
            //response.setTotal(studentTestPlansMapper.countBy(studentTestPlans));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentTestPlans> tParams) {
        Response response = Response.newResponse();
        StudentTestPlans studentTestPlans = tParams.getData();

        if(studentTestPlans== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentTestPlans.getPkStudentTestPlans();
        StudentTestPlans select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentTestPlansMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentTestPlans.setLasteditDate(new Date());
                iReuslt = studentTestPlansMapper.updateByPrimaryKeySelective(studentTestPlans);
            } else {
                studentTestPlans.setPkStudentTestPlans(GuidUtils.getGuid());
                studentTestPlans.setCreationDate(new Date());
                studentTestPlans.setLasteditDate(new Date());
                iReuslt = studentTestPlansMapper.insertSelective(studentTestPlans);
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
