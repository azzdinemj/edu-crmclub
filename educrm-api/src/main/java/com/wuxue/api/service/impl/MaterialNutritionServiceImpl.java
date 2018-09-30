package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.MaterialNutritionalMapper;
import com.wuxue.api.service.MaterialNutritionService;
import com.wuxue.model.MaterialNutrition;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("materialNutritionService")
public class MaterialNutritionServiceImpl implements MaterialNutritionService {

    @Autowired
    private MaterialNutritionalMapper materialNutritionalMapper;

    @Override
    public Response find(Request<MaterialNutrition> tParams) {
        Response response = Response.newResponse();
        MaterialNutrition materialNutrition = tParams.getData();
        if (materialNutrition == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = materialNutrition.getCode();
        if (primaryKey != null && !"".equals(primaryKey)) {
            materialNutrition= materialNutritionalMapper.selectByPrimaryKey(primaryKey);
            response.setData(materialNutrition);
        } else {
            PageHelper.startPage(materialNutrition.getPageNo(), materialNutrition.getPageSize());
            List<MaterialNutrition> list = materialNutritionalMapper.select(materialNutrition);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            response.setData(list);
        }
        return response;
    }

    @Override
    public Response save(Request<MaterialNutrition> tParams) {
        Response response = Response.newResponse();
        MaterialNutrition materialNutrition = tParams.getData();
        if (materialNutrition == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = materialNutrition.getCode();
        String message = "";
        MaterialNutrition select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = materialNutritionalMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            //原料营养没有新增操作，只有修改状态操作
            if (select != null) {
                materialNutrition.setLasteditDate(new Date());

                if(StringUtils.isBlank(materialNutrition.getStatus())) {
                    materialNutrition.setStatus("ENABLE");
                }
                materialNutritionalMapper.updateByPrimaryKeySelective(materialNutrition);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }

    @Override
    public Map<String, MaterialNutrition> findMaterialNutritionByCodes(Set<String> ids) {
        //底层mapper书写
        return null;
    }
}
