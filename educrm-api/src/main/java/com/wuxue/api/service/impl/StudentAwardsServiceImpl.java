package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentAwardsMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentAwards;
import com.wuxue.api.service.StudentAwardsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentAwardsService")
public class StudentAwardsServiceImpl implements StudentAwardsService{
    @Autowired
    StudentAwardsMapper studentAwardsMapper;
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
            iReuslt=studentAwardsMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentAwards> tParams) {
        Response response = Response.newResponse();
        StudentAwards studentAwards = tParams.getData();

        if(studentAwards== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentAwards.getPkStudentAwards();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentAwards byPrimaryKey = studentAwardsMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentAwards.getPageNo(),studentAwards.getPageSize());
            List<StudentAwards> studentAwardsList = studentAwardsMapper.select(studentAwards);
            PageInfo page = new PageInfo(studentAwardsList);
            response.setTotal(page.getTotal());
            if (studentAwardsList.size() > 0) {
                for (StudentAwards list : studentAwardsList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getGrade(),LinkEntity.GTADE_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getActivityLevel(),LinkEntity.STU_AWARDS_ENTITY);
                }
            }

            response.setData(studentAwardsList);
            //response.setTotal(studentAwardsMapper.countBy(studentAwards));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentAwards> tParams) {
        Response response = Response.newResponse();
        StudentAwards studentAwards = tParams.getData();

        if(studentAwards== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentAwards.getPkStudentAwards();
        StudentAwards select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentAwardsMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentAwards.setLasteditDate(new Date());
                iReuslt = studentAwardsMapper.updateByPrimaryKeySelective(studentAwards);
            } else {
                studentAwards.setPkStudentAwards(GuidUtils.getGuid());
                studentAwards.setCreationDate(new Date());
                studentAwards.setLasteditDate(new Date());
                iReuslt = studentAwardsMapper.insertSelective(studentAwards);
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
