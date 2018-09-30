package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class NutritionalAnalysis extends BusinessPage {
    private String pkNutConAnalysis;

    private String pkDomain;

    private String pkStudent;

    private Integer dateType;

    private Date analysisDate;

    private Boolean analysisResults;

    private Integer isvalid;

    public String getPkNutConAnalysis() {
        return pkNutConAnalysis;
    }

    public void setPkNutConAnalysis(String pkNutConAnalysis) {
        this.pkNutConAnalysis = pkNutConAnalysis;
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

    public Boolean getAnalysisResults() {
        return analysisResults;
    }

    public void setAnalysisResults(Boolean analysisResults) {
        this.analysisResults = analysisResults;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

}