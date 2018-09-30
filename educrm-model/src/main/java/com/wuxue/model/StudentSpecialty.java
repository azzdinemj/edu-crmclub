package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class StudentSpecialty extends BaiscPage {
    private String pkStudentSpecialty;

    private String pkDomain;

    private String pkStudent;

    private String specialty;

    private String studyTime;

    private String awardsAchievements;

    private String notes;

    public String getPkStudentSpecialty() {
        return pkStudentSpecialty;
    }

    public void setPkStudentSpecialty(String pkStudentSpecialty) {
        this.pkStudentSpecialty = pkStudentSpecialty;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getAwardsAchievements() {
        return awardsAchievements;
    }

    public void setAwardsAchievements(String awardsAchievements) {
        this.awardsAchievements = awardsAchievements;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}