package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class Homework extends BusinessPage{
    private String pkHomework;

    private String pkDomain;

    private String code;

    private String classinfo;

    private Date homeworkDate;

    private String homeworkDateTime;

    private Integer gradeNum;

    private String workType;

    private String homeworkContent;

    private String homeworkTitle;

    private Date sendDate;

    private Integer sendType;

    public String getPkHomework() {
        return pkHomework;
    }

    public void setPkHomework(String pkHomework) {
        this.pkHomework = pkHomework;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassinfo() {
        return classinfo;
    }

    public void setClassinfo(String classinfo) {
        this.classinfo = classinfo;
    }

    public Date getHomeworkDate() {
        return homeworkDate;
    }

    public void setHomeworkDate(Date homeworkDate) {
        this.homeworkDate = homeworkDate;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public String getHomeworkDateTime() {
        return homeworkDateTime;
    }

    public void setHomeworkDateTime(String homeworkDateTime) {
        this.homeworkDateTime = homeworkDateTime;
    }
}