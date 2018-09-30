package com.wuxue.model.junhwa;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;

public class MealMaterial extends BaiscPage {
    private String mealMaterialId;

    private String setMealId;

    private String materialId;

    private BigDecimal gram;

    private String materialName;

    public String getMealMaterialId() {
        return mealMaterialId;
    }

    public void setMealMaterialId(String mealMaterialId) {
        this.mealMaterialId = mealMaterialId;
    }

    public String getSetMealId() {
        return setMealId;
    }

    public void setSetMealId(String setMealId) {
        this.setMealId = setMealId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getGram() {
        return gram;
    }

    public void setGram(BigDecimal gram) {
        this.gram = gram;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}