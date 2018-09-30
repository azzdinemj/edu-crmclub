package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class StudentPhaseRecord extends BusinessPage {
    private String pkStudentPhaseRecord;

    private String pkDomain;

    private String code;

    private Date stageTime;

    private String type;

    private String pkClassinfo;

    public String getPkStudentPhaseRecord() {
        return pkStudentPhaseRecord;
    }

    public void setPkStudentPhaseRecord(String pkStudentPhaseRecord) {
        this.pkStudentPhaseRecord = pkStudentPhaseRecord;
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

    public Date getStageTime() {
        return stageTime;
    }

    public void setStageTime(Date stageTime) {
        this.stageTime = stageTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }
}