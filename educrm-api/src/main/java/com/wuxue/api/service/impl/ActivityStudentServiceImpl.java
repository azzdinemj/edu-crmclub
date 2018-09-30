package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.ActivityStudentMapper;
import com.wuxue.api.service.ActivityStudentService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.ActivityStudent;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("activityStudentService")
public class ActivityStudentServiceImpl implements ActivityStudentService {
    @Autowired
    ActivityStudentMapper activityStudentMapper;

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
            iReuslt=activityStudentMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<ActivityStudent> tParams) {
        Response response = Response.newResponse();
        ActivityStudent activityStudent = tParams.getData();

        if(activityStudent== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = activityStudent.getPkActivityStudent();
        if(primaryKey !=null && !primaryKey.equals("")){
            activityStudent = activityStudentMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(activityStudent,activityStudent.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(activityStudent,activityStudent.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(activityStudent);
        }else{
            List<ActivityStudent> list = activityStudentMapper.select(activityStudent);
            for (ActivityStudent activityStudentEntity : list) {
                utilsService.setSysUserKeyValue(activityStudentEntity,activityStudentEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(activityStudentEntity,activityStudentEntity.getModifier(),LinkEntity.MODIFIER_ENTITY);
                utilsService.setClassInfoKeyValue(activityStudentEntity,activityStudentEntity.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
            }
            response.setData(list);
        }
        return response;
    }

    @Override
    public Response save(Request<ActivityStudent> tParams) {
        Response response = Response.newResponse();
        ActivityStudent activityStudent = tParams.getData();

        if(activityStudent== null && activityStudent.getPkStudent() == null && activityStudent.getPkClassinfo() == null ){
            return  response.PARAMS_ISNULL();
        }
//        String primaryKey = activityStudent.getPkActivityStudent();
        Boolean flag = true;
        ActivityStudent select = new ActivityStudent();
        select.setPkStudent(activityStudent.getPkStudent());
        select.setPkClassinfo(activityStudent.getPkClassinfo());
        List<ActivityStudent> activityStudentList = activityStudentMapper.select(select);
        if (activityStudentList.size() > 0) {
            if(activityStudentList.get(0).getScore() == null && activityStudent.getScore() != null ||
                    activityStudentList.get(0).getEvaluate() == null && activityStudent.getEvaluate() != null){
                flag = false;
            }
//            if(){
//                flag = false;
//            }
            activityStudent.setCreator(null);
            activityStudent.setPkActivityStudent(activityStudentList.get(0).getPkActivityStudent());
        }
//        if (primaryKey != null && !primaryKey.equals("")) {
//            select = activityStudentMapper.selectByPrimaryKey(primaryKey);
//        }
        int iReuslt = 1;
        String message= "";
        try {
            if (flag == false) {
                activityStudent.setLasteditDate(new Date());
                iReuslt = activityStudentMapper.updateByPrimaryKeySelective(activityStudent);
            } else {
                activityStudent.setCreationDate(new Date());
                activityStudent.setLasteditDate(new Date());
                iReuslt = activityStudentMapper.insertSelective(activityStudent);
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
