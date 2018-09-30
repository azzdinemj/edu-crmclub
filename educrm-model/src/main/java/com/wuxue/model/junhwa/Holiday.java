package com.wuxue.model.junhwa;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class Holiday extends BusinessPage{
    private Integer id;

    private Date holidayDay;

    private String holidayDayTime;

    private Integer type;

    private String holidayId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(Date holidayDay) {
        this.holidayDay = holidayDay;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public String getHolidayDayTime() {
        return holidayDayTime;
    }

    public void setHolidayDayTime(String holidayDayTime) {
        this.holidayDayTime = holidayDayTime;
    }
}