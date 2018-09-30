package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class AskForLeave extends BaiscPage {
    public String getStuEmpCaption() {
        return stuEmpCaption;
    }

    public void setStuEmpCaption(String stuEmpCaption) {
        this.stuEmpCaption = stuEmpCaption;
    }

    private String stuEmpCaption;

    private String pkSubject;

    private  String startTimeStr;

    private  String endTimeStr;

    private String pkAskForLeave;

    private String pkStudentEmployee;

    private Date startTime;

    private Date endTime;

    private String types;

    private String remark;

    private String status;

    private String statusRemark;

    private String creator;

    private Date creationDate;

    private String modifier;

    private Date lasteditDate;

    private String pkLinkman;

    private Integer isaudit;

    private Integer isvalid;

    public String getPkAskForLeave() {
        return pkAskForLeave;
    }

    public void setPkAskForLeave(String pkAskForLeave) {
        this.pkAskForLeave = pkAskForLeave;
    }

    public String getPkStudentEmployee() {
        return pkStudentEmployee;
    }

    public void setPkStudentEmployee(String pkStudentEmployee) {
        this.pkStudentEmployee = pkStudentEmployee;
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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusRemark() {
        return statusRemark;
    }

    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark;
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
    public String getPkSubject() {
        return pkSubject;
    }

    public void setPkSubject(String pkSubject) {
        this.pkSubject = pkSubject;
    }
    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }
    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getPkLinkman() {
        return pkLinkman;
    }

    public void setPkLinkman(String pkLinkman) {
        this.pkLinkman = pkLinkman;
    }

    public Integer getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Integer isaudit) {
        this.isaudit = isaudit;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}