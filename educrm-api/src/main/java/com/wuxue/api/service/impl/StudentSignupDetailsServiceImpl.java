package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.StudentSignupDetailsMapper;
import com.wuxue.api.service.StudentSignupDetailsService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentSignupDetails;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentSignupDetailsService")
public class StudentSignupDetailsServiceImpl implements StudentSignupDetailsService{
    @Autowired
    StudentSignupDetailsMapper studentSignupDetailsMapper;
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
            iReuslt=studentSignupDetailsMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentSignupDetails> tParams) {
        Response response = Response.newResponse();
        StudentSignupDetails studentSignupDetails = tParams.getData();

        if(studentSignupDetails== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentSignupDetails.getPkStudentSignupDetails();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentSignupDetails byPrimaryKey = studentSignupDetailsMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysDictKeyValue(byPrimaryKey,byPrimaryKey.getCurrency(),LinkEntity.CURRENCY_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<StudentSignupDetails> detailsList = studentSignupDetailsMapper.select(studentSignupDetails);
            if (detailsList.size()>0){
                for (StudentSignupDetails details : detailsList) {
                    utilsService.setSysDictKeyValue(details,details.getCurrency(),LinkEntity.CURRENCY_ENTITY);
                }
            }

            response.setData(detailsList);
            //response.setTotal(studentSignupDetailsMapper.countBy(studentSignupDetails));
        }
        return response;
    }

    @Override
    public Response save(Request<StudentSignupDetails> tParams) {
        Response response = Response.newResponse();
        StudentSignupDetails studentSignupDetails = tParams.getData();

        if(studentSignupDetails== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentSignupDetails.getPkStudentSignupDetails();
        StudentSignupDetails select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentSignupDetailsMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = studentSignupDetailsMapper.updateByPrimaryKeySelective(studentSignupDetails);
            } else {
                studentSignupDetails.setPkStudentSignupDetails(GuidUtils.getGuid());
                iReuslt = studentSignupDetailsMapper.insertSelective(studentSignupDetails);
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
