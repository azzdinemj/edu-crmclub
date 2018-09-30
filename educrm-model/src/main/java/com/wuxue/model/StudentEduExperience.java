package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentEduExperience extends BaiscPage {
    private String pkStudentEduExperience;

    private String pkDomain;

    private String pkStudent;

    private String schoolName;

    private String schoolAddress;

    private Date startDate;

    private Date endDate;

    private String startDateTime;

    private String endDateTime;

    private Integer academicRecord;

    private String notes;

    public String getPkStudentEduExperience() {
        return pkStudentEduExperience;
    }

    public void setPkStudentEduExperience(String pkStudentEduExperience) {
        this.pkStudentEduExperience = pkStudentEduExperience;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAcademicRecord() {
        return academicRecord;
    }

    public void setAcademicRecord(Integer academicRecord) {
        this.academicRecord = academicRecord;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}