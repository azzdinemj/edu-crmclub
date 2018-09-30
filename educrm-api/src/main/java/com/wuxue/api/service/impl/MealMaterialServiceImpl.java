package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.MealMaterialMapper;
import com.wuxue.api.service.MealMaterialService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.junhwa.MealMaterial;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("MealMaterialService")
public class MealMaterialServiceImpl implements MealMaterialService {

    @Autowired
    private MealMaterialMapper mealMaterialMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            MealMaterial mealMaterial = mealMaterialMapper.selectByPrimaryKey(primaryKey);
            if (mealMaterial != null) {
//                mealMaterial.(0);
                mealMaterialMapper.deleteByPrimaryKey(mealMaterial.getMaterialId());
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<MealMaterial> tParams) {
        Response response = Response.newResponse();
        MealMaterial mealMaterial = tParams.getData();
        if (mealMaterial == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = mealMaterial.getMaterialId();
        if (primaryKey != null && !"".equals(primaryKey)) {
            MealMaterial mealMaterial1 = mealMaterialMapper.selectByPrimaryKey(primaryKey);
            response.setData(mealMaterial1);
        } else {
            PageHelper.startPage(mealMaterial.getPageNo(), mealMaterial.getPageSize());
            List<MealMaterial> list = mealMaterialMapper.select(mealMaterial);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            response.setData(list);
        }


        return response;
    }

    @Override
    public Response save(Request<MealMaterial> tParams) {
        Response response = Response.newResponse();
        MealMaterial mealMaterial = tParams.getData();
        if (mealMaterial == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = mealMaterial.getMaterialId();
        String message = "";
        MealMaterial select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = mealMaterialMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                mealMaterial.setLasteditDate(new Date());
                mealMaterialMapper.updateByPrimaryKeySelective(mealMaterial);
            } else {
                mealMaterial.setMaterialId(GuidUtils.getGuid());
                mealMaterial.setCreationDate(new Date());
                mealMaterial.setLasteditDate(new Date());
                mealMaterialMapper.insertSelective(mealMaterial);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }
}
