package com.wuxue.model;

import com.wuxue.base.BaiscPage;

/**
 * @author tly
 * @date 2018-07-25
 * 原料表
 */
public class MaterialNutrition extends BaiscPage  {
    /**
     * 编号
     */
    private String code;
    /**
     * 原料名称
     */
    private String name;

    /**
     * 状态：启用 ENABLE，未启用 DISABLE
     */
    private String status;
    /**
     * 可食部分
     */
    private Double canEatPercent;
    /**
     * 能量（千卡）
     */
    private Double energy;
    /**
     * 水分（克）
     */
    private  Double moisture;
    /**
     * 蛋白质（克）
     */
    private  Double protein;
    /**
     * 脂肪（克）
     */
    private  Double fat;
    /**
     * 膳食纤维（克）
     */
    private  Double dietaryFiber;
    /**
     * 碳水化物（克）
     */
    private  Double carbohydrate;
    /**
     * 维生素A
     */
    private  Double vitaminA;
    /**
     * 维生素B1(毫克)
     */
    private  Double vitaminB1;
    /**
     * 维生素B2（毫克）
     */
    private  Double vitaminB2;
    /**
     * 烟酸(毫克)
     */
    private  Double niacin;
    /**
     * 维生素E
     */
    private  Double vitaminE;
    /**
     * 钠（毫克）
     */
    private  Double sodium;
    /**
     * 钙（毫克）
     */
    private  Double calcium;
    /**
     * 铁（毫克）
     */
    private  Double iron;
    /**
     * 类别
     */
    private Integer categoryId;
    /**
     * 维生素C(毫克)
     */
    private  Double vitaminC;

    /**
     * 胆固醇（毫克）
     */
    private  Double cholesterol;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCanEatPercent() {
        return canEatPercent;
    }

    public void setCanEatPercent(Double canEatPercent) {
        this.canEatPercent = canEatPercent;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture) {
        this.moisture = moisture;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(Double dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(Double vitaminA) {
        this.vitaminA = vitaminA;
    }

    public Double getVitaminB1() {
        return vitaminB1;
    }

    public void setVitaminB1(Double vitaminB1) {
        this.vitaminB1 = vitaminB1;
    }

    public Double getVitaminB2() {
        return vitaminB2;
    }

    public void setVitaminB2(Double vitaminB2) {
        this.vitaminB2 = vitaminB2;
    }

    public Double getNiacin() {
        return niacin;
    }

    public void setNiacin(Double niacin) {
        this.niacin = niacin;
    }

    public Double getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(Double vitaminE) {
        this.vitaminE = vitaminE;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Double vitaminC) {
        this.vitaminC = vitaminC;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }
}