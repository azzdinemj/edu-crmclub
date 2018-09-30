package com.wuxue.api.service.junhwa;

import com.wuxue.model.ParentOrder;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

/**
 * 营养分析service
 *
 * @author tly
 * @date 2018-07-26
 */
public interface TrophicAnalysisService {

    Response getStudentNutritionList(ParentOrder parentOrder);

    String select();

    /**
     * 选餐统计
     * @param request
     * @return
     */
    Response selectMealStatistics(Request<String> request);
}
