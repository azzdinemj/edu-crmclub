package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.JhExaminationMapper;
import com.wuxue.api.mapper.JhOptionMapper;
import com.wuxue.api.mapper.JhQuestionMapper;
import com.wuxue.api.service.JhExaminationService;
import com.wuxue.api.service.JhQuestionService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.JhExamination;
import com.wuxue.model.JhOption;
import com.wuxue.model.JhQuestion;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("jhQuestionService")
public class JhQuestionServiceImpl implements JhQuestionService {

    @Autowired
    JhQuestionMapper jhQuestionMapper;
    @Autowired
    JhOptionMapper jhOptionMapper;

    @Override
    public Response delete(Request<JhQuestion> tParams) {
        Response response = Response.newResponse();
        JhQuestion primaryKey = tParams.getData();
        if (primaryKey.getPkQuestion() == null) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            JhQuestion jhQuestion = jhQuestionMapper.selectByPrimaryKey(primaryKey.getPkQuestion());
            jhQuestion.setIsdel(1);
            jhQuestionMapper.updateByPrimaryKeySelective(jhQuestion);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }



    @Override
    public Response find(Request<JhQuestion> tParams) {
        Response response = Response.newResponse();
        JhQuestion jhQuestion = tParams.getData();

        if (jhQuestion == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = jhQuestion.getPkQuestion();
        if (primaryKey != null && !primaryKey.equals("")) {
            JhQuestion byPrimaryKey = jhQuestionMapper.selectByPrimaryKey(primaryKey);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(jhQuestion.getPageNo(), jhQuestion.getPageSize());
            List<JhQuestion> tkProductMarkList = jhQuestionMapper.select(jhQuestion);
            PageInfo page = new PageInfo(tkProductMarkList);
            response.setData(tkProductMarkList);
            response.setTotal(page.getTotal());
        }
        return response;
    }

    @Override
    public Response save(Request<JhQuestion> tParams) {
        Response response = Response.newResponse();
        JhQuestion jhQuestion = tParams.getData();
        if(jhQuestion== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        String pk=jhQuestion.getPkQuestion();
        try {
            if (pk != null&&!"".equals(pk)) {
                iReuslt = jhQuestionMapper.updateByPrimaryKeySelective(jhQuestion);
            } else {
                jhQuestion.setPkQuestion(GuidUtils.getGuid());
                jhQuestion.setCreator(tParams.getCurrentUser());
                jhQuestion.setModifier(tParams.getCurrentUser());
                jhQuestion.setLasteditDate(new Date());
                jhQuestion.setCreationDate(new Date());
                jhQuestion.setPkSysUser(tParams.getCurrentUser());
                jhQuestion.setStatus(2);
                jhQuestionMapper.insertSelective(jhQuestion);

//          保存问卷关联的问题
                if (jhQuestion.getJhOptionList().size() > 0) {
                    for (JhOption jhOption : jhQuestion.getJhOptionList()) {
                        jhOption.setCreator(tParams.getCurrentUser());
                        jhOption.setPkQuestion(jhQuestion.getPkQuestion());
                        jhOption.setPkOption(GuidUtils.getGuid());
                        jhOption.setCreationDate(new Date());
                        jhOption.setModifier(tParams.getCurrentUser());
                        jhOption.setLasteditDate(new Date());
                        jhOptionMapper.insertSelective(jhOption);
                    }
                }
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

    @Override
    public Response FindByUser(Request<JhQuestion> tParams) {
        Response response = Response.newResponse();
        JhQuestion jhQuestion = tParams.getData();

        if (jhQuestion == null) {
            return response.PARAMS_ISNULL();
        }
        jhQuestion.setPkSysUser(tParams.getCurrentUser());
        List<JhQuestion> tkProductMarkList = jhQuestionMapper.selectByUser(jhQuestion);
        response.setData(tkProductMarkList);
        return response;
    }
}
