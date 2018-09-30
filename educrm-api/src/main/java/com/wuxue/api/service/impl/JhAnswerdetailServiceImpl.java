package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.JhAnswerMapper;
import com.wuxue.api.mapper.JhAnswerdetailMapper;
import com.wuxue.api.service.JhAnswerService;
import com.wuxue.api.service.JhAnswerdetailService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.JhAnswer;
import com.wuxue.model.JhAnswerdetail;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("jhAnswerdetailService")
public class JhAnswerdetailServiceImpl implements JhAnswerdetailService {

    @Autowired
    JhAnswerdetailMapper jhAnswerdetailMapper;

    @Override
    public Response delete(Request<JhAnswerdetail> tParams) {
        Response response = Response.newResponse();
        JhAnswerdetail primaryKey = tParams.getData();
        if (primaryKey.getPkAnswerdetail() == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = jhAnswerdetailMapper.deleteByPrimaryKey(primaryKey.getPkAnswerdetail());
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
    public Response find(Request<JhAnswerdetail> tParams) {
        Response response = Response.newResponse();
        JhAnswerdetail tkproductOrder = tParams.getData();

        if (tkproductOrder == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = tkproductOrder.getPkAnswerdetail();
        if (primaryKey != null && !primaryKey.equals("")) {
            JhAnswerdetail byPrimaryKey = jhAnswerdetailMapper.selectByPrimaryKey(primaryKey);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(tkproductOrder.getPageNo(), tkproductOrder.getPageSize());
            List<JhAnswerdetail> tkProductMarkList = jhAnswerdetailMapper.select(tkproductOrder);
            PageInfo page = new PageInfo(tkProductMarkList);
            response.setData(tkProductMarkList);
            response.setTotal(page.getTotal());
        }
        return response;
    }

    @Override
    public Response save(Request<JhAnswerdetail> tParams) {
        Response response = Response.newResponse();
        JhAnswerdetail jhAnswerdetail = tParams.getData();
        if(jhAnswerdetail== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        String pk=jhAnswerdetail.getPkAnswerdetail();
        try {
            if (pk != null&&!"".equals(pk)) {
                iReuslt = jhAnswerdetailMapper.updateByPrimaryKeySelective(jhAnswerdetail);
            } else {
                jhAnswerdetail.setCreationDate(new Date());
                jhAnswerdetail.setPkAnswerdetail(GuidUtils.getGuid());
                iReuslt = jhAnswerdetailMapper.insertSelective(jhAnswerdetail);
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

}
