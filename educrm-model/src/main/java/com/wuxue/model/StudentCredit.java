package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentCredit extends BaiscPage {
    private String pkStudentCredit;

    private String pkDomain;

    private String pkStudent;

    private String schoolName;

    private Date startDate;

    private Date endDate;

    private String typeCourse;

    private String coursesGrades;

    private Integer isofficialTranscript;

    private String notes;

    public String getPkStudentCredit() {
        return pkStudentCredit;
    }

    public void setPkStudentCredit(String pkStudentCredit) {
        this.pkStudentCredit = pkStudentCredit;
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

    public String getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(String typeCourse) {
        this.typeCourse = typeCourse;
    }

    public String getCoursesGrades() {
        return coursesGrades;
    }

    public void setCoursesGrades(String coursesGrades) {
        this.coursesGrades = coursesGrades;
    }

    public Integer getIsofficialTranscript() {
        return isofficialTranscript;
    }

    public void setIsofficialTranscript(Integer isofficialTranscript) {
        this.isofficialTranscript = isofficialTranscript;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}