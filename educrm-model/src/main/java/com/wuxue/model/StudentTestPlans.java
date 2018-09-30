package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentTestPlans extends BaiscPage {
    private String pkStudentTestPlans;

    private String pkDomain;

    private String pkStudent;

    private String grade;

    private Date date;

    private String subjectType;

    private Integer istoeflorsat;

    private String notes;

    public String getPkStudentTestPlans() {
        return pkStudentTestPlans;
    }

    public void setPkStudentTestPlans(String pkStudentTestPlans) {
        this.pkStudentTestPlans = pkStudentTestPlans;
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

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public Integer getIstoeflorsat() {
        return istoeflorsat;
    }

    public void setIstoeflorsat(Integer istoeflorsat) {
        this.istoeflorsat = istoeflorsat;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}