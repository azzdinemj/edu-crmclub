package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.NutritionMapper;
import com.wuxue.api.service.junhwa.NutritionService;
import com.wuxue.model.Notice;
import com.wuxue.model.junhwa.Nutrition;
import com.wuxue.model.junhwa.NutritionExample;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 原料元素
 * @author tly
 * @date 2018-07-26
 */
@Service("nutritionService")
public class NutritionServiceImpl implements NutritionService {

    @Autowired
    private NutritionMapper nutritionMapper;

    @Override
    public List<Nutrition> getNutritionList(List<String> ids) {
        NutritionExample example = new NutritionExample();
        example.createCriteria().andNutritionIdIn(ids);
        List<Nutrition> nutritions = nutritionMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(nutritions)) {
            return new ArrayList<>(0);
        }
        return nutritions;
    }

    @Override
    public Map<String, Nutrition> getNutritionMap(List<String> ids) {
        List<Nutrition> nutritions = getNutritionList(ids);

        Map<String, Nutrition> nutritionMap = new HashMap<>(nutritions.size());
        for (Nutrition nutrition:nutritions) {
            nutritionMap.put(nutrition.getNutritionId(), nutrition);
        }
        return nutritionMap;
    }

    @Override
    public Response delete(Request<String> tParams) {
        return null;
    }

    @Override
    public Response find(Request<Notice> tParams) {
        return null;
    }

    @Override
    public Response save(Request<Notice> tParams) {
        return null;
    }
}
