package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentBehaviorRecord extends BaiscPage {
    private String pkStudentBehaviorRecord;

    private String pkDomain;

    private String pkStudent;

    private String grade;

    private Date date;

    private String dateTime;

    private String details;

    private String disciplinaryAction;

    private String notes;

    public String getPkStudentBehaviorRecord() {
        return pkStudentBehaviorRecord;
    }

    public void setPkStudentBehaviorRecord(String pkStudentBehaviorRecord) {
        this.pkStudentBehaviorRecord = pkStudentBehaviorRecord;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDisciplinaryAction() {
        return disciplinaryAction;
    }

    public void setDisciplinaryAction(String disciplinaryAction) {
        this.disciplinaryAction = disciplinaryAction;
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