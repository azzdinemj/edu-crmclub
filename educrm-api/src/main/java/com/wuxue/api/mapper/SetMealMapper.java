package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.SetMeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetMealMapper extends IInsertMapper<SetMeal>,ICountMapper<SetMeal,Integer>,
        IUpdateMapper<SetMeal>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SetMeal>,ISelectMapper<SetMeal,List<SetMeal>>{
    /**
     * 根据id找套餐信息
     * @author tly
     * @param setMealIds
     * @return
     */
    List<SetMeal> findSetMealByIds(@Param("setMealIds")List<String> setMealIds);

    /**
     * 根据条件获取所有点餐数量
     * */
    List<SetMeal> mealStatistics(SetMeal setMeal);

    /**
     * 查询过敏源
     * */
    List<SetMeal> findAllergy(SetMeal setMeal);


    /**
     * 获取套餐营养含量
     * */
    List<SetMeal> selectNutrition(SetMeal setMeal);

    /**
     * 查询默认套餐编号
     * */
    List<SetMeal> queryDefaultCode(SetMeal setMeal);
}