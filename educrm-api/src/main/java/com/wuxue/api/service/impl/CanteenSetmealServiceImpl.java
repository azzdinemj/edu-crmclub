package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.CanteenSetmealMapper;
import com.wuxue.api.service.CanteenSetmealService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.CanteenSetmeal;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("canteenSetmealService")
public class CanteenSetmealServiceImpl implements CanteenSetmealService {

    @Autowired
    private CanteenSetmealMapper canteenSetmealMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            CanteenSetmeal canteenSetmeal = canteenSetmealMapper.selectByPrimaryKey(primaryKey);
            if (canteenSetmeal != null) {
                canteenSetmealMapper.updateByPrimaryKeySelective(canteenSetmeal);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response find(Request<CanteenSetmeal> tParams) {
        Response response = Response.newResponse();
        CanteenSetmeal canteenSetmeal = tParams.getData();
        if (canteenSetmeal == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = canteenSetmeal.getPkCanteenMeal();
        if (primaryKey != null && !"".equals(primaryKey)) {
            CanteenSetmeal canteenSetmeal1 = canteenSetmealMapper.selectByPrimaryKey(primaryKey);
            response.setData(canteenSetmeal1);
        } else {
            PageHelper.startPage(canteenSetmeal.getPageNo(), canteenSetmeal.getPageSize());
            List<CanteenSetmeal> list = canteenSetmealMapper.select(canteenSetmeal);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());

        }


        return response;
    }

    @Override
    public Response save(Request<CanteenSetmeal> tParams) {
        Response response = Response.newResponse();
        CanteenSetmeal canteenSetmeal = tParams.getData();
        if (canteenSetmeal == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = canteenSetmeal.getPkCanteenMeal();
        String message = "";
        CanteenSetmeal select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = canteenSetmealMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                canteenSetmealMapper.updateByPrimaryKeySelective(canteenSetmeal);
            } else {
                canteenSetmeal.setPkCanteenMeal(GuidUtils.getGuid());
                canteenSetmealMapper.insertSelective(canteenSetmeal);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }
}
