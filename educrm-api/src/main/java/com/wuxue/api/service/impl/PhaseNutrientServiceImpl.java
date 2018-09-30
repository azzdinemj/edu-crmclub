package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.PhaseNutrientMapper;
import com.wuxue.api.service.PhaseNutrientService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.PhaseNutrient;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("phaseNutrientService")
public class PhaseNutrientServiceImpl implements PhaseNutrientService {

    @Autowired
    private PhaseNutrientMapper phaseNutrientMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();

        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            PhaseNutrient phaseNutrient = phaseNutrientMapper.selectByPrimaryKey(Integer.parseInt(primaryKey));
            if (phaseNutrient != null) {
                phaseNutrient.setIsvalid(0);
                phaseNutrientMapper.updateByPrimaryKeySelective(phaseNutrient);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<PhaseNutrient> tParams) {
        Response response = Response.newResponse();
        PhaseNutrient phaseNutrient = tParams.getData();
        if (phaseNutrient == null) {
            return response.PARAMS_ISNULL();
        }
        Integer primaryKey = phaseNutrient.getPkPhaseNutrient();
        if (primaryKey != null && !"".equals(primaryKey)) {
            PhaseNutrient phaseNutrient1 = phaseNutrientMapper.selectByPrimaryKey(primaryKey);
            response.setData(phaseNutrient1);
        } else {
            PageHelper.startPage(phaseNutrient.getPageNo(), phaseNutrient.getPageSize());
            List<PhaseNutrient> list = phaseNutrientMapper.select(phaseNutrient);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());

        }


        return response;
    }

    @Override
    public Response save(Request<PhaseNutrient> tParams) {
        Response response = Response.newResponse();
        PhaseNutrient phaseNutrient = tParams.getData();
        if (phaseNutrient == null) {
            return response.PARAMS_ISNULL();
        }
        Integer primaryKey = phaseNutrient.getPkPhaseNutrient();
        String message = "";
        PhaseNutrient select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = phaseNutrientMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                phaseNutrient.setLasteditDate(new Date());
                phaseNutrientMapper.updateByPrimaryKeySelective(phaseNutrient);
            } else {
                phaseNutrient.setCreationDate(new Date());
                phaseNutrient.setLasteditDate(new Date());
                phaseNutrient.setIsvalid(1);
                phaseNutrientMapper.insertSelective(phaseNutrient);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }
}
