package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;
import java.util.Date;

public class SetMealNutrition extends BusinessPage {
    private String pkMealNutrition;

    private String pkSetMeal;

    private String mealNutritionId;

    private BigDecimal content;

    private BigDecimal proportion;

    private Integer isvalid;

    public String getPkMealNutrition() {
        return pkMealNutrition;
    }

    public void setPkMealNutrition(String pkMealNutrition) {
        this.pkMealNutrition = pkMealNutrition;
    }

    public String getPkSetMeal() {
        return pkSetMeal;
    }

    public void setPkSetMeal(String pkSetMeal) {
        this.pkSetMeal = pkSetMeal;
    }

    public String getMealNutritionId() {
        return mealNutritionId;
    }

    public void setMealNutritionId(String mealNutritionId) {
        this.mealNutritionId = mealNutritionId;
    }

    public BigDecimal getContent() {
        return content;
    }

    public void setContent(BigDecimal content) {
        this.content = content;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

}