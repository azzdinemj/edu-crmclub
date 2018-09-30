package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;

public class StudentTestPlansScores extends BaiscPage {
    private String pkStudentTestPlansScores;

    private String pkStudentTestPlans;

    private String subjectName;

    private BigDecimal scores;

    private String notes;

    public String getPkStudentTestPlansScores() {
        return pkStudentTestPlansScores;
    }

    public void setPkStudentTestPlansScores(String pkStudentTestPlansScores) {
        this.pkStudentTestPlansScores = pkStudentTestPlansScores;
    }

    public String getPkStudentTestPlans() {
        return pkStudentTestPlans;
    }

    public void setPkStudentTestPlans(String pkStudentTestPlans) {
        this.pkStudentTestPlans = pkStudentTestPlans;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public BigDecimal getScores() {
        return scores;
    }

    public void setScores(BigDecimal scores) {
        this.scores = scores;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}