package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class SysAutocode extends BaiscPage {
    private String pkSysAutocode;

    private String pkDomain;

    private String caption;

    private String prefix;

    private Integer isline;

    private Integer zeroWidth;

    private String date;

    private String kind;

    private Integer isvalid;

    private Integer sort;

    private String createDateStr;

    private String lastEditDateStr;

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getLastEditDateStr() {
        return lastEditDateStr;
    }

    public void setLastEditDateStr(String lastEditDateStr) {
        this.lastEditDateStr = lastEditDateStr;
    }

    public String getPkSysAutocode() {
        return pkSysAutocode;
    }

    public void setPkSysAutocode(String pkSysAutocode) {
        this.pkSysAutocode = pkSysAutocode;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getIsline() {
        return isline;
    }

    public void setIsline(Integer isline) {
        this.isline = isline;
    }

    public Integer getZeroWidth() {
        return zeroWidth;
    }

    public void setZeroWidth(Integer zeroWidth) {
        this.zeroWidth = zeroWidth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

}