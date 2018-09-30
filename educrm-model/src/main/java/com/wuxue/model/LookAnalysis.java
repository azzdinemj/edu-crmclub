package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class LookAnalysis extends BusinessPage {
    private String pkLookAnalysis;

    private String pkNutConAnalysis;

    private String pkLinkman;

    private String pkStudent;

    private Date viewDate;

    public String getPkLookAnalysis() {
        return pkLookAnalysis;
    }

    public void setPkLookAnalysis(String pkLookAnalysis) {
        this.pkLookAnalysis = pkLookAnalysis;
    }

    public String getPkNutConAnalysis() {
        return pkNutConAnalysis;
    }

    public void setPkNutConAnalysis(String pkNutConAnalysis) {
        this.pkNutConAnalysis = pkNutConAnalysis;
    }

    public String getPkLinkman() {
        return pkLinkman;
    }

    public void setPkLinkman(String pkLinkman) {
        this.pkLinkman = pkLinkman;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }
}