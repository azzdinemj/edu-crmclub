package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TkSetMealService;
import com.wuxue.model.TkSetMeal;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/tkSetMeal")
public class TkSetMealController implements IFindController<TkSetMeal>,
        ISaveController<TkSetMeal>,IDeleteController<String>{
    @Autowired
    private TkSetMealService tkSetMealService;

    @Override
    public Response Find(@RequestBody Request<TkSetMeal> tkSetMeal) {
        return tkSetMealService.find(tkSetMeal);
    }

    @Override
    public Response Save(@RequestBody Request<TkSetMeal> tkSetMeal) {
        return tkSetMealService.save(tkSetMeal);
    }

    @Override
    public Response Delete(@RequestBody Request<String> tkSetMeal) {
        return tkSetMealService.delete(tkSetMeal);
    }
}
