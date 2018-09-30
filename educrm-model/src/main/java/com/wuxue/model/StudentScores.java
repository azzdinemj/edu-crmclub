package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;
import java.util.Date;

public class StudentScores extends BaiscPage {
    private String pkStudentTestPlansScores;

    private String code;

    private String pkDomain;

    private String pkStudent;

    private String pkCource;

    private String pkEmployee;

    private String pkClassinfo;

    private String pkPlans;

    private String pkStudentTestPlans;

    private Float scores;

    private String memo;

    private Float weight;

    private Date year;

    private String yearTime;//学年

    private String term;

    private String caption;

    public String getPkStudentTestPlansScores() {
        return pkStudentTestPlansScores;
    }

    public void setPkStudentTestPlansScores(String pkStudentTestPlansScores) {
        this.pkStudentTestPlansScores = pkStudentTestPlansScores;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkCource() {
        return pkCource;
    }

    public void setPkCource(String pkCource) {
        this.pkCource = pkCource;
    }

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getPkPlans() {
        return pkPlans;
    }

    public void setPkPlans(String pkPlans) {
        this.pkPlans = pkPlans;
    }

    public String getPkStudentTestPlans() {
        return pkStudentTestPlans;
    }

    public void setPkStudentTestPlans(String pkStudentTestPlans) {
        this.pkStudentTestPlans = pkStudentTestPlans;
    }

    public Float getScores() {
        return scores;
    }

    public void setScores(Float scores) {
        this.scores = scores;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float  getWeight() {
        return weight;
    }

    public void setWeight(Float  weight) {
        this.weight = weight;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYearTime() {
        return yearTime;
    }

    public void setYearTime(String yearTime) {
        this.yearTime = yearTime;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}