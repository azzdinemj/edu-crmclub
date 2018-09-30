package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentBehaviorRecordMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentBehaviorRecord;
import com.wuxue.api.service.StudentBehaviorRecordService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentBehaviorRecordService")
public class StudentBehaviorRecordServiceImpl implements StudentBehaviorRecordService{
    @Autowired
    StudentBehaviorRecordMapper studentBehaviorRecordMapper;
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
            iReuslt=studentBehaviorRecordMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentBehaviorRecord> tParams) {
        Response response = Response.newResponse();
        StudentBehaviorRecord studentBehaviorRecord = tParams.getData();

        if(studentBehaviorRecord== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentBehaviorRecord.getPkStudentBehaviorRecord();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentBehaviorRecord byPrimaryKey = studentBehaviorRecordMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentBehaviorRecord.getPageNo(),studentBehaviorRecord.getPageSize());
            List<StudentBehaviorRecord> recordList = studentBehaviorRecordMapper.select(studentBehaviorRecord);
            PageInfo page = new PageInfo(recordList);
            response.setTotal(page.getTotal());
            if (recordList.size() > 0) {
                for (StudentBehaviorRecord list : recordList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getGrade(),LinkEntity.GTADE_ENTITY);
                }
            }
            response.setData(recordList);
            //response.setTotal(studentBehaviorRecordMapper.countBy(studentBehaviorRecord));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentBehaviorRecord> tParams) {
        Response response = Response.newResponse();
        StudentBehaviorRecord studentBehaviorRecord = tParams.getData();

        if(studentBehaviorRecord== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentBehaviorRecord.getPkStudentBehaviorRecord();
        StudentBehaviorRecord select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentBehaviorRecordMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentBehaviorRecord.setLasteditDate(new Date());
                iReuslt = studentBehaviorRecordMapper.updateByPrimaryKeySelective(studentBehaviorRecord);
            } else {
                studentBehaviorRecord.setPkStudentBehaviorRecord(GuidUtils.getGuid());
                studentBehaviorRecord.setCreationDate(new Date());
                studentBehaviorRecord.setLasteditDate(new Date());
                iReuslt = studentBehaviorRecordMapper.insertSelective(studentBehaviorRecord);
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
