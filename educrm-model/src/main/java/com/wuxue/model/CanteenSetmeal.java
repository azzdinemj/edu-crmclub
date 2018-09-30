package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class CanteenSetmeal extends BusinessPage {
    private String pkCanteenMeal;

    private String code;

    private String pkCanteen;

    private String pkSetMeal;

    private Integer isvalid;

    public String getPkCanteenMeal() {
        return pkCanteenMeal;
    }

    public void setPkCanteenMeal(String pkCanteenMeal) {
        this.pkCanteenMeal = pkCanteenMeal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPkCanteen() {
        return pkCanteen;
    }

    public void setPkCanteen(String pkCanteen) {
        this.pkCanteen = pkCanteen;
    }

    public String getPkSetMeal() {
        return pkSetMeal;
    }

    public void setPkSetMeal(String pkSetMeal) {
        this.pkSetMeal = pkSetMeal;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}