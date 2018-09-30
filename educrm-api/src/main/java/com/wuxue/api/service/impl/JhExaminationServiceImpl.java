package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.JhAnswerdetailMapper;
import com.wuxue.api.mapper.JhExaminationMapper;
import com.wuxue.api.mapper.JhOptionMapper;
import com.wuxue.api.mapper.JhQuestionMapper;
import com.wuxue.api.service.JhAnswerdetailService;
import com.wuxue.api.service.JhExaminationService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.JhAnswerdetail;
import com.wuxue.model.JhExamination;
import com.wuxue.model.JhOption;
import com.wuxue.model.JhQuestion;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("jhExaminationService")
public class JhExaminationServiceImpl implements JhExaminationService {

    @Autowired
    JhExaminationMapper jhExaminationMapper;
    @Autowired
    JhQuestionMapper jhQuestionMapper;
    @Autowired
    JhOptionMapper jhOptionMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<JhExamination> tParams) {
        Response response = Response.newResponse();
        JhExamination primaryKey = tParams.getData();
        if (primaryKey.getPkExamination() == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = jhExaminationMapper.deleteByPrimaryKey(primaryKey.getPkExamination());
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
    public Response find(Request<JhExamination> tParams) {
        Response response = Response.newResponse();
        JhExamination tkproductOrder = tParams.getData();

        if (tkproductOrder == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = tkproductOrder.getPkExamination();
        if (primaryKey != null && !primaryKey.equals("")) {
            JhExamination byPrimaryKey = jhExaminationMapper.selectByPrimaryKey(primaryKey);

            JhQuestion jhQuestion=new JhQuestion();
            jhQuestion.setPkExamination(byPrimaryKey.getPkExamination());
            List<JhQuestion> jhQuestionList=jhQuestionMapper.select(jhQuestion); //所有的题目
            if(jhQuestionList!=null){
                for (JhQuestion j : jhQuestionList ) {
                    JhOption jhOption=new JhOption();
                    jhOption.setPkQuestion(j.getPkQuestion());
                    List<JhOption> jhOptionList=jhOptionMapper.select(jhOption); //该题目的选项
                    j.put("option",jhOptionList);
                }

            }
            byPrimaryKey.put("question",jhQuestionList);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(tkproductOrder.getPageNo(), tkproductOrder.getPageSize());
            List<JhExamination> tkProductMarkList = jhExaminationMapper.select(tkproductOrder);
            PageInfo page = new PageInfo(tkProductMarkList);

            if (tkProductMarkList.size() > 0) {
                for (JhExamination jhExamination : tkProductMarkList) {
                    utilsService.setExaminationObject(jhExamination, jhExamination.getObject(), LinkEntity.OBJECT_ENTITY);
                    utilsService.setQuestionNum(jhExamination, jhExamination.getPkExamination(), LinkEntity.QUESTION_NUM_ENTITY);
                    utilsService.setNumber(jhExamination, jhExamination.getPkExamination(), LinkEntity.NUMBER_ENTITY);
                }
            }
            response.setData(tkProductMarkList);
            response.setTotal(page.getTotal());
        }
        return response;
    }

    @Override
    public Response save(Request<JhExamination> tParams) {
        Response response = Response.newResponse();
        JhExamination jhExaminationh = tParams.getData();
        if(jhExaminationh== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        String pk=jhExaminationh.getPkExamination();
        try {
            if (pk != null&&!"".equals(pk)) {
                iReuslt = jhExaminationMapper.updateByPrimaryKeySelective(jhExaminationh);
            } else {
                jhExaminationh.setCreationDate(new Date());
                jhExaminationh.setLasteditDate(new Date());
                jhExaminationh.setModifier(tParams.getCurrentUser());
                jhExaminationh.setCreator(tParams.getCurrentUser());
                jhExaminationh.setPkExamination(GuidUtils.getGuid());
                iReuslt = jhExaminationMapper.insertSelective(jhExaminationh);

            }
            //                关联习题
            JhQuestion jhQuestion = new JhQuestion();
            jhQuestion.setPkSysUser(tParams.getCurrentUser());
            List<JhQuestion> questionList = jhQuestionMapper.selectByUser(jhQuestion);
            if (questionList.size() > 0) {
                for (JhQuestion question : questionList) {
                    question.setPkExamination(jhExaminationh.getPkExamination());
                    question.setStatus(1);
                    question.setModifier(tParams.getCurrentUser());
                    question.setLasteditDate(new Date());
                    jhQuestionMapper.updateByPrimaryKeySelective(question);
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
    public Response statistics(Request<JhExamination> tParams) {
        Response response = Response.newResponse();
        JhExamination jhExaminationh = tParams.getData();
        if(jhExaminationh== null){
            return  response.PARAMS_ISNULL();
        }

        JhQuestion jhQuestion = new JhQuestion();
        jhQuestion.setPkExamination(jhExaminationh.getPkExamination());
        List<JhQuestion> jhQuestionList = jhQuestionMapper.select(jhQuestion);
        JhOption jhOption = new JhOption();
        jhOption.setPkExamination(jhExaminationh.getPkExamination());
        List<JhOption> optionList = jhOptionMapper.statistics(jhOption);
        if (jhQuestionList.size() > 0) {
            for (JhQuestion question : jhQuestionList) {
                jhOption = new JhOption();
                jhOption.setPkQuestion(question.getPkQuestion());
                List<JhOption> jhOptionList = jhOptionMapper.select(jhOption);


                if (jhOptionList.size() > 0) {
                    for (JhOption option : jhOptionList) {


                        if (optionList.size() > 0) {
                            for (JhOption jhOption1 : optionList) {

                                if(jhOption1.getPkQuestion().equals(question.getPkQuestion())){
                                    if(jhOption1.getMark().equals(option.getMark())){
                                        option.setNum(jhOption1.getNum());
                                        option.setPeopleNum(jhOption1.getPeopleNum());
                                        break;
                                    }else{
                                        option.setNum(0);
                                        option.setPeopleNum(0);
                                    }
                                }


                            }
                        }


                    }
                }
                question.setJhOptionList(jhOptionList);
                question.setPkExamination(jhExaminationh.getPkExamination());
                if(question.getOther() != null && question.getOther() == 1) {
                    List<JhQuestion> jhQuestionList2 = jhQuestionMapper.selectOther(question);
                    question.setOtherList(jhQuestionList2);
                }
            }
        }

        response.setData(jhQuestionList);
        return response;
    }
}
