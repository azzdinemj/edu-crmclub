package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.QuestionsPaperTypeMapper;
import com.wuxue.api.service.QuestionsPaperTypeService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsPaperType;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("questionsPapertTypeService")
public class QuestionsPaperTypeServiceImpl implements QuestionsPaperTypeService {

    @Autowired
    QuestionsPaperTypeMapper questionsPaperTypeMapper;


    @Override
    public Response find(Request<QuestionsPaperType> tParams) {
        QuestionsPaperType questionsPaperType = tParams.getData();
        if(questionsPaperType== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsPaperType.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( questionsPaperTypeMapper.selectByPrimaryKey(primaryKey));
        }else{
            PageHelper.startPage(questionsPaperType.getPageNo(),questionsPaperType.getPageSize());
            List<QuestionsPaperType> List = questionsPaperTypeMapper.select(questionsPaperType);
            PageInfo<QuestionsPaperType> pageInfo = new PageInfo<>(List);
            response.setData(pageInfo);

           // response.setData(questionsItemMapper.select(questionsItem));
           // response.setTotal(questionsItemMapper.countBy(questionsItem));

        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsPaperType> tParams) {
        QuestionsPaperType questionsPaperType = tParams.getData();

        if(questionsPaperType== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsPaperType.getId();
        QuestionsPaperType select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsPaperTypeMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = questionsPaperTypeMapper.updateByPrimaryKeySelective(questionsPaperType);
            } else {
                questionsPaperType.setId(GuidUtils.getGuid());
                iReuslt = questionsPaperTypeMapper.insertSelective(questionsPaperType);
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
