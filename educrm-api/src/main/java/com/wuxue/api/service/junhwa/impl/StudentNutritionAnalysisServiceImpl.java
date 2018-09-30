package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.StudentNutritionAnalysisMapper;
import com.wuxue.api.service.junhwa.NutritionService;
import com.wuxue.api.service.junhwa.StudentNutritionAnalysisService;
import com.wuxue.api.utils.DateUtil;
import com.wuxue.model.junhwa.Nutrition;
import com.wuxue.model.junhwa.StudentNutritionAnalysis;
import com.wuxue.model.junhwa.StudentNutritionAnalysisExample;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tly
 * @date 2018-08-15
 */
@Service("studentNutritionAnalysisService")
public class StudentNutritionAnalysisServiceImpl implements StudentNutritionAnalysisService {

    @Autowired
    private StudentNutritionAnalysisMapper studentNutritionAnalysisMapper;
    @Autowired
    private NutritionService nutritionService;


    @Override
    public Response getNutritionAnalysisByPeriodType(StudentNutritionAnalysis studentNutritionAnalysis) {
        Response response =Response.newResponse();
        String pkStudent = studentNutritionAnalysis.getPkStudent();
        if (null == pkStudent || "".equals(pkStudent)){
            return  response.PARAMS_ISNULL();
        }
        String yearAndMonth = studentNutritionAnalysis.getYearAndMonth();
        if (null == yearAndMonth || "".equals(yearAndMonth)) {
            String currentYear = DateUtil.getCurrentYear();//当前年
            Byte lastMonth = DateUtil.getLastMonth();//上个月
            studentNutritionAnalysis.setCalcYear(currentYear);
            studentNutritionAnalysis.setCalcMonth(lastMonth);
        } else {
            if (studentNutritionAnalysis.getYearAndMonth().indexOf("-")>-1){
                String[] split = yearAndMonth.split("-");
                studentNutritionAnalysis.setCalcYear(split[0]);
                studentNutritionAnalysis.setCalcMonth(Byte.valueOf(split[1]));
            }else{
                return response.ILLEGAL_REQUEST();
            }
        }
        StudentNutritionAnalysisExample example = new StudentNutritionAnalysisExample();
        StudentNutritionAnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andPkStudentEqualTo(pkStudent);
//        Byte periodType = studentNutritionAnalysis.getPeriodType();
        Byte periodType = 2;//按月查
        criteria.andPeriodTypeEqualTo(periodType);
        switch (periodType) {
            case 1:
                criteria.andCalcWeekEqualTo(studentNutritionAnalysis.getCalcWeek());
            case 2:
                criteria.andCalcMonthEqualTo(studentNutritionAnalysis.getCalcMonth());
            case 4:
                criteria.andCalcYearEqualTo(studentNutritionAnalysis.getCalcYear());
                break;
        }
        List<StudentNutritionAnalysis> analysisList = studentNutritionAnalysisMapper.selectByExample(example);

        if (CollectionUtils.isNotEmpty(analysisList)) {

            List<String> nutritionIds = new ArrayList<>(analysisList.size());
            for (StudentNutritionAnalysis analysis:analysisList) {
                nutritionIds.add(analysis.getNutritionId());
            }
            Map<String, Nutrition> nutritionMap = nutritionService.getNutritionMap(nutritionIds);

            for (StudentNutritionAnalysis analysis:analysisList) {
                analysis.setNutrition(nutritionMap.get(analysis.getNutritionId()));
            }

        }
        response.setData(analysisList);
        return response;
    }


}
