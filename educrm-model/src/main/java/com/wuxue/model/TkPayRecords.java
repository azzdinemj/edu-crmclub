package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class TkPayRecords extends BusinessPage {
    private String pkPayRecords;

    private String pkStudent;

    private String pkSetMeal;

    private Integer payType;

    private Double amount;

    private String fee;

    private String body;

    private String title;

    private  Integer status;//0已支付 1未支付 2已取消
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getPkPayRecords() {
        return pkPayRecords;
    }

    public void setPkPayRecords(String pkPayRecords) {
        this.pkPayRecords = pkPayRecords;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkSetMeal() {
        return pkSetMeal;
    }

    public void setPkSetMeal(String pkSetMeal) {
        this.pkSetMeal = pkSetMeal;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}