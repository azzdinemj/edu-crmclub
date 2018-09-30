package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentPlansMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentPlans;
import com.wuxue.api.service.StudentPlansService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentPlansService")
public class StudentPlansServiceImpl implements StudentPlansService{
    @Autowired
    StudentPlansMapper studentPlansMapper;
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
            iReuslt=studentPlansMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentPlans> tParams) {
        Response response = Response.newResponse();
        StudentPlans studentPlans = tParams.getData();

        if(studentPlans== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentPlans.getPkStudentPlans();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentPlans byPrimaryKey = studentPlansMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentPlans.getPageNo(),studentPlans.getPageSize());
            List<StudentPlans> studentPlansList = studentPlansMapper.select(studentPlans);
            PageInfo page = new PageInfo(studentPlansList);
            response.setTotal(page.getTotal());
            if (studentPlansList.size() > 0) {
                for (StudentPlans list : studentPlansList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getTargetCountries(),LinkEntity.COUNTRY_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getSubjectsLike(),LinkEntity.COURSE_ENTITY);
                }
            }
            response.setData(studentPlansList);
            //response.setTotal(studentPlansMapper.countBy(studentPlans));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentPlans> tParams) {
        Response response = Response.newResponse();
        StudentPlans studentPlans = tParams.getData();

        if(studentPlans== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentPlans.getPkStudentPlans();
        StudentPlans select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentPlansMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentPlans.setLasteditDate(new Date());
                iReuslt = studentPlansMapper.updateByPrimaryKeySelective(studentPlans);
            } else {
                studentPlans.setPkStudentPlans(GuidUtils.getGuid());
                studentPlans.setCreationDate(new Date());
                studentPlans.setLasteditDate(new Date());
                iReuslt = studentPlansMapper.insertSelective(studentPlans);
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
