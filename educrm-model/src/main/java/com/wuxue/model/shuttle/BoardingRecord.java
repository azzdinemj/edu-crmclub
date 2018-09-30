package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class BoardingRecord extends BusinessPage {
    private String pkBoardingRecord;

    private String pkDomain;

    private String schoolBus;

    private String siteId;

    private Date date;

    private String dateTime;

    private String pkStudent;

    private String pkSysUser;

    private Integer status;

    private Byte direction;//1=校外开往校内，2=校内开往校外

    public Byte getDirection() {
        return direction;
    }

    public void setDirection(Byte direction) {
        this.direction = direction;
    }


    public String getPkBoardingRecord() {
        return pkBoardingRecord;
    }

    public void setPkBoardingRecord(String pkBoardingRecord) {
        this.pkBoardingRecord = pkBoardingRecord;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getSchoolBus() {
        return schoolBus;
    }

    public void setSchoolBus(String schoolBus) {
        this.schoolBus = schoolBus;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}