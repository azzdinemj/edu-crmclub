package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;
import java.util.Date;

public class Receipt extends BusinessPage {
    private String pkReceipt;

    private String pkDomain;

    private String pkParent;

    private String pkStudent;

    private String pkExpenseItem;

    private String pkSysUser;

    private String code;

    private String paymentMethod;

    private String bankAccountCaption;

    private String bankAccount;

    private String bankCaption;

    private String bankAddress;

    private Date date;

    private String dateTime;

    private String currency;

    private BigDecimal cost;

    private BigDecimal money;

    private String notes;

    private Integer ispayment;

    private String payer;

    private String caption;

    /********************************/
    private String schoolYear;

    public String getPkReceipt() {
        return pkReceipt;
    }

    public void setPkReceipt(String pkReceipt) {
        this.pkReceipt = pkReceipt;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkParent() {
        return pkParent;
    }

    public void setPkParent(String pkParent) {
        this.pkParent = pkParent;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkExpenseItem() {
        return pkExpenseItem;
    }

    public void setPkExpenseItem(String pkExpenseItem) {
        this.pkExpenseItem = pkExpenseItem;
    }

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBankAccountCaption() {
        return bankAccountCaption;
    }

    public void setBankAccountCaption(String bankAccountCaption) {
        this.bankAccountCaption = bankAccountCaption;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankCaption() {
        return bankCaption;
    }

    public void setBankCaption(String bankCaption) {
        this.bankCaption = bankCaption;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getIspayment() {
        return ispayment;
    }

    public void setIspayment(Integer ispayment) {
        this.ispayment = ispayment;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}