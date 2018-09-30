package com.wuxue.model;

import java.util.Date;

public class CourseTeacher extends CourseTeacherKey {
    private Integer kind;

    private Integer isvalid;

    private String memo;

    private String teacherCaption;

    private String courseCaption;

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTeacherCaption() {
        return teacherCaption;
    }

    public void setTeacherCaption(String teacherCaption) {
        this.teacherCaption = teacherCaption;
    }

    public String getCourseCaption() {
        return courseCaption;
    }

    public void setCourseCaption(String courseCaption) {
        this.courseCaption = courseCaption;
    }
}