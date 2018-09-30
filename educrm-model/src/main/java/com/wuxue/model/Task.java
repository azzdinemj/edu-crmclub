package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class Task extends BaiscPage{
    private String pkTask;

    private String pkUser;

    private Integer isdel;

    private Date endDate;

    private String content;

    private Integer type;

    public String getPkTask() {
        return pkTask;
    }

    public void setPkTask(String pkTask) {
        this.pkTask = pkTask;
    }

    public String getPkUser() {
        return pkUser;
    }

    public void setPkUser(String pkUser) {
        this.pkUser = pkUser;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}