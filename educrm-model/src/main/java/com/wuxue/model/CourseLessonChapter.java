package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class CourseLessonChapter extends BaiscPage {
    private String pkCourseLesson;

    private String pkCourse;

    private String pkChapter;

    private Integer sheetType;

    private String caption;

    private Integer sort;

    private String type;

    private String url;

    private Integer isopen;

    private String instruction;

    private Integer status;

    private Integer isdel;

    public String getPkCourseLesson() {
        return pkCourseLesson;
    }

    public void setPkCourseLesson(String pkCourseLesson) {
        this.pkCourseLesson = pkCourseLesson;
    }

    public String getPkCourse() {
        return pkCourse;
    }

    public void setPkCourse(String pkCourse) {
        this.pkCourse = pkCourse;
    }

    public Integer getSheetType() {
        return sheetType;
    }

    public void setSheetType(Integer sheetType) {
        this.sheetType = sheetType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getPkChapter() {
        return pkChapter;
    }

    public void setPkChapter(String pkChapter) {
        this.pkChapter = pkChapter;
    }
}