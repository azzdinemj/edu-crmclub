package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class SysDict extends BaiscPage {
    private String pkSysDict;

    private String caption;

    private String kind;

    private String memo;

    private Integer sort;

    private Integer num;

    private Integer isvalid;

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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