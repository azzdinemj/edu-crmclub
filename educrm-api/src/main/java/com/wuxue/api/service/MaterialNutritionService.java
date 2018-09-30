package com.wuxue.api.service;


import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.MaterialNutrition;

import java.util.Map;
import java.util.Set;

public interface MaterialNutritionService extends ISaveService<MaterialNutrition>,IFindService<MaterialNutrition> {

    /**
     * 通过主键获取原料营养新信息 此处底层mapper未生成
     * @return
     */
    Map<String, MaterialNutrition> findMaterialNutritionByCodes(Set<String> ids);

}
