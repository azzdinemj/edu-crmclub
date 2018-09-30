package com.wuxue.model.junhwa;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class DayStudent extends BusinessPage{
    private Integer dayStudentId;

    private String pkStudent;

    private Date deliveryDate;

    private String deliveryDateTime;

    private Integer type;

    private Integer eatType;

    private String pkClassinfo;

    private Integer isvalid;

    public Integer getDayStudentId() {
        return dayStudentId;
    }

    public void setDayStudentId(Integer dayStudentId) {
        this.dayStudentId = dayStudentId;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEatType() {
        return eatType;
    }

    public void setEatType(Integer eatType) {
        this.eatType = eatType;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }
}