package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.QuestionsMapper;
import com.wuxue.api.mapper.ReviewMapper;
import com.wuxue.api.service.QuestionsService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.CourseLessonChapter;
import com.wuxue.model.Questions;
import com.wuxue.model.Review;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("questionsService")
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    QuestionsMapper questionsMapper;

    @Autowired
    UtilsService utilsService;

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=questionsMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Questions> tParams) {
        Response response = Response.newResponse();
        Questions questions = tParams.getData();

        if(questions== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = questions.getPkQuestions();
        if(primaryKey !=null && !primaryKey.equals("")){
            questions = questionsMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(questions,questions.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(questions,questions.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(questions);
        }else{
            PageHelper.startPage(questions.getPageNo(),questions.getPageSize());
            List<Questions> list = questionsMapper.select(questions);
            for (Questions questionsEntity : list) {
                utilsService.setSysUserKeyValue(questionsEntity,questionsEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(questionsEntity,questionsEntity.getModifier(),LinkEntity.MODIFIER_ENTITY);
            }
            PageInfo<Questions> pageInfo = new PageInfo<>(list);
            response.setData(pageInfo);
        }
        return response;
    }

    @Override
    public Response save(Request<Questions> tParams) {
        Response response = Response.newResponse();
        Questions questions = tParams.getData();

        if(questions== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = questions.getPkQuestions();
        Questions select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = questionsMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                questions.setLasteditDate(new Date());
                iReuslt = questionsMapper.updateByPrimaryKeySelective(questions);
            } else {
                questions.setPkQuestions(GuidUtils.getGuid());
                questions.setCreationDate(new Date());
                questions.setLasteditDate(new Date());
                iReuslt = questionsMapper.insertSelective(questions);
                response.setData(questions.getPkQuestions());//主键
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
    public Response audit(Request<Questions> tParams) {
        Response response = Response.newResponse();
        Questions questions = tParams.getData();

        if(questions== null && questions.getPkQuestions() == null){
            return  response.PARAMS_ISNULL();
        }

        String message;
        try {
            Review review = new Review();
            review.setPkData(questions.getPkQuestions());
            review.setPkDataCaption(questions.getTitle());
            review.setIsdel(0);
            review.setType(1);
            List<Review> reviewList = reviewMapper.select(review);
            if (reviewList.size() > 0) {
                //若是驳回的请求，再次提交。则更改review状态为0
                if(reviewList.get(0).getStatus()==3){
                    reviewList.get(0).setStatus(0);
                    reviewList.get(0).setCotent("题目审核");
                    reviewMapper.updateByPrimaryKeySelective(reviewList.get(0));
                }else {
                    return response.SAVE_DOUBLE();
                }
            }else{
                review.setStatus(0);
                review.setPkReview(GuidUtils.getGuid());
                review.setCotent("题目审核");
                review.setCreator(questions.getModifier());
                review.setModifier(questions.getModifier());
                review.setCreationDate(new Date());
                review.setLasteditDate(new Date());
                reviewMapper.insertSelective(review);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.AUDIT_FAIL(message);
        }
        return response;
    }
}
