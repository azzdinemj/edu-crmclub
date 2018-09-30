package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class Parentconfirm extends BusinessPage {
    private String pkConfirm;

    private String pkLinkman;

    private String pkDebusRecord;

    private String pkSchoolBus;

    private Integer pkStudent;

    private Integer status;

    public String getPkConfirm() {
        return pkConfirm;
    }

    public void setPkConfirm(String pkConfirm) {
        this.pkConfirm = pkConfirm;
    }

    public String getPkLinkman() {
        return pkLinkman;
    }

    public void setPkLinkman(String pkLinkman) {
        this.pkLinkman = pkLinkman;
    }

    public String getPkDebusRecord() {
        return pkDebusRecord;
    }

    public void setPkDebusRecord(String pkDebusRecord) {
        this.pkDebusRecord = pkDebusRecord;
    }

    public String getPkSchoolBus() {
        return pkSchoolBus;
    }

    public void setPkSchoolBus(String pkSchoolBus) {
        this.pkSchoolBus = pkSchoolBus;
    }

    public Integer getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(Integer pkStudent) {
        this.pkStudent = pkStudent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}