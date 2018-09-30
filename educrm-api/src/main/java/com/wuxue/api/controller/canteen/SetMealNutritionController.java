package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.SetMealNutritionService;
import com.wuxue.model.SetMealNutrition;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 套餐营养成分
 */
@RestController
@RequestMapping(value = "api/canteen/setMealNutrition")
public class SetMealNutritionController implements IFindController<SetMealNutrition>,
        ISaveController<SetMealNutrition>,IDeleteController<String>{
    @Autowired
    private SetMealNutritionService setMealNutritionService;

    @Override
    public Response Find(@RequestBody Request<SetMealNutrition> setMealNutrition) {
        return setMealNutritionService.find(setMealNutrition);
    }

    @Override
    public Response Save(@RequestBody Request<SetMealNutrition> setMealNutrition) {
        return setMealNutritionService.save(setMealNutrition);
    }

    @Override
    public Response Delete(@RequestBody Request<String> setMealNutrition) {
        return setMealNutritionService.delete(setMealNutrition);
    }


}
