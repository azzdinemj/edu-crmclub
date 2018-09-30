package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentPlansCaption extends BaiscPage {
    private String pkStudentTestPlans;

    private String code;

    private String pkDomain;

    private String caption;

    private String pkClassinfo;

    private Date date;

    private String dateTime;

    private Integer isvalid;

    private String details;

    private String memo;

    public String getPkStudentTestPlans() {
        return pkStudentTestPlans;
    }

    public void setPkStudentTestPlans(String pkStudentTestPlans) {
        this.pkStudentTestPlans = pkStudentTestPlans;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}