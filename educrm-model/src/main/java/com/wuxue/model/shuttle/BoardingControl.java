package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class BoardingControl extends BusinessPage{
    private String pkBoardingControl;

    private String pkDomain;

    private String siteId;

    private String pkSchoolBus;

    private Integer studentNum;

    private Integer status;

    private Integer type;

    public String getPkBoardingControl() {
        return pkBoardingControl;
    }

    public void setPkBoardingControl(String pkBoardingControl) {
        this.pkBoardingControl = pkBoardingControl;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getPkSchoolBus() {
        return pkSchoolBus;
    }

    public void setPkSchoolBus(String pkSchoolBus) {
        this.pkSchoolBus = pkSchoolBus;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}