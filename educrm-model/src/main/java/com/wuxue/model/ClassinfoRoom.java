package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class ClassinfoRoom extends BusinessPage {
    private String pkClassinfoRoom;

    private String pkDomain;

    private String pkClassinfo;

    private String pkClassroom;

    private Integer isvalid;

    private Integer kind;

    private String memo;

    private String caption;


    public String getPkClassinfoRoom() {
        return pkClassinfoRoom;
    }

    public void setPkClassinfoRoom(String pkClassinfoRoom) {
        this.pkClassinfoRoom = pkClassinfoRoom;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getPkClassroom() {
        return pkClassroom;
    }

    public void setPkClassroom(String pkClassroom) {
        this.pkClassroom = pkClassroom;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}