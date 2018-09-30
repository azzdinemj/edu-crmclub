package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentEduExperienceMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentEduExperience;
import com.wuxue.api.service.StudentEduExperienceService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentEduExperienceService")
public class StudentEduExperienceServiceImpl implements StudentEduExperienceService{
    @Autowired
    StudentEduExperienceMapper studentEduExperienceMapper;
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
            iReuslt=studentEduExperienceMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentEduExperience> tParams) {
        Response response = Response.newResponse();
        StudentEduExperience studentEduExperience = tParams.getData();

        if(studentEduExperience== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentEduExperience.getPkStudentEduExperience();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentEduExperience byPrimaryKey = studentEduExperienceMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentEduExperience.getPageNo(),studentEduExperience.getPageSize());
            List<StudentEduExperience> experienceList = studentEduExperienceMapper.select(studentEduExperience);
            PageInfo page = new PageInfo(experienceList);
            response.setTotal(page.getTotal());
            if (experienceList.size() > 0) {
                for (StudentEduExperience list : experienceList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(experienceList);
            //response.setTotal(studentEduExperienceMapper.countBy(studentEduExperience));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentEduExperience> tParams) {
        Response response = Response.newResponse();
        StudentEduExperience studentEduExperience = tParams.getData();

        if(studentEduExperience== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentEduExperience.getPkStudentEduExperience();
        StudentEduExperience select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentEduExperienceMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentEduExperience.setLasteditDate(new Date());
                iReuslt = studentEduExperienceMapper.updateByPrimaryKeySelective(studentEduExperience);
            } else {
                studentEduExperience.setPkStudentEduExperience(GuidUtils.getGuid());
                studentEduExperience.setCreationDate(new Date());
                studentEduExperience.setLasteditDate(new Date());
                iReuslt = studentEduExperienceMapper.insertSelective(studentEduExperience);
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
