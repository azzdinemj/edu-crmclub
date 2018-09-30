package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class SetMealMaterial extends BusinessPage {

    //todo 字段不完整
    private String pkSetMealMaterial;

    private String pkDomain;

    private String pkSetMeal;

    private String caption;

    private String code;

    private Integer isvalid;

    private Double amount;


    public String getPkSetMealMaterial() {
        return pkSetMealMaterial;
    }

    public void setPkSetMealMaterial(String pkSetMealMaterial) {
        this.pkSetMealMaterial = pkSetMealMaterial;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkSetMeal() {
        return pkSetMeal;
    }

    public void setPkSetMeal(String pkSetMeal) {
        this.pkSetMeal = pkSetMeal;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}