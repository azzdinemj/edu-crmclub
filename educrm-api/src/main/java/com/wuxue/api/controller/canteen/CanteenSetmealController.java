package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.CanteenSetmealService;
import com.wuxue.model.CanteenSetmeal;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10  食堂套餐关联表
 */
@RestController
@RequestMapping(value = "api/canteen/canteenSetmeal")
public class CanteenSetmealController implements IFindController<CanteenSetmeal>,
        ISaveController<CanteenSetmeal>,IDeleteController<String>{
    @Autowired
    private CanteenSetmealService canteenSetmealService;

    @Override
    public Response Find(@RequestBody Request<CanteenSetmeal> canteenSetmeal) {
        return canteenSetmealService.find(canteenSetmeal);
    }

    @Override
    public Response Save(@RequestBody Request<CanteenSetmeal> canteenSetmeal) {
        return canteenSetmealService.save(canteenSetmeal);
    }

    @Override
    public Response Delete(@RequestBody Request<String> canteenSetmeal) {
        return canteenSetmealService.delete(canteenSetmeal);
    }


}
