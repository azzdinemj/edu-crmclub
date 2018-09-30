package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class ClassRoom extends BaiscPage {
    private String pkClassRoom;

    private String pkDomain;

    private String code;

    private String caption;

    private Integer num;

    private Integer sort;

    private String memo;

    private Integer isvalid;

    public String getPkClassRoom() {
        return pkClassRoom;
    }

    public void setPkClassRoom(String pkClassRoom) {
        this.pkClassRoom = pkClassRoom;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}