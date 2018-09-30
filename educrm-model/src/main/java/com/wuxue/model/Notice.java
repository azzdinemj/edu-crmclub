package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class Notice extends BaiscPage{
    private String pkNotice;

    private String pkUser;

    private String ranges;

    private Integer isdel;

    private String title;

    private String content;

    private Integer type;

    private String pkStudent;

    private String pkLinkman;

    public String getPkNotice() {
        return pkNotice;
    }

    public void setPkNotice(String pkNotice) {
        this.pkNotice = pkNotice;
    }

    public String getPkUser() {
        return pkUser;
    }

    public void setPkUser(String pkUser) {
        this.pkUser = pkUser;
    }

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkLinkman() {
        return pkLinkman;
    }

    public void setPkLinkman(String pkLinkman) {
        this.pkLinkman = pkLinkman;
    }
}