package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.QuestionsPaperMapper;
import com.wuxue.api.service.QuestionsPaperService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsPaper;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("questionsPaperService")
public class QuestionsPaperServiceImpl implements QuestionsPaperService {

    @Autowired
    QuestionsPaperMapper questionsPaperMapper;

    @Override
    public Response find(Request<QuestionsPaper> tParams) {
        QuestionsPaper questionsPaper = tParams.getData();
        if(questionsPaper== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsPaper.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( questionsPaperMapper.selectByPrimaryKey(primaryKey));
        }else{
            PageHelper.startPage(questionsPaper.getPageNo(),questionsPaper.getPageSize());
            List<QuestionsPaper> List = questionsPaperMapper.select(questionsPaper);
            PageInfo<QuestionsPaper> pageInfo = new PageInfo<>(List);
            response.setData(pageInfo);

           // response.setData(questionsItemMapper.select(questionsItem));
           // response.setTotal(questionsItemMapper.countBy(questionsItem));

        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsPaper> tParams) {
        QuestionsPaper QuestionsPaper = tParams.getData();

        if(QuestionsPaper== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = QuestionsPaper.getId();
        QuestionsPaper select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsPaperMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = questionsPaperMapper.updateByPrimaryKeySelective(QuestionsPaper);
            } else {
                QuestionsPaper.setId(GuidUtils.getGuid());
                QuestionsPaper.setRegdate(new Date());
                QuestionsPaper.setModifydate(new Date());
                iReuslt = questionsPaperMapper.insertSelective(QuestionsPaper);
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
