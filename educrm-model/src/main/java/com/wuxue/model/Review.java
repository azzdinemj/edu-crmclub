package com.wuxue.model;

import com.wuxue.base.BaiscPage;


public class Review extends BaiscPage {
    private String pkReview;

    private String pkData;

    public String getPkDataCaption() {
        return pkDataCaption;
    }

    public void setPkDataCaption(String pkDataCaption) {
        this.pkDataCaption = pkDataCaption;
    }

    private String pkDataCaption;

    private String cotent;

    private Integer type;

    private Integer status;

    private Integer isdel;

    public String getPkReview() {
        return pkReview;
    }

    public void setPkReview(String pkReview) {
        this.pkReview = pkReview;
    }

    public String getPkData() {
        return pkData;
    }

    public void setPkData(String pkData) {
        this.pkData = pkData;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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