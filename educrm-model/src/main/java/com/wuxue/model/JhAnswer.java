package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class JhAnswer extends BaiscPage {
    private String pkAnswer;

    private String pkExamination;

    private String pkQuestion;

    private String option;

    private Date creationDate;

    private String openid;


    private String motherPhone;

    private String motherName;

    private String fatherName;

    private String fatherPhone;

    private String childrenName;

    private String childrenGrade;

    public String getPkAnswer() {
        return pkAnswer;
    }

    public void setPkAnswer(String pkAnswer) {
        this.pkAnswer = pkAnswer;
    }

    public String getPkExamination() {
        return pkExamination;
    }

    public void setPkExamination(String pkExamination) {
        this.pkExamination = pkExamination;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public String getChildrenGrade() {
        return childrenGrade;
    }

    public void setChildrenGrade(String childrenGrade) {
        this.childrenGrade = childrenGrade;
    }

    public String getPkQuestion() {
        return pkQuestion;
    }

    public void setPkQuestion(String pkQuestion) {
        this.pkQuestion = pkQuestion;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}