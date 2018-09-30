package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.NutritionalAnalysisService;
import com.wuxue.model.NutritionalAnalysis;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 学生营养成分分析
 */
@RestController
@RequestMapping(value = "api/canteen/nutritionalAnalysis")
public class NutritionalAnalysisController implements IFindController<NutritionalAnalysis>,
        ISaveController<NutritionalAnalysis>,IDeleteController<String>{
    @Autowired
    private NutritionalAnalysisService nutritionalAnalysisService;

    @Override
    public Response Find(@RequestBody Request<NutritionalAnalysis> nutritionalAnalysis) {
        return nutritionalAnalysisService.find(nutritionalAnalysis);
    }

    @Override
    public Response Save(@RequestBody Request<NutritionalAnalysis> nutritionalAnalysis) {
        return nutritionalAnalysisService.save(nutritionalAnalysis);
    }

    @Override
    public Response Delete(@RequestBody Request<String> nutritionalAnalysis) {
        return nutritionalAnalysisService.delete(nutritionalAnalysis);
    }


}
