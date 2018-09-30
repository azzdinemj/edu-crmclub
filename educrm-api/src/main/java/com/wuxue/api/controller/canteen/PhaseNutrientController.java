package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.PhaseNutrientService;
import com.wuxue.model.PhaseNutrient;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 阶段健康营养成分
 */
@RestController
@RequestMapping(value = "api/canteen/phaseNutrient")
public class PhaseNutrientController extends NutrientController implements IFindController<PhaseNutrient>,
        ISaveController<PhaseNutrient>,IDeleteController<String>{
    @Autowired
    private PhaseNutrientService phaseNutrientService;

    @Override
    public Response Find(@RequestBody Request<PhaseNutrient> phaseNutrient) {
        return phaseNutrientService.find(phaseNutrient);
    }

    @Override
    public Response Save(@RequestBody Request<PhaseNutrient> phaseNutrient) {
        return phaseNutrientService.save(phaseNutrient);
    }

    @Override
    public Response Delete(@RequestBody Request<String> phaseNutrient) {
        return phaseNutrientService.delete(phaseNutrient);
    }


}
