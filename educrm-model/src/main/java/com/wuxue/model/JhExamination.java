package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class JhExamination extends BaiscPage {
    private String pkExamination;

    private String caption;

    private Date startTime;

    private Date endTime;

    private String creator;

    private Date creationDate;

    private String modifier;

    private Date lasteditDate;

    private Integer status;

    private Integer isdel;

    private String section;

    private String object;

    private String datas;

    private String infos;

    public String getPkExamination() {
        return pkExamination;
    }

    public void setPkExamination(String pkExamination) {
        this.pkExamination = pkExamination;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }
}