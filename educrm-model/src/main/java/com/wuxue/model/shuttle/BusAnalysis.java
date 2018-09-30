package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class BusAnalysis extends BusinessPage {
    private String pkBusAnalysis;

    private String pkDomain;

    private String code;

    private String pkSchoolBus;

    private Integer dateType;

    private Date analysisDate;

    private Integer runningTimes;

    private Integer personTimeNum;

    public String getPkBusAnalysis() {
        return pkBusAnalysis;
    }

    public void setPkBusAnalysis(String pkBusAnalysis) {
        this.pkBusAnalysis = pkBusAnalysis;
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

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public Date getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(Date analysisDate) {
        this.analysisDate = analysisDate;
    }

    public Integer getRunningTimes() {
        return runningTimes;
    }

    public void setRunningTimes(Integer runningTimes) {
        this.runningTimes = runningTimes;
    }

    public Integer getPersonTimeNum() {
        return personTimeNum;
    }

    public void setPersonTimeNum(Integer personTimeNum) {
        this.personTimeNum = personTimeNum;
    }
}