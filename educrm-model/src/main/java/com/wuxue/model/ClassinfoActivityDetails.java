package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;

public class ClassinfoActivityDetails extends BaiscPage {
    private String pkClassActivityDetails;

    private String pkClassActivity;

    private String pkStudent;

    private BigDecimal score;

    private String notes;


    public String getPkClassActivityDetails() {
        return pkClassActivityDetails;
    }

    public void setPkClassActivityDetails(String pkClassActivityDetails) {
        this.pkClassActivityDetails = pkClassActivityDetails;
    }

    public String getPkClassActivity() {
        return pkClassActivity;
    }

    public void setPkClassActivity(String pkClassActivity) {
        this.pkClassActivity = pkClassActivity;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}