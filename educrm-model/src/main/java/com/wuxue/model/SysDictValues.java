package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class SysDictValues extends BaiscPage {
    private String pkSysDictValues;

    private String pkDomain;

    private String pkSysDict;

    private String caption;

    private String value;

    private Integer kind;

    private String memo;

    private Integer isdefault;

    private Integer isvalid;

    private Integer sort;

    private String creationDateTime;

    private String lasteditDateTime;

    public String getPkSysDictValues() {
        return pkSysDictValues;
    }

    public void setPkSysDictValues(String pkSysDictValues) {
        this.pkSysDictValues = pkSysDictValues;
    }

    public String getPkSysDict() {
        return pkSysDict;
    }

    public void setPkSysDict(String pkSysDict) {
        this.pkSysDict = pkSysDict;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getLasteditDateTime() {
        return lasteditDateTime;
    }

    public void setLasteditDateTime(String lasteditDateTime) {
        this.lasteditDateTime = lasteditDateTime;
    }
}