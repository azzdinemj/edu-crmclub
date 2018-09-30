package com.wuxue.api.controller.junhua;

import com.wuxue.api.service.junhwa.StudentNutritionAnalysisService;
import com.wuxue.api.service.junhwa.TrophicAnalysisService;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.junhwa.StudentNutritionAnalysis;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 营养分析
 */
@RestController
@RequestMapping(value = "api/junhua/analysis")
public class TrophicAnalysisController {
	@Autowired
	private TrophicAnalysisService trophicAnalysisService;

	@Autowired
	private StudentNutritionAnalysisService studentNutritionAnalysisService;

	@RequestMapping(value = "/getStudentNutritionList", method = RequestMethod.POST)
	public Response getStudentNutritionList(@RequestBody Request<ParentOrder> request) {
		return trophicAnalysisService.getStudentNutritionList(request.getData());
	}

	@RequestMapping(value = "selectMealStatistics" ,method = RequestMethod.POST)
	public Response selectMealStatistics(@RequestBody Request<String> request){
		return trophicAnalysisService.selectMealStatistics(request);
	}

	/**
	 * 查询学生营养元素
	 * @return
	 */
	@RequestMapping(value = "/getnutritionanalysis")
	public Response getNutritionAnalysis(@RequestBody  Request<StudentNutritionAnalysis> request){
		return studentNutritionAnalysisService.getNutritionAnalysisByPeriodType(request.getData());
	}
}
