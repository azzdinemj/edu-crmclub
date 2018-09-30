package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class SchoolBusStudentNum extends BusinessPage {
    private String pkDomain;

    private Long stunum;

    private String caption;

    private String code;

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public Long getStunum() {
        return stunum;
    }

    public void setStunum(Long stunum) {
        this.stunum = stunum;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}