package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.QuestionsPaperDataMapper;
import com.wuxue.api.service.QuestionsPaperDataService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsPaperData;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionsPapertDataService")
public class QuestionsPaperDataServiceImpl implements QuestionsPaperDataService {

    @Autowired
    QuestionsPaperDataMapper questionsPaperDataMapper;


    @Override
    public Response find(Request<QuestionsPaperData> tParams) {
        QuestionsPaperData questionsPaperData = tParams.getData();
        if(questionsPaperData== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsPaperData.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("")){
            response.setData( questionsPaperDataMapper.selectByPrimaryKey(primaryKey));
        }else{
            PageHelper.startPage(questionsPaperData.getPageNo(),questionsPaperData.getPageSize());
            List<QuestionsPaperData> List = questionsPaperDataMapper.select(questionsPaperData);
            PageInfo<QuestionsPaperData> pageInfo = new PageInfo<>(List);
            response.setData(pageInfo);

           // response.setData(questionsItemMapper.select(questionsItem));
           // response.setTotal(questionsItemMapper.countBy(questionsItem));

        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsPaperData> tParams) {
        QuestionsPaperData questionsPaperData = tParams.getData();

        if(questionsPaperData== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsPaperData.getId();
        QuestionsPaperData select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsPaperDataMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = questionsPaperDataMapper.updateByPrimaryKeySelective(questionsPaperData);
            } else {
                questionsPaperData.setId(GuidUtils.getGuid());
                iReuslt = questionsPaperDataMapper.insertSelective(questionsPaperData);
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
