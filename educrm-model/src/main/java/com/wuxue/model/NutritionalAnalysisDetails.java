package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;

public class NutritionalAnalysisDetails extends BusinessPage {
    private String analysisDetailes;

    private String nutConAnalysisId;

    private String contentAnalysisId;

    private String pkStudent;

    private BigDecimal maximalValue;

    private BigDecimal minimumValue;

    private BigDecimal content;

    private Boolean status;

    public String getAnalysisDetailes() {
        return analysisDetailes;
    }

    public void setAnalysisDetailes(String analysisDetailes) {
        this.analysisDetailes = analysisDetailes;
    }

    public String getNutConAnalysisId() {
        return nutConAnalysisId;
    }

    public void setNutConAnalysisId(String nutConAnalysisId) {
        this.nutConAnalysisId = nutConAnalysisId;
    }

    public String getContentAnalysisId() {
        return contentAnalysisId;
    }

    public void setContentAnalysisId(String contentAnalysisId) {
        this.contentAnalysisId = contentAnalysisId;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public BigDecimal getMaximalValue() {
        return maximalValue;
    }

    public void setMaximalValue(BigDecimal maximalValue) {
        this.maximalValue = maximalValue;
    }

    public BigDecimal getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(BigDecimal minimumValue) {
        this.minimumValue = minimumValue;
    }

    public BigDecimal getContent() {
        return content;
    }

    public void setContent(BigDecimal content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}