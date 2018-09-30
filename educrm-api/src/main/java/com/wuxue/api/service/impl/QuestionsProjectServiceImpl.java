package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.QuestionsProjectMapper;
import com.wuxue.api.service.QuestionsProjectService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsProject;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("questionsProjectService")
public class QuestionsProjectServiceImpl implements QuestionsProjectService {
    @Autowired
    QuestionsProjectMapper questionsProjectMapper;


    @Override
    public Response find(Request<QuestionsProject> tParams) {
        QuestionsProject questionsProject = tParams.getData();

        if(questionsProject== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsProject.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( questionsProjectMapper.selectByPrimaryKey(primaryKey));
        }else{
            response.setData(questionsProjectMapper.select(questionsProject));
            response.setTotal(questionsProjectMapper.countBy(questionsProject));

        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsProject> tParams) {
        QuestionsProject questionsProject = tParams.getData();

        if(questionsProject== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsProject.getId();
        QuestionsProject select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsProjectMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = questionsProjectMapper.updateByPrimaryKeySelective(questionsProject);
            } else {
                questionsProject.setId(GuidUtils.getGuid());
                questionsProject.setRegdate(new Date());
                questionsProject.setModifydate(new Date());
                iReuslt = questionsProjectMapper.insertSelective(questionsProject);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return Response.newResponse();
        }
        return Response.newResponse().SAVE_FAIL(message);
    }



}
