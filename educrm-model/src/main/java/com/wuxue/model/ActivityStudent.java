package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class ActivityStudent extends BaiscPage {
    private String pkActivityStudent;

    private String pkStudent;

    private String pkClassinfo;

    private Integer score;

    private String notes;

    private String evaluate;

    private String className;

    private String studentName;

    public String getPkActivityStudent() {
        return pkActivityStudent;
    }

    public void setPkActivityStudent(String pkActivityStudent) {
        this.pkActivityStudent = pkActivityStudent;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}