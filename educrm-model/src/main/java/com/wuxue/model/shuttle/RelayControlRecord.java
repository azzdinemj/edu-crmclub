package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class RelayControlRecord extends BusinessPage {
    private String pkRelayControlRecord;

    private String pkDomain;

    private String qrCode;

    private String pkStudent;

    private String result;

    private Date noticeDate;

    private String pkLinkman;

    public String getPkRelayControlRecord() {
        return pkRelayControlRecord;
    }

    public void setPkRelayControlRecord(String pkRelayControlRecord) {
        this.pkRelayControlRecord = pkRelayControlRecord;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getPkLinkman() {
        return pkLinkman;
    }

    public void setPkLinkman(String pkLinkman) {
        this.pkLinkman = pkLinkman;
    }
}