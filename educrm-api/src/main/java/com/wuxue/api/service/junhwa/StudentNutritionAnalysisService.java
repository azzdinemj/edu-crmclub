package com.wuxue.api.service.junhwa;

import com.wuxue.model.junhwa.StudentNutritionAnalysis;
import com.wuxue.utils.contract.Response;

/**
 * @author tly
 * @date 2018-08-15
 */
public interface StudentNutritionAnalysisService {

    /**
     * 查询营养元素
     * @return
     */
    Response getNutritionAnalysisByPeriodType(StudentNutritionAnalysis studentNutritionAnalysis);
}
