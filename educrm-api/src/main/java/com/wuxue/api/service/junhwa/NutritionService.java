package com.wuxue.api.service.junhwa;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Notice;
import com.wuxue.model.junhwa.Nutrition;

import java.util.List;
import java.util.Map;

public interface NutritionService extends ISaveService<Notice>,IFindService<Notice>,IDeleteService<String> {

    /**
     * 通过原料查询对应的元素信息
     * @author tly
     * @param ids 原料列表
     * @return
     */
    List<Nutrition> getNutritionList(List<String> ids);

    /**
     * 通过营养主键获取营养信息
     * @param ids
     * @return
     */
    Map<String, Nutrition> getNutritionMap(List<String> ids);
}
