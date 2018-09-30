package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.NutritionalAnalysisMapper;
import com.wuxue.api.service.NutritionalAnalysisService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.NutritionalAnalysis;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("nutritionalAnalysisService")
public class NutritionalAnalysisServiceImpl implements NutritionalAnalysisService {

    @Autowired
    private NutritionalAnalysisMapper nutritionalAnalysisMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            NutritionalAnalysis nutritionalAnalysis = nutritionalAnalysisMapper.selectByPrimaryKey(primaryKey);
            if (nutritionalAnalysis != null) {
                nutritionalAnalysis.setIsvalid(0);
                nutritionalAnalysisMapper.updateByPrimaryKeySelective(nutritionalAnalysis);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<NutritionalAnalysis> tParams) {
        Response response = Response.newResponse();
        NutritionalAnalysis nutritionalAnalysis = tParams.getData();
        if (nutritionalAnalysis == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = nutritionalAnalysis.getPkNutConAnalysis();
        if (primaryKey != null && !"".equals(primaryKey)) {
            NutritionalAnalysis nutritionalAnalysis1 = nutritionalAnalysisMapper.selectByPrimaryKey(primaryKey);
            response.setData(nutritionalAnalysis1);
        } else {
            PageHelper.startPage(nutritionalAnalysis.getPageNo(), nutritionalAnalysis.getPageSize());
            List<NutritionalAnalysis> list = nutritionalAnalysisMapper.select(nutritionalAnalysis);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());

        }


        return response;
    }

    @Override
    public Response save(Request<NutritionalAnalysis> tParams) {
        Response response = Response.newResponse();
        NutritionalAnalysis nutritionalAnalysis = tParams.getData();
        if (nutritionalAnalysis == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = nutritionalAnalysis.getPkNutConAnalysis();
        String message = "";
        NutritionalAnalysis select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = nutritionalAnalysisMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                nutritionalAnalysis.setLasteditDate(new Date());
                nutritionalAnalysisMapper.updateByPrimaryKeySelective(nutritionalAnalysis);
            } else {
                nutritionalAnalysis.setPkNutConAnalysis(GuidUtils.getGuid());
                nutritionalAnalysis.setCreationDate(new Date());
                nutritionalAnalysis.setLasteditDate(new Date());
                nutritionalAnalysis.setIsvalid(1);
                nutritionalAnalysisMapper.insertSelective(nutritionalAnalysis);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }
}
