package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.ParentOrderMapper;
import com.wuxue.api.mapper.SetMealMapper;
import com.wuxue.api.service.junhwa.NutritionService;
import com.wuxue.api.service.junhwa.TrophicAnalysisService;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.junhwa.Nutrition;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 营养分析service
 *
 * @author tly
 * @date 2018-07-26
 */
@Service("trophicAnalysisService")
public class TrophicAnalysisServiceImpl implements TrophicAnalysisService {

    @Autowired
    private ParentOrderMapper parentOrderMapper;
    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private NutritionService nutritionService;


    @Override
    public Response getStudentNutritionList(ParentOrder parentOrder) {
        Response response = Response.newResponse();
        if (parentOrder == null) {
            return response.PARAMS_ISNULL();
        }
        //获取套餐的各种元素总含量
        List<ResultEntity> entities = parentOrderMapper.getMaterialIdAndGramCountList(parentOrder);

        Map<String,Double> materialIdMap =new HashMap<>();
        List<String> ids = new ArrayList<>();
        for (ResultEntity entity : entities) {
            materialIdMap.put(entity.getId(),entity.getCount());
            ids.add(entity.getId());
        }
        //根据元素ids找出所有对应元素信息
        List<Nutrition> nutritions = nutritionService.getNutritionList(ids);

        for (Nutrition nutrition : nutritions) {
            String materialId = nutrition.getMaterialId();
            Long content = nutrition.getContent();
            nutrition.setSumValue(Double.valueOf(content) * materialIdMap.get(materialId));
        }

        Map<String, Nutrition> nameNutritionMap = new HashMap<>();
        for (Nutrition nutrition : nutritions){
            if (nameNutritionMap.containsKey(nutrition.getNutritionName())){
                nameNutritionMap.get(nutrition.getNutritionName()).setSumValue(nutrition.getSumValue());
            }else{
                nameNutritionMap.put(nutrition.getNutritionName(),nutrition);
            }
        }
        List<Nutrition> mapValueList = new ArrayList<>(nameNutritionMap.values());

        response.setData(mapValueList);
        return response;
    }

    @Override
    public String select() {
        Response response = Response.newResponse();
        ParentOrder parentOrder = new ParentOrder();
        parentOrder.setPkStudent("201826873091135748");
        List<ParentOrder> orders = parentOrderMapper.selectAllInfoByStudentId(parentOrder);

        return null;
    }

    @Override
    public Response selectMealStatistics(Request<String> request) {
        Response response = Response.newResponse();
        String pkStudent = request.getData();
        if (null==pkStudent||"".equals(pkStudent)){
            return response.PARAMS_ISNULL();
        }
        List<ResultEntity> entities = parentOrderMapper.selectMealStatisticsByPkStudent(pkStudent);
        response.setData(entities);
        return response;
    }

}
