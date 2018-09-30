package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class MealCount extends BaiscPage {

   private String setMealId;

   private Integer num;

   private Date mealDate;

    public String getSetMealId() {
        return setMealId;
    }

    public void setSetMealId(String setMealId) {
        this.setMealId = setMealId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }
}