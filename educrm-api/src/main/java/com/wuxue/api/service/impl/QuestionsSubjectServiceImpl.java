package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.QuestionsSubjectMapper;
import com.wuxue.api.service.QuestionsSubjectService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsSubject;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionsSubjectService")
public class QuestionsSubjectServiceImpl implements QuestionsSubjectService{
    @Autowired
    QuestionsSubjectMapper questionsSubjectMapper;


    @Override
    public Response find(Request<QuestionsSubject> tParams) {
        QuestionsSubject questionsSubject = tParams.getData();

        if(questionsSubject== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsSubject.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( questionsSubjectMapper.selectByPrimaryKey(primaryKey));
        }else{
            List<QuestionsSubject> list=questionsSubjectMapper.select(questionsSubject);
//            if(list.size()>0){
//                for (QuestionsSubject q:list) {
//                    if(q.getPid().equals("")||q.getPid()==null){
//
//                    }else{
//                        QuestionsSubject questionsSubject1=questionsSubjectMapper.selectByPrimaryKey(q.getPid());
//                        q.put("pidName",questionsSubject1.getName());
//                    }
//
//                }
//            }
            response.setData(list);
            response.setTotal(questionsSubjectMapper.countBy(questionsSubject));

        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsSubject> tParams) {
        QuestionsSubject questionsSubject = tParams.getData();

        if(questionsSubject== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsSubject.getId();
        QuestionsSubject select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsSubjectMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = questionsSubjectMapper.updateByPrimaryKeySelective(questionsSubject);
            } else {
                questionsSubject.setId(GuidUtils.getGuid());
                iReuslt = questionsSubjectMapper.insertSelective(questionsSubject);
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


    @Override
    public Response delete(Request<String> tParams) {
        return null;
    }

}
