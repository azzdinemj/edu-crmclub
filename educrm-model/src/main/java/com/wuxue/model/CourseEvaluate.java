package com.wuxue.model;

import com.wuxue.base.BaiscPage;
import com.wuxue.base.BusinessPage;

import java.util.Date;

public class CourseEvaluate extends BaiscPage {
    private String pkCourseEvaluate;

    private String pkStudent;

    private String pkCourse;

    private String cotent;

    private Integer status;

    private Integer isdel;

    public String getPkCourseEvaluate() {
        return pkCourseEvaluate;
    }

    public void setPkCourseEvaluate(String pkCourseEvaluate) {
        this.pkCourseEvaluate = pkCourseEvaluate;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkCourse() {
        return pkCourse;
    }

    public void setPkCourse(String pkCourse) {
        this.pkCourse = pkCourse;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}