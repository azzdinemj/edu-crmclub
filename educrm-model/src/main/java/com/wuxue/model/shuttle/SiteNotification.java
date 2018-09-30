package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class SiteNotification extends BusinessPage {
    private String pkSiteNotification;

    private String pkDomain;

    private String code;

    private String pkSchoolBus;

    private String pkSchoolBusLine;

    private Date arrivalDate;

    private Integer state;

    private Integer sendMany;

    private String memo;


    public String getPkSiteNotification() {
        return pkSiteNotification;
    }

    public void setPkSiteNotification(String pkSiteNotification) {
        this.pkSiteNotification = pkSiteNotification;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPkSchoolBus() {
        return pkSchoolBus;
    }

    public void setPkSchoolBus(String pkSchoolBus) {
        this.pkSchoolBus = pkSchoolBus;
    }

    public String getPkSchoolBusLine() {
        return pkSchoolBusLine;
    }

    public void setPkSchoolBusLine(String pkSchoolBusLine) {
        this.pkSchoolBusLine = pkSchoolBusLine;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSendMany() {
        return sendMany;
    }

    public void setSendMany(Integer sendMany) {
        this.sendMany = sendMany;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}