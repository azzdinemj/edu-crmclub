package com.wuxue.model;

import com.wuxue.base.BaiscPage;
import com.wuxue.base.BusinessPage;

import java.util.Date;

public class DormRoomStudent extends BusinessPage {
    private String pkDormStudent;

    private String pkDomain;

    private String code;

    private String pkDormRoom;

    private String pkStudent;

    private Integer isvalid;

    private Date date;

    private String dateTime;

    private Date checkInTime;

    private String checkInTimes;

    private Date expireTime;

    private String expireTimes;

    private String memo;

    public String getPkDormStudent() {
        return pkDormStudent;
    }

    public void setPkDormStudent(String pkDormStudent) {
        this.pkDormStudent = pkDormStudent;
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

    public String getPkDormRoom() {
        return pkDormRoom;
    }

    public void setPkDormRoom(String pkDormRoom) {
        this.pkDormRoom = pkDormRoom;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCheckInTimes() {
        return checkInTimes;
    }

    public void setCheckInTimes(String checkInTimes) {
        this.checkInTimes = checkInTimes;
    }

    public String getExpireTimes() {
        return expireTimes;
    }

    public void setExpireTimes(String expireTimes) {
        this.expireTimes = expireTimes;
    }
}