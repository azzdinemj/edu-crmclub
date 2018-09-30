package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentAwards extends BaiscPage {
    private String pkStudentAwards;

    private String pkDomain;

    private String pkStudent;

    private String grade;

    private String activityLevel;

    private String activityName;

    private Date date;

    private String dateTime;

    private String notes;

    public String getPkStudentAwards() {
        return pkStudentAwards;
    }

    public void setPkStudentAwards(String pkStudentAwards) {
        this.pkStudentAwards = pkStudentAwards;
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

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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