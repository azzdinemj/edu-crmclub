package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.StudentInterviewRecordsMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentInterviewRecords;
import com.wuxue.api.service.StudentInterviewRecordsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentInterviewRecordsService")
public class StudentInterviewRecordsServiceImpl implements StudentInterviewRecordsService{
    @Autowired
    StudentInterviewRecordsMapper studentInterviewRecordsMapper;
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
            iReuslt=studentInterviewRecordsMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentInterviewRecords> tParams) {
        Response response = Response.newResponse();
        StudentInterviewRecords studentInterviewRecords = tParams.getData();

        if(studentInterviewRecords== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentInterviewRecords.getPkStudentInterviewRecords();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentInterviewRecords byPrimaryKey = studentInterviewRecordsMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getSubmitor(),LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getAuditor(),LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCancel(),LinkEntity.CANCEL_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<StudentInterviewRecords> interviewRecordsList = studentInterviewRecordsMapper.select(studentInterviewRecords);
            if (interviewRecordsList.size() > 0) {
                for (StudentInterviewRecords list : interviewRecordsList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                }
            }
            response.setData(interviewRecordsList);
            //response.setTotal(studentInterviewRecordsMapper.countBy(studentInterviewRecords));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentInterviewRecords> tParams) {
        Response response = Response.newResponse();
        StudentInterviewRecords studentInterviewRecords = tParams.getData();

        if(studentInterviewRecords== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentInterviewRecords.getPkStudentInterviewRecords();
        StudentInterviewRecords select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentInterviewRecordsMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentInterviewRecords.setLasteditDate(new Date());
                iReuslt = studentInterviewRecordsMapper.updateByPrimaryKeySelective(studentInterviewRecords);
            } else {
                studentInterviewRecords.setPkStudentInterviewRecords(GuidUtils.getGuid());
                studentInterviewRecords.setCreationDate(new Date());
                studentInterviewRecords.setLasteditDate(new Date());
                iReuslt = studentInterviewRecordsMapper.insertSelective(studentInterviewRecords);
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
