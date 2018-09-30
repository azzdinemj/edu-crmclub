package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.LookAnalysisMapper;
import com.wuxue.api.service.LookAnalysisService;
import com.wuxue.api.service.LookAnalysisService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.LookAnalysis;
import com.wuxue.model.LookAnalysis;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("lookAnalysisService")
public class LookAnalysisServiceImpl implements LookAnalysisService {

    @Autowired
    private LookAnalysisMapper lookAnalysisMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            LookAnalysis lookAnalysis = lookAnalysisMapper.selectByPrimaryKey(primaryKey);
            if (lookAnalysis != null) {
                lookAnalysisMapper.updateByPrimaryKeySelective(lookAnalysis);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<LookAnalysis> tParams) {
        Response response = Response.newResponse();
        LookAnalysis lookAnalysis = tParams.getData();
        if (lookAnalysis == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = lookAnalysis.getPkLookAnalysis();
        if (primaryKey != null && !"".equals(primaryKey)) {
            LookAnalysis lookAnalysis1 = lookAnalysisMapper.selectByPrimaryKey(primaryKey);
            response.setData(lookAnalysis1);
        } else {
            PageHelper.startPage(lookAnalysis.getPageNo(), lookAnalysis.getPageSize());
            List<LookAnalysis> list = lookAnalysisMapper.select(lookAnalysis);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());

        }


        return response;
    }

    @Override
    public Response save(Request<LookAnalysis> tParams) {
        Response response = Response.newResponse();
        LookAnalysis lookAnalysis = tParams.getData();
        if (lookAnalysis == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = lookAnalysis.getPkLookAnalysis();
        String message = "";
        LookAnalysis select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = lookAnalysisMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                lookAnalysisMapper.updateByPrimaryKeySelective(lookAnalysis);
            } else {
                lookAnalysis.setPkLookAnalysis(GuidUtils.getGuid());
                lookAnalysis.setViewDate(new Date());
                lookAnalysisMapper.insertSelective(lookAnalysis);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }
}
