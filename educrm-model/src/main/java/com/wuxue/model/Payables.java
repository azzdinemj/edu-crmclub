package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;
import java.util.Date;

public class Payables extends BusinessPage {
    private String pkPayables;

    private String pkDomain;

    private String pkParent;

    private String pkSysUser;

    private Integer kind;

    private String pkStudent;

    private String pkExpenseItem;

    private String code;

    private Date date;

    private BigDecimal cost;

    private String notes;

    private String dateTime;

    private Integer status;

    private String caption;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPkPayables() {
        return pkPayables;
    }

    public void setPkPayables(String pkPayables) {
        this.pkPayables = pkPayables;
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

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}