package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class StudentAssign extends BaiscPage {
    private String pkStudentAssign;

    private String pkStudent;

    private String pkCourse;

    private Integer status;

    private Integer isdel;

    public String getPkStudentAssign() {
        return pkStudentAssign;
    }

    public void setPkStudentAssign(String pkStudentAssign) {
        this.pkStudentAssign = pkStudentAssign;
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