package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.LookAnalysisService;
import com.wuxue.model.LookAnalysis;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 家长查看营养分析结果记录
 */
@RestController
@RequestMapping(value = "api/canteen/lookAnalysis")
public class LookAnalysisController implements IFindController<LookAnalysis>,
        ISaveController<LookAnalysis>,IDeleteController<String>{
    @Autowired
    private LookAnalysisService lookAnalysisService;

    @Override
    public Response Find(@RequestBody Request<LookAnalysis> lookAnalysis) {
        return lookAnalysisService.find(lookAnalysis);
    }

    @Override
    public Response Save(@RequestBody Request<LookAnalysis> lookAnalysis) {
        return lookAnalysisService.save(lookAnalysis);
    }

    @Override
    public Response Delete(@RequestBody Request<String> lookAnalysis) {
        return lookAnalysisService.delete(lookAnalysis);
    }


}
