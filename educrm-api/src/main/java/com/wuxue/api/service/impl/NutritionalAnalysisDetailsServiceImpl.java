package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.NutritionalAnalysisDetailsMapper;
import com.wuxue.api.service.NutritionalAnalysisDetailsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.NutritionalAnalysisDetails;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("nutritionalAnalysisDetailsService")
public class NutritionalAnalysisDetailsServiceImpl implements NutritionalAnalysisDetailsService {

    @Autowired
    private NutritionalAnalysisDetailsMapper nutritionalAnalysisDetailsMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            NutritionalAnalysisDetails nutritionalAnalysisDetails = nutritionalAnalysisDetailsMapper.selectByPrimaryKey(primaryKey);
            if (nutritionalAnalysisDetails != null) {
                nutritionalAnalysisDetailsMapper.updateByPrimaryKeySelective(nutritionalAnalysisDetails);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<NutritionalAnalysisDetails> tParams) {
        Response response = Response.newResponse();
        NutritionalAnalysisDetails nutritionalAnalysisDetails = tParams.getData();
        if (nutritionalAnalysisDetails == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = nutritionalAnalysisDetails.getAnalysisDetailes();
        if (primaryKey != null && !"".equals(primaryKey)) {
            NutritionalAnalysisDetails nutritionalAnalysisDetails1 = nutritionalAnalysisDetailsMapper.selectByPrimaryKey(primaryKey);
            response.setData(nutritionalAnalysisDetails1);
        } else {
            PageHelper.startPage(nutritionalAnalysisDetails.getPageNo(), nutritionalAnalysisDetails.getPageSize());
            List<NutritionalAnalysisDetails> list = nutritionalAnalysisDetailsMapper.select(nutritionalAnalysisDetails);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());

        }


        return response;
    }

    @Override
    public Response save(Request<NutritionalAnalysisDetails> tParams) {
        Response response = Response.newResponse();
        NutritionalAnalysisDetails nutritionalAnalysisDetails = tParams.getData();
        if (nutritionalAnalysisDetails == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = nutritionalAnalysisDetails.getAnalysisDetailes();
        String message = "";
        NutritionalAnalysisDetails select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = nutritionalAnalysisDetailsMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                nutritionalAnalysisDetails.setLasteditDate(new Date());
                nutritionalAnalysisDetailsMapper.updateByPrimaryKeySelective(nutritionalAnalysisDetails);
            } else {
                nutritionalAnalysisDetails.setAnalysisDetailes(GuidUtils.getGuid());
                nutritionalAnalysisDetails.setCreationDate(new Date());
                nutritionalAnalysisDetails.setLasteditDate(new Date());
                nutritionalAnalysisDetailsMapper.insertSelective(nutritionalAnalysisDetails);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }
}
