package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.QuestionsItemMapper;
import com.wuxue.api.mapper.QuestionsTestMapper;
import com.wuxue.api.service.QuestionsTestService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.QuestionsTest;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("questionsTestService")
public class QuestionsTestServiceImpl implements QuestionsTestService {

    @Autowired
    QuestionsTestMapper questionsTestMapper;
    @Autowired
    QuestionsItemMapper questionsItemMapper;

    @Override
    public Response find(Request<QuestionsTest> tParams) {
        QuestionsTest questionsTest = tParams.getData();
        if(questionsTest== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsTest.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( questionsTestMapper.selectByPrimaryKey(primaryKey));
        }else{
            if(questionsTest.getType()==0){
                PageHelper.startPage(questionsTest.getPageNo(),questionsTest.getPageSize());
                List<QuestionsTest> List = questionsTestMapper.select(questionsTest);
                if(List.size()>0){
                    for(QuestionsTest q:List){
                        q.setType(1);
                        int  num=questionsTestMapper.countBy(q);
                        q.put("QtSize",num);
                    }
                }
                PageInfo<QuestionsTest> pageInfo = new PageInfo<>(List);
                response.setData(pageInfo);
            }else if(questionsTest.getType()==1){

                PageHelper.startPage(questionsTest.getPageNo(),questionsTest.getPageSize());
                List<QuestionsTest> List = questionsTestMapper.select(questionsTest);
                if(List.size()>0){
                    for(QuestionsTest q:List){
                        QuestionsItem questionsItem=questionsItemMapper.selectByPrimaryKey(q.getPkQuestionsItem());
                        q.put(Light.QUESTIONS_ITEM,questionsItem);
                    }
                }
                PageInfo<QuestionsTest> pageInfo = new PageInfo<>(List);
                response.setData(pageInfo);
            }

        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsTest> tParams) {
        QuestionsTest questionsTest = tParams.getData();

        if(questionsTest== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsTest.getId();
        QuestionsTest select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsTestMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = questionsTestMapper.updateByPrimaryKeySelective(questionsTest);
            } else {
                questionsTest.setId(GuidUtils.getGuid());
                questionsTest.setCreateDate(new Date());
                iReuslt = questionsTestMapper.insertSelective(questionsTest);
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
