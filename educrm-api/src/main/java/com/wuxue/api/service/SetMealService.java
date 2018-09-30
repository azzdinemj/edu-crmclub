package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SetMeal;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;
import java.util.Map;

public interface SetMealService extends ISaveService<SetMeal>,IFindService<SetMeal>,IDeleteService<String> {

    List<SetMeal> findByStudent(SetMeal setMeal,String pkStudent);

    /**
     * 获取套餐数量
     * */
    Response mealStatistics(Request<SetMeal> tParams);

    /**
     * 通过主键获取原料信息
     * @param id
     * @return
     */
    SetMeal findOnlySetMealById(String id);

    /**
     * 通过主键获取就餐记录信息
     * @param ids
     * @queryDefault
     */
    Map<String, SetMeal> getMealIdANdSetMealMap(List<String> ids);

    /**
     * 查询是否默认
     * */
    Response queryDefault(Request<SetMeal> request);

    /**
     *查询套餐编号是否重复
     */
    Response queryCode(Request<SetMeal> request);
}
