package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentInterviewRecordMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentInterviewRecord;
import com.wuxue.api.service.StudentInterviewRecordService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentInterviewRecordService")
public class StudentInterviewRecordServiceImpl implements StudentInterviewRecordService{
    @Autowired
    StudentInterviewRecordMapper studentInterviewRecordMapper;
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
            iReuslt=studentInterviewRecordMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentInterviewRecord> tParams) {
        Response response = Response.newResponse();
        StudentInterviewRecord studentInterviewRecord = tParams.getData();

        if(studentInterviewRecord== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentInterviewRecord.getPkStudentInterviewRecord();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentInterviewRecord byPrimaryKey = studentInterviewRecordMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getSubmitor(),LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getAuditor(),LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCancel(),LinkEntity.CANCEL_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentInterviewRecord.getPageNo(),studentInterviewRecord.getPageSize());
            List<StudentInterviewRecord> interviewRecordList = studentInterviewRecordMapper.select(studentInterviewRecord);
            PageInfo page = new PageInfo(interviewRecordList);
            response.setTotal(page.getTotal());
            if (interviewRecordList.size() > 0) {
                for (StudentInterviewRecord list : interviewRecordList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getGrade(),LinkEntity.GTADE_ENTITY);
                }
            }
            response.setData(interviewRecordList);
            //response.setTotal(studentInterviewRecordMapper.countBy(studentInterviewRecord));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentInterviewRecord> tParams) {
        Response response = Response.newResponse();
        StudentInterviewRecord studentInterviewRecord = tParams.getData();

        if(studentInterviewRecord== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentInterviewRecord.getPkStudentInterviewRecord();
        StudentInterviewRecord select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentInterviewRecordMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentInterviewRecord.setLasteditDate(new Date());
                studentInterviewRecord.setModifier(tParams.getCurrentUser());
                iReuslt = studentInterviewRecordMapper.updateByPrimaryKeySelective(studentInterviewRecord);
            } else {
                studentInterviewRecord.setPkStudentInterviewRecord(GuidUtils.getGuid());
                studentInterviewRecord.setCreator(tParams.getCurrentUser());
                studentInterviewRecord.setModifier(tParams.getCurrentUser());
                studentInterviewRecord.setCreationDate(new Date());
                studentInterviewRecord.setLasteditDate(new Date());
                iReuslt = studentInterviewRecordMapper.insertSelective(studentInterviewRecord);
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
