package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class SysRole extends BaiscPage {
    private String pkSysRole;

    private String pkDomain;

    private String code;

    private String caption;

    private Integer isvalid;

    private String memo;

    private String lasteditDateTime;

    private String creationDateTime;

    public String getPkSysRole() {
        return pkSysRole;
    }

    public void setPkSysRole(String pkSysRole) {
        this.pkSysRole = pkSysRole;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLasteditDateTime() {
        return lasteditDateTime;
    }

    public void setLasteditDateTime(String lasteditDateTime) {
        this.lasteditDateTime = lasteditDateTime;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}