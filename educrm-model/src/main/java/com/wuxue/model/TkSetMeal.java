package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class TkSetMeal extends BusinessPage {
    private String pkSetMeal;

    private String caption;

    private Integer number;

    private Integer status;

    private String memo;

    private Double currentPrice;//现价

    private Double price;//原价

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}