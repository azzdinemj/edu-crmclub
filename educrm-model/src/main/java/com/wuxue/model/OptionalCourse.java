package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class OptionalCourse extends BusinessPage {
    private String pkSchedul;

    private String domain;

    private String code;

    private String schedulCaption;

    private Integer num;

    private Integer isvalid;


    public String getPkSchedul() {
        return pkSchedul;
    }

    public void setPkSchedul(String pkSchedul) {
        this.pkSchedul = pkSchedul;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSchedulCaption() {
        return schedulCaption;
    }

    public void setSchedulCaption(String schedulCaption) {
        this.schedulCaption = schedulCaption;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}