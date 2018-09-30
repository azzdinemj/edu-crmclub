package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentWorksPortfolio extends BaiscPage {
    private String pkStudentWorksPortfolio;

    private String pkDomain;

    private String pkStudent;

    private String grade;

    private String typeWork;

    private Date date;

    private String dateTime;

    private String notes;

    public String getPkStudentWorksPortfolio() {
        return pkStudentWorksPortfolio;
    }

    public void setPkStudentWorksPortfolio(String pkStudentWorksPortfolio) {
        this.pkStudentWorksPortfolio = pkStudentWorksPortfolio;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(String typeWork) {
        this.typeWork = typeWork;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}