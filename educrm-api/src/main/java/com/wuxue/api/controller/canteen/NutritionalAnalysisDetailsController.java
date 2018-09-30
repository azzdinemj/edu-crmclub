package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.NutritionalAnalysisDetailsService;
import com.wuxue.model.NutritionalAnalysisDetails;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 学生营养分析明细
 */
@RestController
@RequestMapping(value = "api/canteen/nutritionalAnalysisDetails")
public class NutritionalAnalysisDetailsController implements IFindController<NutritionalAnalysisDetails>,
        ISaveController<NutritionalAnalysisDetails>,IDeleteController<String>{
    @Autowired
    private NutritionalAnalysisDetailsService nutritionalAnalysisDetailsService;

    @Override
    public Response Find(@RequestBody Request<NutritionalAnalysisDetails> nutritionalAnalysisDetails) {
        return nutritionalAnalysisDetailsService.find(nutritionalAnalysisDetails);
    }

    @Override
    public Response Save(@RequestBody Request<NutritionalAnalysisDetails> nutritionalAnalysisDetails) {
        return nutritionalAnalysisDetailsService.save(nutritionalAnalysisDetails);
    }

    @Override
    public Response Delete(@RequestBody Request<String> nutritionalAnalysisDetails) {
        return nutritionalAnalysisDetailsService.delete(nutritionalAnalysisDetails);
    }


}
