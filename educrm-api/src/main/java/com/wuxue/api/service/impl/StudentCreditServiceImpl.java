package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.StudentCreditMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentCredit;
import com.wuxue.api.service.StudentCreditService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentCreditService")
public class StudentCreditServiceImpl implements StudentCreditService{
    @Autowired
    StudentCreditMapper studentCreditMapper;
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
            iReuslt=studentCreditMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentCredit> tParams) {
        Response response = Response.newResponse();
        StudentCredit studentCredit = tParams.getData();

        if(studentCredit== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentCredit.getPkStudentCredit();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentCredit byPrimaryKey = studentCreditMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<StudentCredit> studentCreditList = studentCreditMapper.select(studentCredit);
            if (studentCreditList.size() > 0) {
                for (StudentCredit list : studentCreditList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(studentCreditList);
            //response.setTotal(studentCreditMapper.countBy(studentCredit));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentCredit> tParams) {
        Response response = Response.newResponse();
        StudentCredit studentCredit = tParams.getData();

        if(studentCredit== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentCredit.getPkStudentCredit();
        StudentCredit select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentCreditMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentCredit.setLasteditDate(new Date());
                iReuslt = studentCreditMapper.updateByPrimaryKeySelective(studentCredit);
            } else {
                studentCredit.setPkStudentCredit(GuidUtils.getGuid());
                studentCredit.setCreationDate(new Date());
                studentCredit.setLasteditDate(new Date());
                iReuslt = studentCreditMapper.insertSelective(studentCredit);
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
