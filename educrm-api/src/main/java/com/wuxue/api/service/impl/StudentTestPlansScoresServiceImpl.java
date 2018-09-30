package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.StudentTestPlansScoresMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentTestPlansScores;
import com.wuxue.api.service.StudentTestPlansScoresService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentTestPlansScoresService")
public class StudentTestPlansScoresServiceImpl implements StudentTestPlansScoresService{
    @Autowired
    StudentTestPlansScoresMapper studentTestPlansScoresMapper;
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
            iReuslt=studentTestPlansScoresMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentTestPlansScores> tParams) {
        Response response = Response.newResponse();
        StudentTestPlansScores studentTestPlansScores = tParams.getData();

        if(studentTestPlansScores== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentTestPlansScores.getPkStudentTestPlansScores();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentTestPlansScores byPrimaryKey = studentTestPlansScoresMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<StudentTestPlansScores> plansScoresList = studentTestPlansScoresMapper.select(studentTestPlansScores);
            if (plansScoresList.size() > 0) {
                for (StudentTestPlansScores list : plansScoresList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(plansScoresList);
            //response.setTotal(studentTestPlansScoresMapper.countBy(studentTestPlansScores));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentTestPlansScores> tParams) {
        Response response = Response.newResponse();
        StudentTestPlansScores studentTestPlansScores = tParams.getData();

        if(studentTestPlansScores== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentTestPlansScores.getPkStudentTestPlansScores();
        StudentTestPlansScores select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentTestPlansScoresMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = studentTestPlansScoresMapper.updateByPrimaryKeySelective(studentTestPlansScores);
            } else {
                studentTestPlansScores.setPkStudentTestPlansScores(GuidUtils.getGuid());
                studentTestPlansScores.setCreationDate(new Date());
                studentTestPlansScores.setLasteditDate(new Date());
                iReuslt = studentTestPlansScoresMapper.insertSelective(studentTestPlansScores);
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
