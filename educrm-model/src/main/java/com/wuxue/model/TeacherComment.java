package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class TeacherComment extends BaiscPage {
    private String pkTeacherComment;

    private String pkDomain;

    private String pkStudent;

    private String semester;

    private Date year;

    private Integer type;

    private String discipline;

    private String content;

    private Integer status;

    private String years;

    public String getPkTeacherComment() {
        return pkTeacherComment;
    }

    public void setPkTeacherComment(String pkTeacherComment) {
        this.pkTeacherComment = pkTeacherComment;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}