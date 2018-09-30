package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ActivePersonnelMapper;
import com.wuxue.api.mapper.JhAnswerMapper;
import com.wuxue.api.mapper.ProductMapper;
import com.wuxue.api.mapper.TkProductMarkMapper;
import com.wuxue.api.service.JhAnswerService;
import com.wuxue.api.service.TkProductMarkService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.ActivePersonnel;
import com.wuxue.model.JhAnswer;
import com.wuxue.model.Product;
import com.wuxue.model.TkProductMark;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("jhAnswerService")
public class JhAnswerServiceImpl implements JhAnswerService {

    @Autowired
    JhAnswerMapper jhAnswerMapper;
    @Autowired
    ActivePersonnelMapper activePersonnelMapper;

    @Override
    public Response delete(Request<JhAnswer> tParams) {
        Response response = Response.newResponse();
        JhAnswer primaryKey = tParams.getData();
        if (primaryKey.getPkAnswer() == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = jhAnswerMapper.deleteByPrimaryKey(primaryKey.getPkAnswer());
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
    public Response find(Request<JhAnswer> tParams) {
        Response response = Response.newResponse();
        JhAnswer tkproductOrder = tParams.getData();

        if (tkproductOrder == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = tkproductOrder.getPkAnswer();
        if (primaryKey != null && !primaryKey.equals("")) {
            JhAnswer byPrimaryKey = jhAnswerMapper.selectByPrimaryKey(primaryKey);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(tkproductOrder.getPageNo(), tkproductOrder.getPageSize());
            List<JhAnswer> tkProductMarkList = jhAnswerMapper.select(tkproductOrder);
            PageInfo page = new PageInfo(tkProductMarkList);
            response.setData(tkProductMarkList);
            response.setTotal(page.getTotal());
        }
        return response;
    }

    @Override
    public Response save(Request<JhAnswer> tParams) {
        Response response = Response.newResponse();
        JhAnswer tkProductMark = tParams.getData();
        if(tkProductMark== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        String pk=tkProductMark.getPkAnswer();
        try {
            if (pk != null&&!"".equals(pk)) {
                iReuslt = jhAnswerMapper.updateByPrimaryKeySelective(tkProductMark);
            } else {
                ActivePersonnel activePersonnel = new ActivePersonnel();
                activePersonnel.setName(tkProductMark.getChildrenName());
                activePersonnel.setMobile(tkProductMark.getFatherPhone());
                activePersonnel.setPhone(tkProductMark.getMotherPhone());
                List<ActivePersonnel> select = activePersonnelMapper.select(activePersonnel);
                if(select != null) {
                    tkProductMark.setCreationDate(new Date());
                    tkProductMark.setPkAnswer(GuidUtils.getGuid());
                    iReuslt = jhAnswerMapper.insertSelective(tkProductMark);
                    if (iReuslt > 0) {
                        response.setData(tkProductMark.getPkAnswer());
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
    public Response queryAnswerList(Request<JhAnswer> tParams) {
        Response response = Response.newResponse();
        JhAnswer jhAnswer = tParams.getData();
        if(jhAnswer== null){
            return  response.PARAMS_ISNULL();
        }
        String message= "";

        try {
            PageHelper.startPage(jhAnswer.getPageNo(), jhAnswer.getPageSize());
            List<JhAnswer> tkProductMarkList = jhAnswerMapper.selectAnswerList(jhAnswer);
            PageInfo page = new PageInfo(tkProductMarkList);
            response.setData(tkProductMarkList);
            response.setTotal(page.getTotal());
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }
}
