package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentInterviewMapper;
import com.wuxue.api.mapper.TaskMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.CommonUtils;
import com.wuxue.model.*;
import com.wuxue.api.service.StudentInterviewService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentInterviewService")
public class StudentInterviewServiceImpl implements StudentInterviewService{
    @Autowired
    StudentInterviewMapper studentInterviewMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    TaskMapper taskMapper;

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
            iReuslt=studentInterviewMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentInterview> tParams) {
        Response response = Response.newResponse();
        StudentInterview studentInterview = tParams.getData();

        if(studentInterview== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentInterview.getPkStudentInterview();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentInterview byPrimaryKey = studentInterviewMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getSubmitor(),LinkEntity.SUBMITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getAuditor(),LinkEntity.AUDITOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCancel(),LinkEntity.CANCEL_ENTITY);
            utilsService.setStudentKeyValue(byPrimaryKey,byPrimaryKey.getPkStudent(), LinkEntity.STUDENT_ENTITY);
            utilsService.setEmployeeKeyValue(byPrimaryKey, byPrimaryKey.getPkEmployee(), LinkEntity.USER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentInterview.getPageNo(),studentInterview.getPageSize());
            List<StudentInterview> interviewList = studentInterviewMapper.select(studentInterview);
            PageInfo page = new PageInfo(interviewList);
            response.setTotal(page.getTotal());
            if (interviewList.size() > 0) {
                for (StudentInterview list : interviewList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setStudentKeyValue(list, list.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getSubmitor(), LinkEntity.SUBMITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getAuditor(), LinkEntity.AUDITOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCancel(), LinkEntity.CANCEL_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getPkEmployee(), LinkEntity.USER_ENTITY);
                    utilsService.setSysDictKeyValue(list,list.getCaption(),LinkEntity.SYSDIC_ENTITY);
                }
            }
            response.setData(interviewList);
            //response.setTotal(studentInterviewMapper.countBy(studentInterview));
        }
        return response;
    }

    @Override
    public Response save(Request<StudentInterview> tParams) {
        Response response = Response.newResponse();
        StudentInterview studentInterview = tParams.getData();

        if(studentInterview== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentInterview.getPkStudentInterview();
        StudentInterview select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentInterviewMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentInterview.setModifier(tParams.getCurrentUser());
                studentInterview.setLasteditDate(new Date());
                iReuslt = studentInterviewMapper.updateByPrimaryKeySelective(studentInterview);
            } else {
//                studentInterview.setPkStudentInterview(GuidUtils.getGuid());
                studentInterview.setCreationDate(new Date());
                studentInterview.setCreator(tParams.getCurrentUser());
                studentInterview.setModifier(tParams.getCurrentUser());
                studentInterview.setLasteditDate(new Date());
                iReuslt = studentInterviewMapper.insertSelective(studentInterview);

                Task task = new Task();
                task.setPkTask(GuidUtils.getGuid());
                task.setIsdel(1);
                task.setContent("新生面试");
                if(studentInterview.getPkEmployee() != null){
                    task.setPkUser(studentInterview.getPkEmployee());
                }

                if(studentInterview.getDate() != null){
                    task.setEndDate(studentInterview.getDate());
                }
//                0财务 1其他
                task.setType(1);
                task.setCreator(tParams.getCurrentUser());
                task.setModifier(tParams.getCurrentUser());
                task.setCreationDate(new Date());
                task.setLasteditDate(new Date());
                taskMapper.insertSelective(task);

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

    @Override
    public Response audit(Request<StudentInterview> tParams) {
        Response response = Response.newResponse();
        StudentInterview studentInterview = tParams.getData();


        if(studentInterview== null){
            return  response.PARAMS_ISNULL();
        }

        String primaryKey = studentInterview.getPkStudentInterview();
        int iReuslt = 0;
        String message= "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                StudentInterview byPrimaryKey = studentInterviewMapper.selectByPrimaryKey(primaryKey);

                Response verification = CommonUtils.auditVerification(byPrimaryKey);
                if(verification == null) {
                    if (byPrimaryKey.getIsaudit() == null || "".equals(byPrimaryKey.getIsaudit()) || byPrimaryKey.getIsaudit() !=2) {
                        if(studentInterview.getIsaudit()==1){
                            byPrimaryKey.setAuditDate(new Date());
                            byPrimaryKey.setIsaudit(studentInterview.getIsaudit());
                            byPrimaryKey.setAuditor(studentInterview.getAuditor());
//                            byPrimaryKey.setModifier(studentInterview.getAuditor());
                            byPrimaryKey.setLasteditDate(new Date());
                            byPrimaryKey.setMemo(studentInterview.getMemo());
                        }
                        if(studentInterview.getIsaudit()==0){
                            byPrimaryKey.setAuditDate(new Date());
                            byPrimaryKey.setIssubmit(0);
                            byPrimaryKey.setIsaudit(studentInterview.getIsaudit());
                            byPrimaryKey.setAuditor(studentInterview.getAuditor());
//                            byPrimaryKey.setModifier(studentInterview.getAuditor());
                            byPrimaryKey.setLasteditDate(new Date());
                            byPrimaryKey.setMemo(studentInterview.getMemo());
                        }

                        iReuslt = studentInterviewMapper.updateByPrimaryKeySelective(byPrimaryKey);
                    } else {
                        return response.SAVE_DOUBLE();
                    }
                }
            }catch (Exception ex){
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        }else{
            return  response.PARAMS_ISNULL();
        }
        if(iReuslt>0) {
            return response;
        }

        return response.AUDIT_SUBMIT();
    }

    @Override
    public Response cancel(Request<StudentInterview> tParams) {
        Response response = Response.newResponse();
        StudentInterview studentInterview = tParams.getData();


        if(studentInterview== null){
            return  response.PARAMS_ISNULL();
        }

        String primaryKey = studentInterview.getPkStudentInterview();
        int iReuslt = 1;
        String message= "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                StudentInterview byPrimaryKey = studentInterviewMapper.selectByPrimaryKey(primaryKey);

                Response verification = CommonUtils.cnacelVerification(byPrimaryKey);
                if(verification == null) {
                    if (byPrimaryKey.getCancel() == null || "".equals(byPrimaryKey.getCancel())) {
                        byPrimaryKey.setCancel(studentInterview.getCancel());
                        byPrimaryKey.setIscancel(1);
                        byPrimaryKey.setCancelDate(new Date());
                        byPrimaryKey.setModifier(studentInterview.getCancel());
                        byPrimaryKey.setLasteditDate(new Date());
                        studentInterviewMapper.updateByPrimaryKeySelective(byPrimaryKey);
                    } else {
                        return response.SAVE_DOUBLE();
                    }
                }
            }catch (Exception ex){
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        }else{
            return  response.PARAMS_ISNULL();
        }
        if(iReuslt>0) {
            return response;
        }

        return response.SAVE_FAIL(message);
    }

    @Override
    public Response submit(Request<StudentInterview> tParams) {
        Response response = Response.newResponse();
        StudentInterview studentInterview = tParams.getData();


        if(studentInterview== null){
            return  response.PARAMS_ISNULL();
        }

        String primaryKey = studentInterview.getPkStudentInterview();
        int iReuslt = 0;
        String message= "";
        if (primaryKey != null && !primaryKey.equals("")) {
            try {
                StudentInterview byPrimaryKey = studentInterviewMapper.selectByPrimaryKey(primaryKey);

                Response verification = CommonUtils.submitVerification(byPrimaryKey);
                if(verification == null) {
                    if (byPrimaryKey.getIssubmit() == null || byPrimaryKey.getIssubmit()!=1 || byPrimaryKey.getIssubmit().equals("")) {
                        byPrimaryKey.setSubmitor(studentInterview.getSubmitor());
                        byPrimaryKey.setIssubmit(1);
                        byPrimaryKey.setSubmitDate(new Date());
                        byPrimaryKey.setModifier(studentInterview.getSubmitor());
                        byPrimaryKey.setLasteditDate(new Date());
                        iReuslt = studentInterviewMapper.updateByPrimaryKeySelective(byPrimaryKey);
                    } else {
                        return response.SAVE_DOUBLE();
                    }
                }
            }catch (Exception ex){
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        }else{
            return  response.PARAMS_ISNULL();
        }
        if(iReuslt>0) {
            return response;
        }

        return response.SUBMIT_FAIL(message);
    }
}
