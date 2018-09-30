package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.QuestionsItemMapper;
import com.wuxue.api.mapper.QuestionsSubjectMapper;
import com.wuxue.api.mapper.SysDictValuesMapper;
import com.wuxue.api.service.QuestionsItemService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsItem;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("questionsItemService")
public class QuestionsItemServiceImpl implements QuestionsItemService{
    @Autowired
    QuestionsSubjectMapper questionsSubjectMapper;

    @Autowired
    QuestionsItemMapper questionsItemMapper;

    @Autowired
    SysDictValuesMapper sysDictValuesMapper;

    @Override
    public Response delete(Request<QuestionsItem> tParams) {
        Response response = Response.newResponse();
        QuestionsItem questionsItem = tParams.getData();
        if(questionsItem.getId()== null || questionsItem.getId().equals("")){
            return  response.PARAMS_ISNULL();
        }
        String message= "";
        try {
            QuestionsItem questionsItem1 = questionsItemMapper.selectByPrimaryKey(questionsItem.getId());
            questionsItem1.setIsdel(0);
            questionsItemMapper.updateByPrimaryKeySelective(questionsItem1);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<QuestionsItem> tParams) {
        QuestionsItem questionsItem = tParams.getData();
        if(questionsItem== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsItem.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( questionsItemMapper.selectByPrimaryKey(primaryKey));
        }else{
            PageHelper.startPage(questionsItem.getPageNo(),questionsItem.getPageSize());
            List<QuestionsItem> List = questionsItemMapper.select(questionsItem);

            for (QuestionsItem q:List) {
                if(q.getRank()!=null){
                  SysDictValues sysDictValues= sysDictValuesMapper.selectByPrimaryKey(q.getRank());
                  q.put("rank",sysDictValues);
                }
            }

            PageInfo<QuestionsItem> pageInfo = new PageInfo<>(List);
            response.setData(pageInfo);

           // response.setData(questionsItemMapper.select(questionsItem));
           // response.setTotal(questionsItemMapper.countBy(questionsItem));

        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsItem> tParams) {
        QuestionsItem questionsItem = tParams.getData();

        if(questionsItem== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsItem.getId();
        QuestionsItem select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsItemMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = questionsItemMapper.updateByPrimaryKeySelective(questionsItem);
            } else {
                questionsItem.setId(GuidUtils.getGuid());
                questionsItem.setRegdate(new Date());
                questionsItem.setModifydate(new Date());
                iReuslt = questionsItemMapper.insertSelective(questionsItem);
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
