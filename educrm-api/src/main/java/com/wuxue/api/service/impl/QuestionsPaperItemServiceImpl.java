package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.QuestionsPaperItemMapper;
import com.wuxue.api.service.QuestionsPaperItemService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsPaperItem;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("questionsPaperItemService")
public class QuestionsPaperItemServiceImpl implements QuestionsPaperItemService {

    @Autowired
    QuestionsPaperItemMapper questionsPaperItemMapper;

    @Override
    public Response find(Request<QuestionsPaperItem> tParams) {
        QuestionsPaperItem questionsPaperItem = tParams.getData();
        if(questionsPaperItem== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsPaperItem.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( questionsPaperItemMapper.selectByPrimaryKey(primaryKey));
        }else{
            PageHelper.startPage(questionsPaperItem.getPageNo(),questionsPaperItem.getPageSize());
            List<QuestionsPaperItem> List = questionsPaperItemMapper.select(questionsPaperItem);
            PageInfo<QuestionsPaperItem> pageInfo = new PageInfo<>(List);
            response.setData(pageInfo);

           // response.setData(questionsItemMapper.select(questionsItem));
           // response.setTotal(questionsItemMapper.countBy(questionsItem));

        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsPaperItem> tParams) {
        QuestionsPaperItem questionsPaperItem = tParams.getData();

        if(questionsPaperItem== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsPaperItem.getId();
        QuestionsPaperItem select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsPaperItemMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = questionsPaperItemMapper.updateByPrimaryKeySelective(questionsPaperItem);
            } else {
                questionsPaperItem.setId(GuidUtils.getGuid());
                questionsPaperItem.setRegdate(new Date());
                questionsPaperItem.setModifydate(new Date());
                iReuslt = questionsPaperItemMapper.insertSelective(questionsPaperItem);
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
