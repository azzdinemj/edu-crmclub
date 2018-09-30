package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.MaterialNutritionService;
import com.wuxue.model.MaterialNutrition;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 原料营养管理器
 */
@RestController
@RequestMapping(value = "api/canteen/materialNutrition")
public class MaterialNutritionController implements IFindController<MaterialNutrition>,
        ISaveController<MaterialNutrition> {

    @Autowired
    private MaterialNutritionService materialNutritionService;

    @Override
    public Response Find(Request<MaterialNutrition> request)
    {
        return materialNutritionService.find(request);
    }

    @Override
    public Response Save(Request<MaterialNutrition> request) {

        return materialNutritionService.save(request);
    }
}
