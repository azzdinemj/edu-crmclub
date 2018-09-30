package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class JhOption extends BaiscPage {
    private String pkOption;

    private String pkQuestion;

    private String pkExamination;

    private String mark;

    private String caption;

    private String creator;

    private Date creationDate;

    private String modifier;

    private Date lasteditDate;

    private Integer status;

    private Integer isdel;

    private Integer sort;

    private Integer num;

    private Integer peopleNum;

    public String getPkOption() {
        return pkOption;
    }

    public void setPkOption(String pkOption) {
        this.pkOption = pkOption;
    }

    public String getPkQuestion() {
        return pkQuestion;
    }

    public void setPkQuestion(String pkQuestion) {
        this.pkQuestion = pkQuestion;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLasteditDate() {
        return lasteditDate;
    }

    public void setLasteditDate(Date lasteditDate) {
        this.lasteditDate = lasteditDate;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPkExamination() {
        return pkExamination;
    }

    public void setPkExamination(String pkExamination) {
        this.pkExamination = pkExamination;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }
}