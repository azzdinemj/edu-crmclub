package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.QuestionsOptionMapper;
import com.wuxue.api.service.QuestionsOptionService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.QuestionsOption;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 题库选项
 */
@Service("questionsOptionService")
public class QuestionsOptionServiceImpl implements QuestionsOptionService{
    @Autowired
    QuestionsOptionMapper sysMenuMapper;


    @Override
    public Response find(Request<QuestionsOption> tParams) {
        QuestionsOption questionsOption = tParams.getData();

        if(questionsOption== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
         String primaryKey = questionsOption.getId();
        Response response = Response.newResponse();
        if(primaryKey !=null && !primaryKey.equals("") ){
            response.setData( sysMenuMapper.selectByPrimaryKey(primaryKey));
        }else{
            response.setData(sysMenuMapper.select(questionsOption));
           // response.setTotal(sysMenuMapper.countBy(sysMenu));
        }
        return response;
    }

    @Override
    public Response save(Request<QuestionsOption> tParams) {
        QuestionsOption questionsOption = tParams.getData();

        if(questionsOption== null){
            return  Response.newResponse().PARAMS_ISNULL();
        }
        String primaryKey = questionsOption.getId();
        QuestionsOption select = null;
        if (primaryKey !=null && !primaryKey.equals("") ) {
            select = sysMenuMapper.selectByPrimaryKey(primaryKey);
        }
        Response response = Response.newResponse();
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = sysMenuMapper.updateByPrimaryKeySelective(questionsOption);
            } else {
                questionsOption.setId(GuidUtils.getGuid());
                iReuslt = sysMenuMapper.insertSelective(questionsOption);
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
