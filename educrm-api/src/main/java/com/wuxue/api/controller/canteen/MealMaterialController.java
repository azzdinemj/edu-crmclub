package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.MealMaterialService;
import com.wuxue.model.junhwa.MealMaterial;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 套餐原料
 */
@RestController
@RequestMapping(value = "api/canteen/mealMaterial")
public class MealMaterialController implements IFindController<MealMaterial>,
        ISaveController<MealMaterial>,IDeleteController<String>{
    @Autowired
    private MealMaterialService mealMaterialService;

    @Override
    public Response Find(@RequestBody Request<MealMaterial> mealMaterial) {
        return mealMaterialService.find(mealMaterial);
    }

    @Override
    public Response Save(@RequestBody Request<MealMaterial> mealMaterial) {
        return mealMaterialService.save(mealMaterial);
    }

    @Override
    public Response Delete(@RequestBody Request<String> mealMaterial) {
        return mealMaterialService.delete(mealMaterial);
    }


}
