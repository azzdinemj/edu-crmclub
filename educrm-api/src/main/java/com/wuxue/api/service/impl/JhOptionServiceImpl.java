package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.JhAnswerdetailMapper;
import com.wuxue.api.mapper.JhOptionMapper;
import com.wuxue.api.service.JhAnswerdetailService;
import com.wuxue.api.service.JhOptionService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.JhAnswerdetail;
import com.wuxue.model.JhOption;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("jhOptionService")
public class JhOptionServiceImpl implements JhOptionService {

    @Autowired
    JhOptionMapper jhOptionMapper;

    @Override
    public Response delete(Request<JhOption> tParams) {
        Response response = Response.newResponse();
        JhOption primaryKey = tParams.getData();
        if (primaryKey.getPkOption() == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = jhOptionMapper.deleteByPrimaryKey(primaryKey.getPkOption());
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
    public Response find(Request<JhOption> tParams) {
        Response response = Response.newResponse();
        JhOption tkproductOrder = tParams.getData();

        if (tkproductOrder == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = tkproductOrder.getPkOption();
        if (primaryKey != null && !primaryKey.equals("")) {
            JhOption byPrimaryKey = jhOptionMapper.selectByPrimaryKey(primaryKey);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(tkproductOrder.getPageNo(), tkproductOrder.getPageSize());
            List<JhOption> tkProductMarkList = jhOptionMapper.select(tkproductOrder);
            PageInfo page = new PageInfo(tkProductMarkList);
            response.setData(tkProductMarkList);
            response.setTotal(page.getTotal());
        }
        return response;
    }

    @Override
    public Response save(Request<JhOption> tParams) {
        Response response = Response.newResponse();
        JhOption jhAnswerdetail = tParams.getData();
        if(jhAnswerdetail== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        String pk=jhAnswerdetail.getPkOption();
        try {
            if (pk != null&&!"".equals(pk)) {
                iReuslt = jhOptionMapper.updateByPrimaryKeySelective(jhAnswerdetail);
            } else {
                jhAnswerdetail.setCreationDate(new Date());
                jhAnswerdetail.setPkOption(GuidUtils.getGuid());
                iReuslt = jhOptionMapper.insertSelective(jhAnswerdetail);
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
