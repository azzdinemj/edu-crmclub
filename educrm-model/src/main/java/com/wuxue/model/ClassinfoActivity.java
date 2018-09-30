package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;
import java.util.Date;

public class ClassinfoActivity extends BusinessPage {
    private String pkClassinfoActivity;

    private String pkDomain;

    private String pkClassinfo;

    private String pkExpenseItem;

    private String activityType;

    private String activityName;

    private Date startDate;

    private Date endDate;

    private BigDecimal cost;

    private String notes;

    public String getPkClassinfoActivity() {
        return pkClassinfoActivity;
    }

    public void setPkClassinfoActivity(String pkClassinfoActivity) {
        this.pkClassinfoActivity = pkClassinfoActivity;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getPkExpenseItem() {
        return pkExpenseItem;
    }

    public void setPkExpenseItem(String pkExpenseItem) {
        this.pkExpenseItem = pkExpenseItem;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

}