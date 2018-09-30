package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class NoticeRecord extends BusinessPage {
    private String pkNoticeRecord;

    private String pkDomain;

    private String code;

    private String pkNotificationRecord;

    private Date date;

    private String pkSchoolBus;

    private String lineNum;

    private Integer returnValue;

    private Integer num;

    private Integer wxNoticeStatus;

    public String getPkNoticeRecord() {
        return pkNoticeRecord;
    }

    public void setPkNoticeRecord(String pkNoticeRecord) {
        this.pkNoticeRecord = pkNoticeRecord;
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

    public String getPkNotificationRecord() {
        return pkNotificationRecord;
    }

    public void setPkNotificationRecord(String pkNotificationRecord) {
        this.pkNotificationRecord = pkNotificationRecord;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPkSchoolBus() {
        return pkSchoolBus;
    }

    public void setPkSchoolBus(String pkSchoolBus) {
        this.pkSchoolBus = pkSchoolBus;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public Integer getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Integer returnValue) {
        this.returnValue = returnValue;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getWxNoticeStatus() {
        return wxNoticeStatus;
    }

    public void setWxNoticeStatus(Integer wxNoticeStatus) {
        this.wxNoticeStatus = wxNoticeStatus;
    }
}