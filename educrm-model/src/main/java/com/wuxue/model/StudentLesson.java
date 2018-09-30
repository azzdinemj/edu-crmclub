package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class StudentLesson extends BaiscPage {
    private String pkStudentLesson;

    private String pkStudent;

    private String pkCourseChapter;

    private String pkCourse;

    private Integer num;

    private Integer status;

    private Integer isdel;

    public String getPkStudentLesson() {
        return pkStudentLesson;
    }

    public void setPkStudentLesson(String pkStudentLesson) {
        this.pkStudentLesson = pkStudentLesson;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkCourseChapter() {
        return pkCourseChapter;
    }

    public void setPkCourseChapter(String pkCourseChapter) {
        this.pkCourseChapter = pkCourseChapter;
    }

    public String getPkCourse() {
        return pkCourse;
    }

    public void setPkCourse(String pkCourse) {
        this.pkCourse = pkCourse;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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