package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class StudentInterviewRecord extends BusinessPage {
    private String pkStudentInterviewRecord;

    private String pkDomain;

    private String pkStudent;

    private String grade;

    private Date date;

    private String dateTime;

    private String what;

    private String advices;

    private String notes;

    private Integer isType;

    public String getPkStudentInterviewRecord() {
        return pkStudentInterviewRecord;
    }

    public void setPkStudentInterviewRecord(String pkStudentInterviewRecord) {
        this.pkStudentInterviewRecord = pkStudentInterviewRecord;
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

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getAdvices() {
        return advices;
    }

    public void setAdvices(String advices) {
        this.advices = advices;
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

    public Integer getIsType() {
        return isType;
    }

    public void setIsType(Integer isType) {
        this.isType = isType;
    }
}