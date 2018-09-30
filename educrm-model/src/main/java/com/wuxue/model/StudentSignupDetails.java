package com.wuxue.model;

import com.wuxue.base.Page;

import java.math.BigDecimal;

public class StudentSignupDetails extends Page {
    private String pkStudentSignupDetails;

    private String pkDomain;

    private String pkStudentSignup;

    private String pkExpenseItem;

    private BigDecimal money;

    private String memo;

    private String currency;

    private String unit;

    private Integer num;

    public String getPkStudentSignupDetails() {
        return pkStudentSignupDetails;
    }

    public void setPkStudentSignupDetails(String pkStudentSignupDetails) {
        this.pkStudentSignupDetails = pkStudentSignupDetails;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkStudentSignup() {
        return pkStudentSignup;
    }

    public void setPkStudentSignup(String pkStudentSignup) {
        this.pkStudentSignup = pkStudentSignup;
    }

    public String getPkExpenseItem() {
        return pkExpenseItem;
    }

    public void setPkExpenseItem(String pkExpenseItem) {
        this.pkExpenseItem = pkExpenseItem;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}