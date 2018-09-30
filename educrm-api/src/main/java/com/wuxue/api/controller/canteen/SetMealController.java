package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.SetMealService;
import com.wuxue.model.SetMeal;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 套餐表基本信息
 */
@RestController
@RequestMapping(value = "api/canteen/setMeal")
public class SetMealController implements IFindController<SetMeal>,
        ISaveController<SetMeal>,IDeleteController<String>{
    @Autowired
    private SetMealService setMealService;

    @Override
    public Response Find(@RequestBody Request<SetMeal> setMeal) {
        return setMealService.find(setMeal);
    }

    @Override
    public Response Save(@RequestBody Request<SetMeal> setMeal) {
        return setMealService.save(setMeal);
    }

    @Override
    public Response Delete(@RequestBody Request<String> setMeal) {
        return setMealService.delete(setMeal);
    }

    @RequestMapping(value = "/mealstatistics",method = RequestMethod.POST)
    public Response mealStatistics(@RequestBody Request<SetMeal> request){
        return setMealService.mealStatistics(request);
    }

    /**
     * 通过套餐主键查询套餐详情
     * @param setMealId
     * @return
     */
    @RequestMapping(value = "/findOnlySetMealById",method = RequestMethod.POST)
    public Response findOnlySetMealById(@RequestBody String setMealId){
        Response response = Response.newResponse();
        if(StringUtils.isEmpty(setMealId)){
            return response.PARAMS_ISNULL();
        }
        SetMeal setMeal = setMealService.findOnlySetMealById(setMealId);
        response.setData(setMeal);
        return response;
    }

    @RequestMapping(value = "/querydefault",method = RequestMethod.POST)
    public Response queryDefault(@RequestBody Request<SetMeal> request){
        return setMealService.queryDefault(request);
    }

    @RequestMapping(value = "/querycode",method = RequestMethod.POST)
    public Response queryCode(@RequestBody Request<SetMeal> request){
        return setMealService.queryCode(request);
    }
}
