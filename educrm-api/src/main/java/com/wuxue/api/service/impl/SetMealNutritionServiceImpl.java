package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.SetMealNutritionMapper;
import com.wuxue.api.service.SetMealNutritionService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.SetMealNutrition;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("setMealNutritionService")
public class SetMealNutritionServiceImpl implements SetMealNutritionService {

    @Autowired
    private SetMealNutritionMapper setMealNutritionMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            SetMealNutrition setMealNutrition = setMealNutritionMapper.selectByPrimaryKey(primaryKey);
            if (setMealNutrition != null) {
                setMealNutrition.setIsvalid(0);
                setMealNutritionMapper.updateByPrimaryKeySelective(setMealNutrition);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<SetMealNutrition> tParams) {
        Response response = Response.newResponse();
        SetMealNutrition setMealNutrition = tParams.getData();
        if (setMealNutrition == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = setMealNutrition.getPkMealNutrition();
        if (primaryKey != null && !"".equals(primaryKey)) {
            SetMealNutrition setMealNutrition1 = setMealNutritionMapper.selectByPrimaryKey(primaryKey);
            response.setData(setMealNutrition1);
        } else {
            PageHelper.startPage(setMealNutrition.getPageNo(), setMealNutrition.getPageSize());
            List<SetMealNutrition> list = setMealNutritionMapper.select(setMealNutrition);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());

        }


        return response;
    }

    @Override
    public Response save(Request<SetMealNutrition> tParams) {
        Response response = Response.newResponse();
        SetMealNutrition setMealNutrition = tParams.getData();
        if (setMealNutrition == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = setMealNutrition.getPkMealNutrition();
        String message = "";
        SetMealNutrition select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = setMealNutritionMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                setMealNutrition.setLasteditDate(new Date());
                setMealNutritionMapper.updateByPrimaryKeySelective(setMealNutrition);
            } else {
                setMealNutrition.setPkMealNutrition(GuidUtils.getGuid());
                setMealNutrition.setCreationDate(new Date());
                setMealNutrition.setLasteditDate(new Date());
                setMealNutrition.setIsvalid(1);
                setMealNutritionMapper.insertSelective(setMealNutrition);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }
}
